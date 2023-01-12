package classes;

import enumerations.Direction;
import interfaces.ScheduleADT;
import interfaces.StationADT;

import java.io.Serializable;

/**
 * This class represents a schedule
 */
public class Schedule implements ScheduleADT, Serializable {
    private StationADT station;
    private double timeToArrive, timeToDepart;
    private Direction direction;


    /**
     * Constructor for the schedule
     *
     * @param station the station
     * @param timeToArrive the time to arrive
     * @param timeToDepart the time to depart
     * @param direction the direction
     */
    public Schedule(StationADT station, double timeToArrive, double timeToDepart, Direction direction) {
        this.station = station;
        this.timeToArrive = timeToArrive;
        this.timeToDepart = timeToDepart;
        this.direction = direction;
    }

    /**
     * This method is used to get the station that the train has to stop
     *
     * @return the station that the train has to stop
     */
    @Override
    public StationADT getStation() {
        return station;
    }

    /**
     * This method is used to set the station that the train has to stop
     *
     * @param station the station that the train has to stop
     */
    @Override
    public void setStation(StationADT station) {
        this.station = station;
    }

    /**
     * This method is used to get the time to arrive
     *
     * @return the time to arrive
     */
    @Override
    public double getTimeToArrive() {
        return timeToArrive;
    }

    /**
     * This method is used to set the time to arrive
     *
     * @param timeToArrive the time to arrive
     */
    @Override
    public void setTimeToArrive(double timeToArrive) {
        this.timeToArrive = timeToArrive;
    }

    /**
     * This method is used to get the time to depart
     *
     * @return the time to depart
     */
    @Override
    public double getTimeToDepart() {
        return timeToDepart;
    }

    /**
     * This method is used to set the time to depart
     *
     * @param timeToDepart the time to depart
     */
    @Override
    public void setTimeToDepart(double timeToDepart) {
        this.timeToDepart = timeToDepart;
    }

    /**
     * This method is used to get the direction that the train is going
     *
     * @return the direction that the train is going
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * This method is used to set the direction that the train is going
     *
     * @param direction the direction that the train is going
     */
    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * This method is used to get the string representation of the schedule
     *
     * @return the string representation of the schedule
     */
    @Override
    public String toString() {
        return "Schedule{" +
                "station=" + station +
                ", timeToArrive=" + timeToArrive +
                ", timeToDepart=" + timeToDepart +
                ", direction=" + direction +
                '}';
    }
}
