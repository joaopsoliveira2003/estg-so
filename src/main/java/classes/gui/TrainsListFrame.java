package classes.gui;

import interfaces.TrainADT;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class is the frame for the trains list
 */
public class TrainsListFrame extends JFrame {
    public TrainsListFrame(List<TrainADT> trains, String title) {
        super(title + " - " + trains.size() + " Trains");

        JTable table = new JTable(new TrainsTableModel(trains));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        //listen for click on schedules and open a schedules list for this train
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 2) {
                    new PassengersListFrame(trains.get(row).getPassengers(), "Passengers of the Train " + trains.get(row).getName());
                }
                if (row >= 0 && col == 3) {
                    new SchedulesListFrame(trains.get(row).getSchedules(), "Schedules for Train " + trains.get(row).getName());
                }
            }
        });


        new Timer(1000, event -> {
            scrollPane.updateUI();
            scrollPane.repaint();
            setTitle(title + " - " + trains.size() + " Trains");
        }).start();

        add(scrollPane);

        pack();
        setMinimumSize(new Dimension(400, 150));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() - 50, Toolkit.getDefaultToolkit().getScreenSize().height - getHeight() - 50);

        setVisible(true);
    }
}
