package classes.gui;

import interfaces.PassengerADT;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class is used for listing all the passengers
 */
public class PassengersListFrame extends JFrame {
    public PassengersListFrame(List<PassengerADT> passengers, String title) {
        super(title + " - " + passengers.size() + " Passengers");

        JTable table = new JTable(new PassengersTableModel(passengers));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                int row = table.rowAtPoint(event.getPoint());
                int col = table.columnAtPoint(event.getPoint());
                if (row >= 0 && col == 2) {
                    new CardFrame(passengers.get(row).getCard());
                }
            }
        });

        new Timer(1000, e -> {
            scrollPane.updateUI();
            scrollPane.repaint();
            setTitle(title + " - " + passengers.size() + " Passengers");
        }).start();

        add(scrollPane);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() - 50, Toolkit.getDefaultToolkit().getScreenSize().height - getHeight() - 50);

        setVisible(true);
    }
}
