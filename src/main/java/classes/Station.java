package classes;

import interfaces.PassengerADT;
import interfaces.StationADT;
import interfaces.TrackADT;
import interfaces.TrainADT;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * This class represents a station
 */
public class Station implements StationADT, Serializable {
    private String name;
    private List<TrackADT> tracks;
    private List<PassengerADT> passengers;
    private List<TrainADT> currentTrains;
    private int maxTrains;
    private Semaphore semaphoreOfTrains,semaphoreOfPassengers;
    private boolean isOverCapacity;

    /**
     * Constructor for the Station class
     *
     * @param name the name of the station
     * @param maxTrains the maximum number of trains that can be in the station
     */
    public Station(String name, int maxTrains) {
        this.name = name;
        this.tracks = new LinkedList<>();
        this.passengers = new LinkedList<>();
        this.currentTrains = new LinkedList<>();
        this.maxTrains = maxTrains;
        this.semaphoreOfTrains = new Semaphore(maxTrains, true);
        this.semaphoreOfPassengers = new Semaphore(1, true);
        this.isOverCapacity = false;
    }

    /**
     * This method is used to get the name of the station
     *
     * @return the name of the station
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This method is used to set the name of the station
     *
     * @param name the name of the station
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to get the tracks that are connected to the station
     *
     * @return the tracks that are connected to the station
     */
    @Override
    public List<TrackADT> getTracks() {
        return tracks;
    }

    /**
     * This method is used to set the tracks that are connected to the station
     *
     * @param tracks the tracks that are connected to the station
     */
    @Override
    public void setTracks(List<TrackADT> tracks) {
        this.tracks = tracks;
    }

    /**
     * This method is used to get the passengers that are in the station
     *
     * @return the passengers that are in the station
     */
    @Override
    public List<PassengerADT> getPassengers() {
        return passengers;
    }

    /**
     * This method is used to set the passengers that are in the station
     *
     * @param passengers the passengers that are in the station
     */
    @Override
    public void setPassengers(List<PassengerADT> passengers) {
        this.passengers = passengers;
    }

    /**
     * This method is used to get the current trains at the station
     *
     * @return the current trains at the station
     */
    @Override
    public List<TrainADT> getCurrentTrains() {
        return currentTrains;
    }

    /**
     * This method is used to set the current trains at the station
     *
     * @param currentTrains the current trains at the station
     */
    @Override
    public void setCurrentTrains(List<TrainADT> currentTrains) {
        this.currentTrains = currentTrains;
    }

    /**
     * This method is used to get the train capacity of the station
     *
     * @return the train capacity of the station
     */
    @Override
    public int getMaxTrains() {
        return maxTrains;
    }

    /**
     * This method is used to set the train capacity of the station
     *
     * @param maxTrain the train capacity of the station
     */
    @Override
    public void setMaxTrains(int maxTrain) {
        this.maxTrains = maxTrain;
    }

    /**
     * This method is used to get the semaphore of trains of the station
     *
     * @return the semaphore of trains of the station
     */
    @Override
    public Semaphore getSemaphoreOfTrains() {
        return semaphoreOfTrains;
    }

    /**
     * This method is used to set the semaphore of trains of the station
     *
     * @param semaphoreOfTrains the semaphore of trains the station
     */
    @Override
    public void setSemaphoreOfTrains(Semaphore semaphoreOfTrains) {
        this.semaphoreOfTrains = semaphoreOfTrains;
    }

    /**
     * This method is used to get the semaphore of passengers of the station
     *
     * @return the semaphore of passengers of the station
     */
    @Override
    public Semaphore getSemaphoreOfPassengers() {
        return semaphoreOfPassengers;
    }

    /**
     * This method is used to set the semaphore of passengers of the station
     *
     * @param semaphoreOfPassengers the semaphore of passengers of the station
     */
    @Override
    public void setSemaphoreOfPassengers(Semaphore semaphoreOfPassengers) {
        this.semaphoreOfPassengers = semaphoreOfPassengers;
    }

    /**
     * This method is used to get the distance from the current station to the destination station by going through all the tracks of each station and finding the trajectory based on the stations each track connects and with that sum the ammount of stations between the current station and the destination station
     *
     * @param other the destination station
     * @return the distance from the current station to the destination station
     */
    @Override
    public int getDistanceTo(StationADT other) {

        // initialize a queue for the breadth-first search
        Queue<StationADT> queue = new LinkedList<>();

        // initialize a map to store the distances from the start station to each station
        Map<StationADT, Integer> distanceMap = new HashMap<>();

        // add the start station to the queue and set its distance to 0
        queue.add(this);
        distanceMap.put(this, 0);

        // perform a breadth-first search to find the shortest path between the two stations
        while (!queue.isEmpty()) {

            // remove the first station from the queue
            StationADT current = queue.poll();

            // check if we have reached the destination station
            if (current == other) {

                // return the distance from the start station to the destination station
                return distanceMap.get(other);
            }

            // loop through the tracks of the current station
            for (TrackADT track : current.getTracks()) {

                // get the end and start stations of the track
                StationADT end = track.getEndStation();
                StationADT start = track.getStartStation();

                // check if we have already visited the end station
                if (!distanceMap.containsKey(end)) {

                    // if not, add it to the queue and update its distance
                    queue.add(end);
                    distanceMap.put(end, distanceMap.get(current) + 1);
                }

                // check if we have already visited the start station
                if (!distanceMap.containsKey(start)) {

                    // if not, add it to the queue and update its distance
                    queue.add(start);
                    distanceMap.put(start, distanceMap.get(current) + 1);
                }
            }
        }

        // if we reach here, it means that there is no path between the two stations
        return -1;
    }

    /**
     * This method is used to get the capacity status of the station
     *
     * @return the capacity status of the station
     */
    @Override
    public boolean isOverCapacity() {
        return isOverCapacity;
    }

    /**
     * This method is used to set the capacity status of the station
     *
     * @param overCapacity the capacity status of the station
     */
    @Override
    public void setOverCapacity(boolean overCapacity) {
        isOverCapacity = overCapacity;
    }

    /**
     * This method is used to get the string representation of the station
     *
     * @return the string representation of the station
     */
    @Override
    public String toString() {
        return name;
    }
}