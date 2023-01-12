package classes.gui;

import interfaces.TrackADT;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class is the frame for the tracks list
 */
public class TracksListFrame extends JFrame {
    public TracksListFrame(List<TrackADT> tracks, String title) {
        super(title + " - " + tracks.size() + " Tracks");

        JTable table = new JTable(new TracksTableModel(tracks));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        new Timer(1000, event -> {
            scrollPane.updateUI();
            scrollPane.repaint();
            setTitle(title + " - " + tracks.size() + " Tracks");
        }).start();

        add(scrollPane);

        pack();
        setMinimumSize(new Dimension(400, 150));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() - 50, Toolkit.getDefaultToolkit().getScreenSize().height - getHeight() - 50);

        setVisible(true);
    }
}
