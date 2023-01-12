package classes.gui;

import interfaces.TrackADT;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * This class is the table model for the tracks list
 */
public class TracksTableModel extends AbstractTableModel {
    private final List<TrackADT> tracks;
    private final String[] columnNames = {"Name", "Length", "Start Station", "End Station", "Train", "Competition"};

    public TracksTableModel(List<TrackADT> tracks) {
        this.tracks = tracks;
    }

    @Override
    public int getRowCount() {
        return tracks.size();
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
        TrackADT track = tracks.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return track.getName();
            case 1:
                return track.getLength() + "";
            case 2:
                if (track.getStartStation() != null) {
                    return track.getStartStation().getName();
                } else {
                    return "None";
                }
            case 3:
                if (track.getEndStation() != null) {
                    return track.getEndStation().getName();
                } else {
                    return "None";
                }
            case 4:
                if (track.getTrain() != null) {
                    return track.getTrain().getName();
                } else {
                    return "None";
                }
            case 5:
                return track.getSemaphoreOfTrains().hasQueuedThreads();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // not editable
    }
}
