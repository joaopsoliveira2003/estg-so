package interfaces;

import java.util.List;
import java.util.concurrent.Semaphore;

public interface StationADT {

    String getName();

    void setName(String name);

    List<TrackADT> getTracks();

    void setTracks(List<TrackADT> tracks);

    List<PassengerADT> getPassengers();

    void setPassengers(List<PassengerADT> passengers);

    List<TrainADT> getCurrentTrains();

    void setCurrentTrains(List<TrainADT> currentTrains);

    int getMaxTrains();

    void setMaxTrains(int maxTrainCapacity);

    Semaphore getSemaphoreOfTrains();

    void setSemaphoreOfTrains(Semaphore semaphoreOfTrains);

    Semaphore getSemaphoreOfPassengers();

    void setSemaphoreOfPassengers(Semaphore semaphoreOfPassengers);

    int getDistanceTo(StationADT other);

    boolean isOverCapacity();

    void setOverCapacity(boolean overCapacity);
}
