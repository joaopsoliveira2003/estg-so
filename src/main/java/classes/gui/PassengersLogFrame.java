package classes.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for showing passengers logs
 */
public class PassengersLogFrame extends JFrame {
    private static final JTextArea logsArea = new JTextArea();
    private static final JScrollPane scrollPane = new JScrollPane(logsArea);

    public static synchronized void log(String text) {
        logsArea.append(" [" + String.format("%tT", System.currentTimeMillis()) + "]: " + text + "\n");
    }

    public PassengersLogFrame() {
        super("Passengers Log");

        logsArea.setEditable(false);
        logsArea.setLineWrap(true);
        logsArea.setWrapStyleWord(true);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(scrollPane);

        pack();
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() - 50, 50);

        setVisible(true);
    }
}
