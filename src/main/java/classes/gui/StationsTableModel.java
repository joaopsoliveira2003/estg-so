package classes.gui;

import interfaces.StationADT;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class is the table model for the stations list
 */
public class StationsTableModel extends AbstractTableModel {
    private final List<StationADT> stations;
    private final String[] columnNames = {"Name", "Passengers", "Trains", "Tracks", "Max Trains", "Full"};

    public StationsTableModel(List<StationADT> stations) {
        this.stations = stations;
    }

    @Override
    public int getRowCount() {
        return stations.size();
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
        StationADT station = stations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return station.getName();
            case 1:
                return station.getPassengers().size() + "";
            case 2:
                return station.getCurrentTrains().size() + "";
            case 3:
                return station.getTracks().size() + "";
            case 4:
                return station.getMaxTrains() + "";
            case 5:
                return station.getCurrentTrains().size() >= station.getMaxTrains();
            default:
                return null;
        }
    }
}
