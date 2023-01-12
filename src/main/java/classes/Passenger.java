package classes;

import classes.gui.PassengersLogFrame;
import interfaces.CardADT;
import interfaces.PassengerADT;
import interfaces.StationADT;
import interfaces.TrainADT;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.Semaphore;

/**
 * This class represents a passenger
 */
public class Passenger implements PassengerADT, Runnable, Comparable<Passenger>, Serializable {
    private String name;
    private int age;
    private CardADT card;
    private StationADT entryStation, exitStation, destinationStation, currentStation;
    private TrainADT currentTrain;
    private Semaphore semaphoreToBoard;
    private boolean isOnDestination, hasInvalidCard;

    /**
     * Constructor of the class Passenger
     *
     * @param name The name of the passenger
     * @param age The age of the passenger
     * @param card The card of the passenger
     * @param entryStation The entry station of the passenger
     * @param destinationStation The destination station of the passenger
     */
    public Passenger(String name, int age, CardADT card, StationADT entryStation, StationADT destinationStation) {
        this.name = name;
        this.age = age;
        this.card = card;
        this.entryStation = entryStation;
        this.destinationStation = destinationStation;
        this.currentStation = entryStation;
        this.exitStation = destinationStation;
        this.currentTrain = null;
        this.semaphoreToBoard = new Semaphore(0);
        this.isOnDestination = false;
        this.hasInvalidCard = false;
        entryStation.getPassengers().add(this);
    }

    /**
     * This method is used to get the name of the passenger
     *
     * @return the name of the card
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This method is used to set the name of the passenger
     *
     * @param name the name of the passenger
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to get the age of the passenger
     *
     * @return the age of the passenger
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * This method is used to set the age of the passenger
     *
     * @param age the age of the passenger
     */
    @Override
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * This method is used to get the card of the passenger
     *
     * @return the card of the passenger
     */
    @Override
    public CardADT getCard() {
        return card;
    }

    /**
     * This method is used to set the card of the passenger
     *
     * @param card the card of the passenger
     */
    @Override
    public void setCard(CardADT card) {
        this.card = card;
    }

    /**
     * This method is used to get the entry station of the passenger
     *
     * @return the entry station
     */
    @Override
    public StationADT getEntryStation() {
        return entryStation;
    }

    /**
     * This method is used to set the entry station of the passenger
     *
     * @param entryStation the entry station of the passenger
     */
    @Override
    public void setEntryStation(StationADT entryStation) {
        this.entryStation = entryStation;
    }

    /**
     * This method is used to get the destination station of the passenger
     *
     * @return the destination station of the passenger
     */
    @Override
    public StationADT getDestinationStation() {
        return destinationStation;
    }

    /**
     * This method is used to set the destination station of the passenger
     *
     * @param destinationStation the destination station of the passenger
     */
    @Override
    public void setDestinationStation(StationADT destinationStation) {
        this.destinationStation = destinationStation;
    }

    /**
     * This method is used to get the current station of the passenger
     *
     * @return the current station of the passenger
     */
    @Override
    public StationADT getCurrentStation() {
        return currentStation;
    }

    /**
     * This method is used to set the current station of the passenger
     *
     * @param currentStation the current station of the passenger
     */
    @Override
    public void setCurrentStation(StationADT currentStation) {
        this.currentStation = currentStation;
    }

    /**
     * This method is used to get the next exit station of the passenger
     *
     * @return the next exit station of the passenger
     */
    @Override
    public StationADT getExitStation() {
        return exitStation;
    }

    /**
     * This method is used to set the next exit station of the passenger
     *
     * @param exitStation the next exit station of the passenger
     */
    @Override
    public void setExitStation(StationADT exitStation) {
        this.exitStation = exitStation;
    }

    /**
     * This method is used to get the train of the passenger
     *
     * @return the train of the passenger
     */
    @Override
    public TrainADT getCurrentTrain() {
        return currentTrain;
    }

    /**
     * This method is used to set the train of the passenger
     *
     * @param currentTrain the train of the passenger
     */
    @Override
    public void setCurrentTrain(TrainADT currentTrain) {
        this.currentTrain = currentTrain;
    }

    /**
     * This method is used to get the semaphore of the passenger to board
     *
     * @return the semaphore of the passenger to board
     */
    @Override
    public Semaphore getSemaphoreToBoard() {
        return semaphoreToBoard;
    }

    /**
     * This method is used to set the semaphore of the passenger to board
     *
     * @param semaphoreToBoard the semaphore of the passenger to board
     */
    @Override
    public void setSemaphoreToBoard(Semaphore semaphoreToBoard) {
        this.semaphoreToBoard = semaphoreToBoard;
    }

    /**
     * This method is used to get the destination status of the passenger
     *
     * @return the destination status of the passenger
     */
    @Override
    public boolean isOnDestination() {
        return isOnDestination;
    }

