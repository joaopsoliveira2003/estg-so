package classes;

import classes.gui.PassengersLogFrame;
import classes.gui.TrainsLogFrame;
import enumerations.Direction;
import interfaces.*;

import javax.swing.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * This class represents a train
 */
public class Train implements TrainADT, Runnable, Serializable {
    private String name;
    private int maxPassengers;
    private List<PassengerADT> passengers;
    private List<ScheduleADT> schedules;
    private StationADT currentStation;
    private TrackADT currentTrack;
    private boolean isMoving, isConflictuous, isOverCrowded;
    private Semaphore semaphoreToDepart, semaphoreOfSchedules, semaphoreOfPassengers;

    /**
     * Constructor for the Train class
     *
     * @param name the name of the train
     * @param maxPassengers the maximum number of passengers that the train can carry
     * @param currentStation the station that the train is currently at
     */
    public Train(String name, int maxPassengers, StationADT currentStation) {
        this.name = name;
        this.maxPassengers = maxPassengers;
        this.passengers = new LinkedList<>();
        this.schedules = new LinkedList<>();
        this.currentStation = currentStation;
        this.currentTrack = null;
        this.isMoving = false;
        this.isConflictuous = false;
        this.isOverCrowded = false;
        this.semaphoreToDepart = new Semaphore(0);
        this.semaphoreOfSchedules = new Semaphore(1, true);
        this.semaphoreOfPassengers = new Semaphore(1, true);
        currentStation.getSemaphoreOfTrains().acquireUninterruptibly();
        currentStation.getCurrentTrains().add(this);
    }

    /**
     * This method is used to get the name of the train
     *
     * @return the name of the train
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This method is used to set the name of the train
     *
     * @param name the name of the train
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to get the max capacity of the train
     *
     * @return the max capacity of the train
     */
    @Override
    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * This method is used to set the max capacity of the train
     *
     * @param maxPassengers the capacity of the train
     */
    @Override
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    /**
     * This method is used to get the passengers of the train
     *
     * @return the passengers of the train
     */
    @Override
    public List<PassengerADT> getPassengers() {
        return passengers;
    }

    /**
     * This method is used to set the passengers of the train
     *
     * @param passengers the passengers of the train
     */
    @Override
    public void setPassengers(List<PassengerADT> passengers) {
        this.passengers = passengers;
    }

    /**
     * This method is used to get the schedules of the train
     *
     * @return the schedules of the train
     */
    @Override
    public List<ScheduleADT> getSchedules() {
        return schedules;
    }

    /**
     * This method is used to set the schedules of the train
     *
     * @param schedules the schedules of the train
     */
    @Override
    public void setSchedules(List<ScheduleADT> schedules) {
        this.schedules = schedules;
    }

    /**
     * This method is used to get the current station of the train
     *
     * @return the current station of the train
     */
    @Override
    public StationADT getCurrentStation() {
        return currentStation;
    }

    /**
     * This method is used to set the current station of the train
     *
     * @param currentStation the current station of the train
     */
    @Override
    public void setCurrentStation(StationADT currentStation) {
        this.currentStation = currentStation;
    }

    /**
     * This method is used to get the current track of the train
     *
     * @return the current track of the train
     */
    @Override
    public TrackADT getCurrentTrack() {
        return currentTrack;
    }

    /**
     * This method is used to set the current track of the train
     *
     * @param currentTrack the current track of the train
     */
    @Override
    public void setCurrentTrack(TrackADT currentTrack) {
        this.currentTrack = currentTrack;
    }

    /**
     * This method is used to know if the train is moving
     *
     * @return true if the train is moving, false otherwise
     */
    @Override
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * This method is used to set the moving status of the train
     *
     * @param moving the moving status of the train
     */
    @Override
    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    /**
     * This method is used to know if the train is conflictuous
     *
     * @return true if the train is conflictuous, false otherwise
     */
    @Override
    public boolean isConflictuous() {
        return isConflictuous;
    }

    /**
     * This method is used to set the conflictuous status of the train
     *
     * @param conflictuous the conflictuous status of the train
     */
    @Override
    public void setConflictuous(boolean conflictuous) {
        isConflictuous = conflictuous;
    }

    /**
     * This method is used to get the semaphore to depart of the train
     *
     * @return the semaphore to depart of the train
     */
    @Override
    public Semaphore getSemaphoreToDepart() {
        return semaphoreToDepart;
    }

    /**
     * This method is used to set the semaphore to depart of the train
     *
     * @param semaphoreToDepart the semaphore to depart of the train
     */
    @Override
    public void setSemaphoreToDepart(Semaphore semaphoreToDepart) {
        this.semaphoreToDepart = semaphoreToDepart;
    }

    /**
     * This method is used to get the semaphore of the schedules
     *
     * @return the semaphore of the schedules
     */
    @Override
    public Semaphore getSemaphoreOfSchedules() {
        return semaphoreOfSchedules;
    }

    /**
     * This method is used to set the semaphore of the schedules
     *
     * @param semaphoreOfSchedules the semaphore of the schedules
     */
    @Override
    public void setSemaphoreOfSchedules(Semaphore semaphoreOfSchedules) {
        this.semaphoreOfSchedules = semaphoreOfSchedules;
    }

