package interfaces;

public interface CardADT {

    int getId();

    void setId(int id);

    int getExpirationYear();

    void setExpirationYear(int expirationYear);

    StationADT getFirstStation();

    void setFirstStation(StationADT firstStation);

    StationADT getLastStation();

    void setLastStation(StationADT lastStation);

    int getTravels();

    void setTravels(int travels);

    void addTravels(int travels);

    void removeTravels(int travels);
}
