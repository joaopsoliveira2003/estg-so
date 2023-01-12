package classes.gui;

import interfaces.ScheduleADT;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This class is the table model for the schedules list
 */
public class SchedulesTableModel extends AbstractTableModel {
    private final List<ScheduleADT> schedules;
    private final String[] columnNames = {"Station", "Time to Arrive", "Time to Depart", "Direction"};

    public SchedulesTableModel(List<ScheduleADT> schedules) {
        this.schedules = schedules;
    }

    @Override
    public int getRowCount() {
        return schedules.size();
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
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex >= 0 && (columnIndex == 1 || columnIndex == 2);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ScheduleADT schedule = schedules.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return schedule.getStation().getName();
            case 1:
                return schedule.getTimeToArrive();
            case 2:
                return schedule.getTimeToDepart();
            case 3:
                return schedule.getDirection();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ScheduleADT schedule = schedules.get(rowIndex);
        try {
            switch (columnIndex) {
                case 1:
                    schedule.setTimeToArrive(Integer.parseInt((String) aValue));
                    break;
                case 2:
                    schedule.setTimeToDepart(Integer.parseInt((String) aValue));
                    break;
            }
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
