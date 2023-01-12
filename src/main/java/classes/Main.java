package classes;

import classes.gui.ConflictsLogFrame;
import classes.gui.MainFrame;
import classes.gui.PassengersLogFrame;
import classes.gui.TrainsLogFrame;
import interfaces.*;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is the main class of the program
 */
public class Main {

    // linked lists holding the data
    private static final List<StationADT> stations = new LinkedList<>();
    private static final List<TrackADT> tracks = new LinkedList<>();
    private static final List<TrainADT> trains = new LinkedList<>();
    private static final List<PassengerADT> passengers = new LinkedList<>();

    // frames
    private static ConflictsLogFrame conflictsLogFrame;
    private static PassengersLogFrame passengersLogFrame;
    private static TrainsLogFrame trainsLogFrame;

    // object used to wake the threads that check if the program has to be running
    private static final Object lock = new Object();

    // variable to control running status of the program
    public static final AtomicBoolean running = new AtomicBoolean(false);

    // hashmaps to hold the threads in order to be able to start and stop them
    private static final Map<TrainADT, Thread> trainThreads = new HashMap<>();
    private static final Map<PassengerADT, Thread> passengerThreads = new HashMap<>();

    /**
     * This function is used to start all the windows of the program
     */
    private static void startGUI() {
        conflictsLogFrame = new ConflictsLogFrame();
        passengersLogFrame = new PassengersLogFrame();
        trainsLogFrame = new TrainsLogFrame();
        new MainFrame(stations, tracks, trains, passengers);
    }

    /**
     * This function is used to stop the logs windows of the program
     */
    private static void stopGUI() {
        conflictsLogFrame.dispose();
        passengersLogFrame.dispose();
        trainsLogFrame.dispose();
    }

    /**
     * This function is used to start the trains and the passengers threads
     */
    private static void startThreads() {
        for (PassengerADT passengerADT : passengers) {
            Thread thread = new Thread((Runnable) passengerADT);
            passengerThreads.put(passengerADT, thread);
            thread.start();
        }
        for (TrainADT trainADT : trains) {
            Thread thread = new Thread((Runnable) trainADT);
            trainThreads.put(trainADT, thread);
            thread.start();
        }
    }

    /**
     * This function is used to stop the trains and the passengers threads
     */
    public static void stopThreads() {
        for (Map.Entry<PassengerADT, Thread> entry : passengerThreads.entrySet()) {
            try {
                entry.getValue().stop();
            } catch (ThreadDeath ignored) {}
        }
        for (Map.Entry<TrainADT, Thread> entry : trainThreads.entrySet()) {
            try {
                entry.getValue().stop();
            } catch (ThreadDeath ignored) {}
        }
    }

    /**
     * This function is responsible for boarding a passenger
     * @param passengerADT The passenger to be boarded
     * @param trainADT The train that the passenger is boarding
     */
    private static void boardPassenger(PassengerADT passengerADT, TrainADT trainADT) {
        // set current train of passenger
        passengerADT.setCurrentTrain(trainADT);

        // add passenger to the train
        trainADT.getPassengers().add(passengerADT);

        // release the semaphore of the passenger to simulate the boarding
        passengerADT.getSemaphoreToBoard().release();
    }

    /**
     * This function is responsible for checking if a train is over capacity
     * If the train is over capacity, the passengers are removed from the train
     *
     * @param train The train to check
     * @return true if the train is over capacity, false otherwise
     */
    private static boolean checkTrainForOverCrowding(TrainADT train) {

        // acquire the semaphore of schedules of the train
        train.getSemaphoreOfSchedules().acquireUninterruptibly();

        // if the train schedules are empty
        if (train.getSchedules().isEmpty()) {

            // release the semaphore of schedules of the train
            train.getSemaphoreOfSchedules().release();

            // nothing to do
            return false;
        }

        // release the semaphore of schedules of the train
        train.getSemaphoreOfSchedules().release();

        // acquire the semaphore of passengers of the train
        train.getSemaphoreOfPassengers().acquireUninterruptibly();

        // if the train passengers are empty
        if (train.getPassengers().isEmpty()) {

            // release the semaphore of passengers of the train
            train.getSemaphoreOfPassengers().release();

            // nothing to do
            return false;
        }

        // calculate over capacity with the number of passengers on the train and max capacity of the train
        int overCapacity = train.getPassengers().size() - train.getMaxPassengers();

        // release the semaphore of passengers of the train
        train.getSemaphoreOfPassengers().release();

        // if the train is overcrowded
        return overCapacity > 0;
    }

