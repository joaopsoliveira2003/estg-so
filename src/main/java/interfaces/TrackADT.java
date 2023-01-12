package interfaces;

import java.util.concurrent.Semaphore;

public interface TrackADT {

    String getName();

    void setName(String name);

    double getLength();

    void setLength(double length);

    StationADT getStartStation();

    void setStartStation(StationADT startStation);

    StationADT getEndStation();

    void setEndStation(StationADT endStation);

    Semaphore getSemaphoreOfTrains();

    void setSemaphoreOfTrains(Semaphore semaphoreOfTrains);

    TrainADT getTrain();

    void setTrain(TrainADT train);

    boolean isTrainGoingToStation(StationADT station);
}
