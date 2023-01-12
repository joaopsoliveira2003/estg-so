package classes.gui;

import interfaces.ScheduleADT;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class is the frame for the schedules list
 */
public class SchedulesListFrame extends JFrame {
    public SchedulesListFrame(List<ScheduleADT> schedules, String title) {
        super(title + " - " + schedules.size() + " Schedules");

        JTable table = new JTable(new SchedulesTableModel(schedules));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        new Timer(1000, event -> {
            scrollPane.updateUI();
            scrollPane.repaint();
            setTitle(title + " - " + schedules.size() + " Schedules");
        }).start();

        add(scrollPane);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(50, Toolkit.getDefaultToolkit().getScreenSize().height - getHeight() - 50);

        setVisible(true);
    }
}
