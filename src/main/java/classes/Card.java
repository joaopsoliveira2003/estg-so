package classes;

import interfaces.CardADT;
import interfaces.StationADT;

import java.io.Serializable;

/**
 * This class represents a card
 */
public class Card implements CardADT, Serializable {
    private int id, expirationYear, travels;
    private StationADT firstStation, lastStation;

    /**
     * Constructor of the class Card
     *
     * @param id The id of the card
     * @param expirationYear The expiration year of the card
     * @param firstStation The first station of the card
     * @param lastStation The last station of the card
     * @param travels The number of travels of the card
     */
    public Card(int id, int expirationYear, StationADT firstStation, StationADT lastStation, int travels) {
        this.id = id;
        this.expirationYear = expirationYear;
        this.firstStation = firstStation;
        this.lastStation = lastStation;
        this.travels = travels;
    }

    /**
     * Constructor of the class Card
     *
     * @param id The id of the card
     * @param expirationYear The expiration year of the card
     * @param firstStation The first station of the card
     * @param lastStation The last station of the card
     */
    public Card(int id, int expirationYear, StationADT firstStation, StationADT lastStation) {
        this.id = id;
        this.expirationYear = expirationYear;
        this.firstStation = firstStation;
        this.lastStation = lastStation;
        this.travels = 0;
    }

    /**
     * This method is used to get the id of the card
     *
     * @return the id of the card
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * This method is used to set the id of the card
     *
     * @param id the id of the card
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method is used to get the expiration year of the card
     *
     * @return the balance of the card
     */
    @Override
    public int getExpirationYear() {
        return expirationYear;
    }

    /**
     * This method is used to set the expiration year of the card
     *
     * @param expirationYear the balance of the card
     */
    @Override
    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    /**
     * This method is used to get the first station that is possible to enter with the card
     *
     * @return the first station
     */
    @Override
    public StationADT getFirstStation() {
        return firstStation;
    }

    /**
     * This method is used to set the first station that is possible to enter with the card
     *
     * @param firstStation the first station
     */
    @Override
    public void setFirstStation(StationADT firstStation) {
        this.firstStation = firstStation;
    }

    /**
     * This method is used to get the last station that is possible to leave with the card
     *
     * @return the first station
     */
    @Override
    public StationADT getLastStation() {
        return lastStation;
    }

    /**
     * This method is used to set the last station that is possible to leave with the card
     *
     * @param lastStation the last station
     */
    @Override
    public void setLastStation(StationADT lastStation) {
        this.lastStation = lastStation;
    }

    /**
     * This method is used to get the ammount of travels that the card has
     *
     * @return the ammount of travels
     */
    @Override
    public int getTravels() {
        return travels;
    }

    /**
     * This method is used to set the ammount of travels that the card has
     *
     * @param travels the ammount of travels
     */
    @Override
    public void setTravels(int travels) {
        this.travels = travels;
    }

    /**
     * This method is used to add a specific ammount of travels to the card
     *
     * @param travels the ammount of travels to add
     */
    @Override
    public void addTravels(int travels) {
        this.travels += travels;
    }

    /**
     * This method is used to remove a specific ammount of travels to the card
     *
     * @param travels the ammount of travels to remove
     */
    @Override
    public void removeTravels(int travels) {
        this.travels -= travels;
    }

    /**
     * This method is used to get the string representation of the card
     *
     * @return the string representation of the card
     */
    @Override
    public String toString() {
        return id + "";
    }
}