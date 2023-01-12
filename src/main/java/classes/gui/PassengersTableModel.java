package classes.gui;

import interfaces.PassengerADT;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class is the table model for the passengers list
 */
public class PassengersTableModel extends AbstractTableModel {
    private final List<PassengerADT> passengers;
    private final String[] columnNames = { "Name", "Age", "Card", "Entry Station", "Destination Station", "Current Station", "Exit Station", "Train", "Is On Destination", "Has Invalid Card", "Waiting to Board" };

    public PassengersTableModel(List<PassengerADT> passengers) {
        this.passengers = passengers;
    }

    @Override
    public int getRowCount() {
        return passengers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            case 8:
                return Boolean.class;
            case 9:
                return Boolean.class;
            case 10:
                return Boolean.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PassengerADT passenger = passengers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return passenger.getName();
            case 1:
                return passenger.getAge() + "";
            case 2:
                if (passenger.getCard() == null) {
                    return "None";
                } else {
                    return "Card " + passenger.getCard().getId();
                }
            case 3:
                if (passenger.getEntryStation() == null) {
                    return "None";
                } else {
                    return passenger.getEntryStation().getName();
                }
            case 4:
                if (passenger.getDestinationStation() == null) {
                    return "None";
                } else {
                    return passenger.getDestinationStation().getName();
                }
            case 5:
                if (passenger.getCurrentStation() == null) {
                    return "None";
                } else {
                    return passenger.getCurrentStation().getName();
                }
            case 6:
                if (passenger.getExitStation() == null) {
                    return "None";
                } else {
                    return passenger.getExitStation().getName();
                }
            case 7:
                if (passenger.getCurrentTrain() == null) {
                    return "None";
                } else {
                    return passenger.getCurrentTrain().getName();
                }
            case 8:
                return passenger.isOnDestination();
            case 9:
                return passenger.hasInvalidCard();
            case 10:
                return passenger.getSemaphoreToBoard().hasQueuedThreads();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // not editable
    }
}

