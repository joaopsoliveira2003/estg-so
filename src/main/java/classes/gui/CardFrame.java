package classes.gui;

import interfaces.CardADT;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for showing details of a card
 */
public class CardFrame extends JFrame {
    public CardFrame(CardADT card) {
        super("Card " + card.getId());

        JTextField idField = new JTextField();
        idField.setEditable(false);
        idField.setHorizontalAlignment(JTextField.CENTER);
        idField.setText("ID: " + card.getId());

        JTextField expirationYearField = new JTextField();
        expirationYearField.setEditable(false);
        expirationYearField.setHorizontalAlignment(JTextField.CENTER);
        expirationYearField.setText("Expiration Year: " + card.getExpirationYear());

        JTextField firstStationField = new JTextField();
        firstStationField.setEditable(false);
        firstStationField.setHorizontalAlignment(JTextField.CENTER);
        firstStationField.setText("First Station: " + card.getFirstStation().getName());

        JTextField lastStationField = new JTextField();
        lastStationField.setEditable(false);
        lastStationField.setHorizontalAlignment(JTextField.CENTER);
        lastStationField.setText("Last Station: " + card.getLastStation().getName());

        JTextField travelsField = new JTextField();
        travelsField.setEditable(false);
        travelsField.setHorizontalAlignment(JTextField.CENTER);
        travelsField.setText("Travels: " + card.getTravels());

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(idField);
        add(expirationYearField);
        add(firstStationField);
        add(lastStationField);
        add(travelsField);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() - 50, Toolkit.getDefaultToolkit().getScreenSize().height - getHeight() - 50);

        setVisible(true);

        new Timer(1000, e -> {
            travelsField.setText("Travels: " + card.getTravels());
        }).start();
    }
}
