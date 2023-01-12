package interfaces;

import enumerations.Direction;

public interface ScheduleADT {

    StationADT getStation();

    void setStation(StationADT station);

    double getTimeToArrive();

    void setTimeToArrive(double timeToArrive);

    double getTimeToDepart();

    void setTimeToDepart(double timeToDepart);

    Direction getDirection();

    void setDirection(Direction direction);
}
