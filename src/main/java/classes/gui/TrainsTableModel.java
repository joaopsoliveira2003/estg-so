package classes.gui;

import interfaces.TrainADT;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class is the table model for the trains list
 */
public class TrainsTableModel extends AbstractTableModel {
    private final List<TrainADT> trains;
    private final String[] columnNames = { "Name", "Max Passengers", "Passengers", "Schedules", "Current Station", "Current Track", "Is Moving", "Is Conflictuous", "Is Overcrowded" };

    public TrainsTableModel(List<TrainADT> trains) {
        this.trains = trains;
    }

    @Override
    public int getRowCount() {
        return trains.size();
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
                return Boolean.class;
            case 7:
                return Boolean.class;
            case 8:
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
        TrainADT train = trains.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return train.getName();
            case 1:
                return train.getMaxPassengers() + "";
            case 2:
                return train.getPassengers().size() + "";
            case 3:
                return train.getSchedules().size() + "";
            case 4:
                if (train.getCurrentStation() != null) {
                    return train.getCurrentStation().getName();
                } else {
                    return "None";
                }
            case 5:
                if (train.getCurrentTrack() != null) {
                    return train.getCurrentTrack().getName();
                } else {
                    return "None";
                }
            case 6:
                return train.isMoving();
            case 7:
                return train.isConflictuous();
            case 8:
                return train.isOverCrowded();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // not editable
    }
}