    private static void detectTrainsOppositeDirection() {

        // run inside a thread
        new Thread(() -> {

            // use a hashmap to save the last train in the station
            Map<TrackADT, TrainADT> trackTrainMap = new HashMap<>();
            for (TrackADT track : tracks) {
                if (track.getTrain() != null) {
                    trackTrainMap.put(track, track.getTrain());
                }
            }

            // while the simulation is running
            while (running.get()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}

                // check if there are tracks with trains waiting
                for (TrackADT track : tracks) {
                    if (track.getSemaphoreOfTrains().hasQueuedThreads()) {

                        // check if the train is not the same as the last train in the track
                        if (trackTrainMap.get(track) != track.getTrain()) {

                            // log the conflict
                            ConflictsLogFrame.log("Track " + track.getName() + " is occupied [" + track.getSemaphoreOfTrains().getQueueLength() + " train(s) waiting]");

                            // update the last train in the track
                            trackTrainMap.put(track, track.getTrain());
                        }
                    }
                }
            }
        }).start();
    }

    public static void preventOverCrowdedTrainStation(TrainADT train) {

        // acquire the semaphore of schedules of the train
        train.getSemaphoreOfSchedules().acquireUninterruptibly();

        // if the train schedules are empty
        if (train.getSchedules().isEmpty()) {

            // release the semaphore of schedules of the train
            train.getSemaphoreOfSchedules().release();

            // nothing to do
            return;
        }

        // save schedule into a variable
        ScheduleADT schedule = train.getSchedules().get(0);

        // save the station into a variable
        StationADT station = schedule.getStation();

        // release the semaphore of schedules of the train
        train.getSemaphoreOfSchedules().release();

        // number of trains going
        int goingTrains = (int) station.getTracks().stream()
                .filter(track -> track.isTrainGoingToStation(station))
                .count();

        // validate if going trains is more than of permits
        if (goingTrains + 1 > station.getSemaphoreOfTrains().availablePermits()) {

            // check if the station was already marked as overcrowded
            if (!station.isOverCapacity()) {

                // log the station as overcrowded
                ConflictsLogFrame.log("Station " + station.getName() + " is full [" + goingTrains + " train(s) waiting]");

                // set the station as overcrowded
                station.setOverCapacity(true);
            }

            // set the train as conflictuous in order to prevent it from leaving the station
            train.setConflictuous(true);
        } else {

            if (station.isOverCapacity()) {

                // log the station as not overcrowded
                ConflictsLogFrame.log("Station " + station.getName() + " is not full anymore");

                // set the station as not overcrowded
                station.setOverCapacity(false);
            }

            // check if the train is conflictuous
            if (train.isConflictuous()) {

                // set the train as not conflictuous
                train.setConflictuous(false);
            }
        }
    }
    /**
     * This function is the main method of the program
     * @param args the arguments of the program
     */
    public static void main(String[] args) {

        // start the control panel
        startGUI();

        // wait for the user to start the simulation
        while (!running.get()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        // check if all trains have not finished meaning that the simulation isn't over
        if (!trains.stream().allMatch(TrainADT::isFinished)) {

            // start the threads
            startThreads();

            // thread for checking if all trains have finished in order to stop the simulation
            new Thread(() -> {
                while (!trains.stream().allMatch(TrainADT::isFinished) && running.get()) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {}
                    }
                }
                running.set(false);
            }).start();

            // thread for checking if there are trains going in opposite directions
            detectTrainsOppositeDirection();

            // main module
            while (running.get()) {

                // go through all the trains
                for (TrainADT train : trains) {

                    // sleep for 2 seconds
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ignored) {}

                    // save the station into a variable
                    StationADT station = train.getCurrentStation();

                    // if the train is waiting
                    if (train.getSemaphoreToDepart().hasQueuedThreads()) {

                        // prevent trains from overcrowding their next station by waiting
                        preventOverCrowdedTrainStation(train);

                        // check if the train is not conflictuous
                        if (train.isConflictuous()) {
                            continue;
                        }

                        // list for saving the passengers that might be removed from the train
                        List<PassengerADT> passengersToAddInTheStation = new ArrayList<>();

                        // check if the train was marked as overcrowded
                        if (train.isOverCrowded()) {

                            // check if the train is actually overcrowded
                            if (checkTrainForOverCrowding(train)) {

                                // acquire the semaphore of passengers of the train
                                train.getSemaphoreOfPassengers().acquireUninterruptibly();

                                // sort the passengers of the train based on their destination
                                train.getPassengers().sort(Comparator.comparingInt(o -> o.getEntryStation().getDistanceTo(o.getDestinationStation())));

                                // remove the passengers from the train
                                while (train.getPassengers().size() > train.getMaxPassengers()) {

                                    // remove the passenger from the train
                                    PassengerADT passenger = train.getPassengers().remove(0);

                                    // add the passenger to the list of passengers to add in the station
                                    passengersToAddInTheStation.add(passenger);

                                    // stop the passenger thread
                                    passengerThreads.get(passenger).stop();

                                    // log the passenger as removed from the train
                                    PassengersLogFrame.log("[OVER_CAPACITY] " + passenger.getName() + " is at " + station.getName());

                                    // set the current train of the passenger to null
                                    passenger.setCurrentTrain(null);

                                    // set the current station of the passenger to the station of the train
                                    passenger.setCurrentStation(station);

                                    // set the exit station of the passenger to their destination station
                                    passenger.setExitStation(passenger.getDestinationStation());
                                }

                                // release the semaphore of passengers of the train
                                train.getSemaphoreOfPassengers().release();
                            }

                            // log the train as not overcrowded
                            ConflictsLogFrame.log("Train " + train.getName() + " is no longer over capacity");

                            // set as not overcrowded
                            train.setOverCrowded(false);
                        }

                        // check if the current station of the train has passengers
                        if (train.getCurrentStation().getPassengers().size() > 0) {

                            // acquire the semaphore of handling the passengers of the station
                            station.getSemaphoreOfPassengers().acquireUninterruptibly();

                            // sort passengers of the current station of the train with the getDistanceTo method
                            station.getPassengers().sort(Comparator.comparingInt(o -> o.getEntryStation().getDistanceTo(o.getDestinationStation())));

                            // go through the passengers of the current station
                            for (PassengerADT passenger : station.getPassengers()) {

                                // check if the passenger is waiting to board a train
                                if (passenger.getSemaphoreToBoard().hasQueuedThreads()) {

                                    // check if the passenger is valid
                                    if (passenger.getCard().getTravels() > 0 && passenger.getCard().getExpirationYear() >= Calendar.getInstance().get(Calendar.YEAR) && passenger.getEntryStation().getDistanceTo(passenger.getDestinationStation()) >= passenger.getCard().getFirstStation().getDistanceTo(passenger.getCard().getLastStation())) {

                                        // acquire the semaphore of handling the passengers of the train
                                        train.getSemaphoreOfPassengers().acquireUninterruptibly();

                                        // if the train stops at the destination of the passenger
                                        if (train.getSchedules().stream().anyMatch(schedule -> schedule.getStation().equals(passenger.getDestinationStation()))) {
                                            boardPassenger(passenger, train);
                                        } else {
                                            // check if the distanceTo of the passenger is less than the distanceTo of the next station of the schedule of the train
                                            if (train.getCurrentStation().getDistanceTo(passenger.getDestinationStation()) > train.getSchedules().get(0).getStation().getDistanceTo(passenger.getDestinationStation())) {
                                                boardPassenger(passenger, train);
                                            }
                                        }

                                        // release the semaphore of handling the passengers of the train
                                        train.getSemaphoreOfPassengers().release();
                                    } else {
                                        // mark with invalid card
                                        passenger.setHasInvalidCard(true);

                                        // release the semaphore of the passenger in order to stop the thread (verification in the passenger that stops the thread)
                                        passenger.getSemaphoreToBoard().release();
                                    }
                                }
                            }

                            // release the semaphore of handling the passengers of the station
                            station.getSemaphoreOfPassengers().release();

                            // check if the train is overcrowded
                            if (checkTrainForOverCrowding(train)) {

                                // acquire the semaphore of handling the passengers of the train
                                train.getSemaphoreOfPassengers().acquireUninterruptibly();

                                // log the train as overcrowded
                                ConflictsLogFrame.log("Train " + train.getName() + " is over capacity [" + (train.getPassengers().size() - train.getMaxPassengers()) + " passengers]");

                                // release the semaphore of handling the passengers of the train
                                train.getSemaphoreOfPassengers().release();

                                // set as overcrowded
                                train.setOverCrowded(true);
                            }
                        }

                        // acquire the semaphore of the passenger of the station
                        station.getSemaphoreOfPassengers().acquireUninterruptibly();

                        // remove the passengers from the station that boarded trains
                        station.getPassengers().removeIf(passengerADT -> passengerADT.getCurrentTrain() != null);

                        // remove the passengers from the station that were marked with an invalid card
                        station.getPassengers().removeIf(PassengerADT::hasInvalidCard);

                        // add the passengers to the station that were removed from the train
                        station.getPassengers().addAll(passengersToAddInTheStation);

                        // start the threads of the passengers that were removed from the train
                        for (PassengerADT passenger : passengersToAddInTheStation) {
                            Thread thread = new Thread((Runnable) passenger);
                            passengerThreads.put(passenger, thread);
                            thread.start();
                        }

                        // clear the list of passengers to add in the station
                        passengersToAddInTheStation.clear();

                        // release the semaphore of the passengers of the station
                        station.getSemaphoreOfPassengers().release();

                        // allow the train to depart
                        train.getSemaphoreToDepart().release();
                    }
                }

                // wake the thread that checks if all trains have arrived the destination
                synchronized (lock) {
                    lock.notifyAll();
                }
            }

            // stop all threads if not stopped
            stopThreads();

            // window to tell the simulation is over
            JOptionPane.showMessageDialog(null, "The program has finished!");

        } else {
            // window to tell the simulation is already over
            JOptionPane.showMessageDialog(null, "All trains have already finished!");

            // window to close the windows of the logs
            stopGUI();
        }
    }
}