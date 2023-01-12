package interfaces;

import java.util.concurrent.Semaphore;

public interface PassengerADT {

    String getName();

    void setName(String name);

    int getAge();

    void setAge(int age);

    CardADT getCard();

    void setCard(CardADT card);

    StationADT getEntryStation();

    void setEntryStation(StationADT entryStation);

    StationADT getDestinationStation();

    void setDestinationStation(StationADT destinationStation);

    StationADT getCurrentStation();

    void setCurrentStation(StationADT currentStation);

    StationADT getExitStation();

    void setExitStation(StationADT exitStation);

    TrainADT getCurrentTrain();

    void setCurrentTrain(TrainADT currentTrain);

    Semaphore getSemaphoreToBoard();

    void setSemaphoreToBoard(Semaphore semaphoreToBoard);

    boolean isOnDestination();

    void setOnDestination(boolean isOnDestination);

    boolean hasInvalidCard();

    void setHasInvalidCard(boolean hasInvalidCard);
}
