package classes.gui;

import interfaces.StationADT;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * This class is the frame for the stations list
 */
public class StationsListFrame extends JFrame {
    public StationsListFrame(List<StationADT> stations, String title) {
        super(title + " - " + stations.size() + " Stations");

        JTable table = new JTable(new StationsTableModel(stations));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                int row = table.rowAtPoint(event.getPoint());
                int col = table.columnAtPoint(event.getPoint());
                if (row >= 0 && col == 1) {
                    new PassengersListFrame(stations.get(row).getPassengers(), "Passengers at the Station " + stations.get(row).getName());
                } else if (row >= 0 && col == 2) {
                    new TrainsListFrame(stations.get(row).getCurrentTrains(), "Trains at the Station " + stations.get(row).getName());
                } else if (row >= 0 && col == 3) {
                    new TracksListFrame(stations.get(row).getTracks(), "Tracks of the Station " + stations.get(row).getName());
                }
            }
        });

        new Timer(1000, event -> {
            scrollPane.updateUI();
            scrollPane.repaint();
            setTitle(title + " - " + stations.size() + " Stations");
        }).start();

        add(scrollPane);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() - 50, Toolkit.getDefaultToolkit().getScreenSize().height - getHeight() - 50);

        setVisible(true);
    }
}
