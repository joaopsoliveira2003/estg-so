package classes.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for the conflicts logs
 */
public class ConflictsLogFrame extends JFrame {
    private static final JTextArea conflictsArea = new JTextArea();
    private static final JScrollPane scrollPane = new JScrollPane(conflictsArea);

    public static synchronized void log(String text) {
        conflictsArea.append(" [" + String.format("%tT", System.currentTimeMillis()) + "]: " + text + "\n");
    }

    public ConflictsLogFrame() {
        super("Conflicts Log");

        conflictsArea.setEditable(false);
        conflictsArea.setLineWrap(true);
        conflictsArea.setWrapStyleWord(true);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(scrollPane);

        pack();
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, 50);

        setVisible(true);
    }
}