    /**
     * This method is used to set the destination status of the passenger
     *
     * @param isOnDestination the destination status of the passenger
     */
    @Override
    public void setOnDestination(boolean isOnDestination) {
        this.isOnDestination = isOnDestination;
    }

    /**
     * This method is used to get the invalid card status of the passenger
     *
     * @return the invalid card status of the passenger
     */
    @Override
    public boolean hasInvalidCard() {
        return hasInvalidCard;
    }

    /**
     * This method is used to set the invalid card status of the passenger
     *
     * @param hasInvalidCard the invalid card status of the passenger
     */
    @Override
    public void setHasInvalidCard(boolean hasInvalidCard) {
        this.hasInvalidCard = hasInvalidCard;
    }

    /**
     * This method is used to compare two passengers and their corresponding travels (ammount of stations between)
     *
     * @param other The passenger to be compared
     * @return 0 if the two passengers are equal, 1 if the current passenger is greater than the passenger to be compared, -1 if the current passenger is less than the passenger to be compared
     */
    @Override
    public int compareTo(Passenger other) {
        return Integer.compare(this.entryStation.getDistanceTo(this.destinationStation), other.getEntryStation().getDistanceTo(other.getDestinationStation()));
    }

    /**
     * This method is used to get the string representation of the passenger
     *
     * @return the string representation of the passenger
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

            // check if the is not on destination
            while (!isOnDestination) {

                // try to acquire lock
                semaphoreToBoard.acquireUninterruptibly();

                // check if the passenger has invalid card
                if (hasInvalidCard()) {
                    PassengersLogFrame.log("[CARD] " + this.name + " is at the station " + this.currentStation.getName());
                    return;
                }

                // set current station as null because now the passenger is going to be on the train
                currentStation = null;

                // acquire the semaphore of schedules of the train
                currentTrain.getSemaphoreOfSchedules().acquireUninterruptibly();

                // check if exitStation is on the route of this train and wait for it otherwise find the station to leave in order to wait for the next train
                if (currentTrain.getSchedules().stream().anyMatch(station -> station.getStation().equals(destinationStation))) {

                    // release the semaphore of schedules of the train
                    currentTrain.getSemaphoreOfSchedules().release();

                    // log the entry of the passenger
                    PassengersLogFrame.log("[ENTRY] " + this.getName() + " entered the train " + currentTrain.getName() + " at the station " + currentTrain.getCurrentStation().getName());

                    // wait for the train to reach the destination station
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            if (currentTrain == null) {
                                return;
                            }
                            if (currentTrain.getCurrentStation() == null) {
                                continue;
                            }
                            if (currentTrain.getCurrentStation().equals(destinationStation)) {
                                break;
                            }
                        } catch (InterruptedException ignored) {}
                    }

                    // set the passenger as on destination
                    isOnDestination = true;

                    // set the current station as null
                    currentStation = null;

                    // remove travel from the passenger card
                    card.removeTravels(1);

                    // log the exit of the passenger
                    PassengersLogFrame.log("[END] " + this.getName() + " is at the station " + destinationStation.getName());

                } else {

                    // find the station with the minimum distance to the exit station
                    exitStation = Objects.requireNonNull(currentTrain.getSchedules().stream().min(Comparator.comparingInt(station -> station.getStation().getDistanceTo(destinationStation))).orElse(null)).getStation();

                    // release the semaphore of schedules of the train
                    currentTrain.getSemaphoreOfSchedules().release();

                    // log the entry of the passenger
                    PassengersLogFrame.log("[ENTRY] " + this.getName() + " entered the train " + currentTrain.getName() + " at the station " + currentTrain.getCurrentStation().getName());

                    // wait for the train to arrive at the next station
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            if (currentTrain == null) {
                                return;
                            }
                            if (currentTrain.getCurrentStation() == null) {
                                continue;
                            }
                            if (currentTrain.getCurrentStation().equals(exitStation)) {
                                break;
                            }
                        } catch (InterruptedException ignored) {}
                    }

                    // log the exit of the passenger
                    PassengersLogFrame.log("[MIDDLE] " + this.getName() + " is at the station " + exitStation.getName());

                    // set the current station to the next station
                    currentStation = exitStation;

                    // acquire the semaphore of passengers of the station
                    currentStation.getSemaphoreOfPassengers().acquireUninterruptibly();

                    // add passenger to the station
                    currentTrain.getCurrentStation().getPassengers().add(this);

                    // release the semaphore of passengers of the station
                    currentStation.getSemaphoreOfPassengers().release();
                }

                // set next station
                exitStation = destinationStation;

                // acquire the semaphore of passengers of the train
                currentTrain.getSemaphoreOfPassengers().acquireUninterruptibly();

                // remove passenger from the train
                currentTrain.getPassengers().remove(this);

                // release the semaphore of passengers of the train
                currentTrain.getSemaphoreOfPassengers().release();

                // set train as null
                currentTrain = null;
            }
        } catch (ThreadDeath ignored) {}
    }
}
