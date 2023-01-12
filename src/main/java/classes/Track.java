package classes;

import interfaces.StationADT;
import interfaces.TrackADT;
import interfaces.TrainADT;

import java.io.Serializable;
import java.util.concurrent.Semaphore;

/**
 * This class represents a track that represents a path between two stations
 */
public class Track implements TrackADT, Serializable {
    private String name;
    private double length;
    private StationADT startStation, endStation;
    private Semaphore semaphoreOfTrains;
    private TrainADT train;

    /**
     * Constructor for the Track class
     *
     * @param name the name of the track
     * @param length the length of the track
     * @param startStation the station that the track starts from
     * @param endStation the station that the track ends at
     */
    public Track(String name, double length, StationADT startStation, StationADT endStation) {
        this.name = name;
        this.length = length;
        this.startStation = startStation;
        this.endStation = endStation;
        this.semaphoreOfTrains = new Semaphore(1, true);
        this.train = null;
        startStation.getTracks().add(this);
        endStation.getTracks().add(this);
    }

    /**
     * This method is used to get the name of the track
     *
     * @return the name of the track
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This method is used to set the name of the track
     *
     * @param name the name of the track
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to get the length of the track
     *
     * @return the length of the track
     */
    @Override
    public double getLength() {
        return length;
    }

    /**
     * This method is used to set the length of the track
     *
     * @param length the length of the track
     */
    @Override
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * This method is used to get the start station of the track
     *
     * @return the start station of the track
     */
    @Override
    public StationADT getStartStation() {
        return startStation;
    }

    /**
     * This method is used to set the start station of the track
     *
     * @param startStation the start station of the track
     */
    @Override
    public void setStartStation(StationADT startStation) {
        this.startStation = startStation;
    }

    /**
     * This method is used to get the end station of the track
     *
     * @return the end station of the track
     */
    @Override
    public StationADT getEndStation() {
        return endStation;
    }

    /**
     * This method is used to set the end station of the track
     *
     * @param endStation the end station of the track
     */
    @Override
    public void setEndStation(StationADT endStation) {
        this.endStation = endStation;
    }

    /**
     * This method is used to get the semaphore of the track
     *
     * @return the semaphore of the track
     */
    @Override
    public Semaphore getSemaphoreOfTrains() {
        return semaphoreOfTrains;
    }

    /**
     * This method is used to set the semaphore of the track
     *
     * @param semaphoreOfTrains the semaphore of the track
     */
    @Override
    public void setSemaphoreOfTrains(Semaphore semaphoreOfTrains) {
        this.semaphoreOfTrains = semaphoreOfTrains;
    }

    /**
     * This method is used to get the train on the track
     *
     * @return the train on the track
     */
    @Override
    public TrainADT getTrain() {
        return train;
    }

    /**
     * This method is used to set the train on the track
     *
     * @param train the train on the track
     */
    @Override
    public void setTrain(TrainADT train) {
        this.train = train;
    }

    /**
     * This method is used to know if a train is going to a specific station (first station of the schedule)
     *
     * @param station the station to check
     * @return true if the train is going to the station, false otherwise
     */
    @Override
    public boolean isTrainGoingToStation(StationADT station) {
        return train != null && !train.getSchedules().isEmpty() && train.getSchedules().get(0).getStation() == station;

    }

    /**
     * This method is used to get the string representation of the track
     *
     * @return the string representation of the track
     */
    @Override
    public String toString() {
        return "Track{" +
                "name='" + name +
                ", length=" + length +
                ", startStation=" + startStation +
                ", endStation=" + endStation +
                ", semaphoreOfTrains=" + semaphoreOfTrains +
                ", train=" + train +
                '}';
    }
}