    /**
     * This method is used to get the semaphore of the passengers
     *
     * @return the semaphore of the passengers
     */
    @Override
    public Semaphore getSemaphoreOfPassengers() {
        return semaphoreOfPassengers;
    }

    /**
     * This method is used to set the semaphore of the passengers
     *
     * @param semaphoreOfPassengers the semaphore of the passengers
     */
    @Override
    public void setSemaphoreOfPassengers(Semaphore semaphoreOfPassengers) {
        this.semaphoreOfPassengers = semaphoreOfPassengers;
    }

    /**
     * This method is used to know if the train has finished its schedule
     *
     * @return true if the train has finished its schedule, false otherwise
     */
    @Override
    public boolean isFinished() {
        return schedules.isEmpty();
    }

    /**
     * This method is used to set the finished status of the train
     *
     * @param finished the finished status of the train
     */
    @Override
    public void setFinished(boolean finished) {
        if (finished) {
            schedules.clear();
        }
    }

    /**
     * This method is used to get the crowdness of the train
     *
     * @return the current schedule of the train
     */
    @Override
    public boolean isOverCrowded() {
        return isOverCrowded;
    }

    /**
     * This method is used to set the crowdness of the train
     *
     * @param overCrowded the crowdness of the train
     */
    @Override
    public void setOverCrowded(boolean overCrowded) {
        isOverCrowded = overCrowded;
    }

    /**
     * This method is used to generate a string representation of the train
     *
     * @return a string representation of the train
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * This method is executed when the thread is started
     */
    @Override
    public void run() {

        try {

            //check if the train has schedules
            while (schedules.size() > 0) {

                // acquire the lock of the train in order to get permision to depart
                semaphoreToDepart.acquireUninterruptibly();

                // acquire the semaphore of the schedules
                semaphoreOfSchedules.acquireUninterruptibly();

                // get the first schedule (first station to go to)
                ScheduleADT schedule = schedules.get(0);

                // release the semaphore of the schedules
                semaphoreOfSchedules.release();

                // get the first station to go to
                StationADT station = schedule.getStation();

                // get the direction of the train
                Direction direction = schedule.getDirection();

                // get the track to go to
                List<TrackADT> tracks = currentStation.getTracks();
                TrackADT track = null;
                for (TrackADT trackList : tracks) {
                    if ((direction == Direction.FORWARD) ? trackList.getEndStation() == station : trackList.getStartStation() == station) {
                        track = trackList;
                        break;
                    }
                }

                // check if the schedule is wrong
                if (track == null) {
                    JOptionPane.showMessageDialog(null, "Error: There is a wrong schedule! Exiting...");
                    System.exit(1);
                }

                // wait to depart
                try {
                    Thread.sleep((long) schedule.getTimeToDepart() * 60000);
                } catch (InterruptedException ignored) {}

                // try to acquire the track
                try {
                    track.getSemaphoreOfTrains().acquire();
                } catch (InterruptedException ignored) {}

                // set the current track
                currentTrack = track;

                // set the current train of the track as this train
                currentTrack.setTrain(this);

                // remove this train from the station
                currentStation.getCurrentTrains().remove(this);

                // release the lock of the station
                currentStation.getSemaphoreOfTrains().release();

                // set the train as moving
                isMoving = true;

                // log that the train is moving
                TrainsLogFrame.log("Train " + name + " (" + currentStation.getName() + " -> " + station.getName() + ") [" + passengers.size() + "/" + this.maxPassengers + " passengers]");

                // simulate the movement
                try {
                    Thread.sleep((long) (schedule.getTimeToArrive() * 60000L + track.getLength() * 30000L));
                } catch (InterruptedException ignored) {}

                // try to acquire the station semaphore
                station.getSemaphoreOfTrains().acquireUninterruptibly();

                // add this train to the station
                station.getCurrentTrains().add(this);

                // log that the train arrived
                TrainsLogFrame.log("Train " + name + " is at " + station.getName() + " [" + (schedules.size() - 1) + " stations remaining]");

                // set the train in the track as null
                track.setTrain(null);

                // release the track
                track.getSemaphoreOfTrains().release();

                // acquire the semaphore of the schedules
                semaphoreOfSchedules.acquireUninterruptibly();

                // remove the first schedule
                schedules.remove(0);

                // release the semaphore of the schedules
                semaphoreOfSchedules.release();

                // set the current station
                currentStation = station;

                // set the current track
                currentTrack = null;

                // set the train to not moving
                isMoving = false;
            }

            this.isOverCrowded = false;

            // fix sleepy passengers
            this.semaphoreOfPassengers.acquireUninterruptibly();

            for (PassengerADT passenger : passengers) {
                if (passenger.getCurrentStation() == null) {
                    passenger.setCurrentTrain(null);
                    passenger.setCurrentStation(null);
                    passenger.setOnDestination(true);
                    passenger.getCard().removeTravels(1);
                    PassengersLogFrame.log("[END] " + passenger.getName() + " is at the station " + currentStation.getName());
                }
            }

            passengers.clear();

            this.semaphoreOfPassengers.release();

        } catch (ThreadDeath ignored) {}
    }
}
