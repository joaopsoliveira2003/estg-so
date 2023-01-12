package interfaces;

import java.util.List;
import java.util.concurrent.Semaphore;

public interface TrainADT {

    String getName();

    void setName(String name);

    int getMaxPassengers();

    void setMaxPassengers(int maxPassengers);

    List<PassengerADT> getPassengers();

    void setPassengers(List<PassengerADT> passengers);

    List<ScheduleADT> getSchedules();

    void setSchedules(List<ScheduleADT> schedules);

    StationADT getCurrentStation();

    void setCurrentStation(StationADT currentStation);

    TrackADT getCurrentTrack();

    void setCurrentTrack(TrackADT currentTrack);

    boolean isMoving();

    void setMoving(boolean moving);

    boolean isConflictuous();

    void setConflictuous(boolean conflictuous);

    Semaphore getSemaphoreToDepart();

    void setSemaphoreToDepart(Semaphore semaphoreToDepart);

    Semaphore getSemaphoreOfSchedules();

    void setSemaphoreOfSchedules(Semaphore semaphoreOfSchedules);

    Semaphore getSemaphoreOfPassengers();

    void setSemaphoreOfPassengers(Semaphore semaphoreOfPassengers);

    boolean isFinished();

    void setFinished(boolean finished);

    boolean isOverCrowded();

    void setOverCrowded(boolean overCrowded);


}
