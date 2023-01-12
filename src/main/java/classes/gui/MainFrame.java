package classes.gui;

import classes.DataPersistence;
import classes.Main;
import interfaces.PassengerADT;
import interfaces.StationADT;
import interfaces.TrackADT;
import interfaces.TrainADT;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * This class is the main menu of the program
 */
public class MainFrame extends JFrame {
    public MainFrame(List<StationADT> stations, List<TrackADT> tracks, List<TrainADT> trains, List<PassengerADT> passengers) {
        super("Main Menu");

        JButton stationsListButton = new JButton("Stations List");
        stationsListButton.addActionListener(e -> {
            new StationsListFrame(stations, "Stations List");
        });

        JButton tracksListButton = new JButton("Tracks List");
        tracksListButton.addActionListener(e -> {
            new TracksListFrame(tracks, "Tracks List");
        });

        JButton trainsListButton = new JButton("Trains List");
        trainsListButton.addActionListener(e -> {
            new TrainsListFrame(trains, "Trains List");
        });

        JButton passengersListButton = new JButton("Passengers List");
        passengersListButton.addActionListener(e -> {
            new PassengersListFrame(passengers, "Passengers List");
        });

        JButton startSimulatorButton = new JButton("Start Simulator");
        startSimulatorButton.setEnabled(false);
        JButton stopSimulatorButton = new JButton("Stop Simulator");
        stopSimulatorButton.setEnabled(false);

        startSimulatorButton.addActionListener(e -> {
            Main.running.set(true);
            startSimulatorButton.setEnabled(false);
            stopSimulatorButton.setEnabled(true);
        });

        stopSimulatorButton.addActionListener(e -> {
            Main.running.set(false);
            Main.stopThreads();
            stopSimulatorButton.setEnabled(false);
        });

        JButton loadDataButton = new JButton("Load / Generate Data");
        JButton saveDataButton = new JButton("Save Data");
        loadDataButton.addActionListener(e -> {
            // try to load data otherwise generate it or exit
            try {
                DataPersistence.loadData(stations, tracks, trains, passengers);
            } catch (IOException error) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to generate data?", "Data not found", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    DataPersistence.generateData(stations, tracks, trains, passengers);
                } else {
                    JOptionPane.showMessageDialog(null, "The program will be closed");
                    System.exit(0);
                }
            } finally {
                startSimulatorButton.setEnabled(true);
                loadDataButton.setEnabled(false);
            }
        });

        saveDataButton.addActionListener(e -> {
            DataPersistence.saveData(stations, tracks, trains, passengers);
        });

        setLayout(new GridLayout(4, 2));

        add(stationsListButton);
        add(tracksListButton);
        add(trainsListButton);
        add(passengersListButton);
        add(startSimulatorButton);
        add(stopSimulatorButton);
        add(loadDataButton);
        add(saveDataButton);


        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }
}
