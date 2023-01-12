package classes;

import enumerations.Direction;
import interfaces.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for the data
 */
public class DataPersistence {
    private static String BASE_PATH;

    /**
     * This method is used to generate random passengers with random cards
     *
     * @param passengers list of passengers where they will be added
     * @param stations list of stations needed to generate random passsengers
     * @param numberOfPassengers number of passengers to be generated
     */
    public static void generatePassengers(List<PassengerADT> passengers, List<StationADT> stations, int numberOfPassengers) {
        for (int i = 1; i <= numberOfPassengers; i++) {
            // there must be 5 stations of diference in terms of index between the start and end stations
            int startStationIndex = new Random().nextInt(stations.size());
            int endStationIndex = new Random().nextInt(stations.size());
            while (Math.abs(startStationIndex - endStationIndex) < 5) {
                endStationIndex = new Random().nextInt(stations.size());
            }
            StationADT entryStation = stations.get(startStationIndex);
            StationADT exitStation = stations.get(endStationIndex);
            CardADT card = new Card(new Random().nextInt(10000), 2023, entryStation, exitStation, new Random().nextInt(10));
            PassengerADT passenger = new Passenger("Random " + i, new Random().nextInt(100), card, entryStation, exitStation);
            passengers.add(passenger);
        }
    }

    /**
     * This method is used to generate the data
     *
     * @param stations the list of stations
     * @param tracks the list of tracks
     * @param trains the list of trains
     * @param passengers the list of passengers
     */
    public static void generateData(List<StationADT> stations, List<TrackADT> tracks, List<TrainADT> trains, List<PassengerADT> passengers) {
        // S.BENTO
        StationADT station1 = new Station("S.Bento", 3);

        // CAMPANHÃ AND STATIONS IN COMMOM TO BRAGA, GUIMARÃES AND MARCO LINES

        StationADT station2 = new Station("Campanhã", 6);
        TrackADT track1 = new Track("S.Bento - Campanhã", 0.1, station1, station2);

        StationADT station3 = new Station("Rio Tinto", 3);
        TrackADT track2 = new Track("Campanhã  - Rio Tinto", 0.2, station2, station3);

        StationADT station4 = new Station("Ermesinde", 4);
        TrackADT track3 = new Track("Rio Tinto - Ermesinde", 0.1, station3, station4);

        // TO MARCO

        StationADT station5 = new Station("Recarei", 3);
        TrackADT track4 = new Track("Ermesinde - Recarei", 0.2, station4, station5);

        StationADT station6 = new Station("Cête", 3);
        TrackADT track5 = new Track("Recarei - Cête", 0.1, station5, station6);

        StationADT station7 = new Station("Penafiel", 3);
        TrackADT track6 = new Track("Cête - Penafiel", 0.2, station6, station7);

        StationADT station8 = new Station("Marco", 6);
        TrackADT track7 = new Track("Penafiel - Marco", 0.1, station7, station8);

        // LINES IN COMMOM TO GUIMARÃES AND BRAGA

        StationADT station9 = new Station("Sra. das Dores", 3);
        TrackADT track8 = new Track("Ermesinde - Sra. das Dores", 0.2, station4, station9);

        StationADT station10 = new Station("Trofa", 3);
        TrackADT track9 = new Track("Sra. das Dores - Trofa", 0.1, station9, station10);

        // TO GUIMARÃES

        StationADT station11 = new Station("Vila das Aves", 3);
        TrackADT track10 = new Track("Trofa - Vila das Aves", 0.2, station10, station11);

        StationADT station12 = new Station("Guimarães", 3);
        TrackADT track11 = new Track("Vila das Aves - Guimarães", 0.1, station11, station12);

        // TO BRAGA

        StationADT station13 = new Station("Nine", 3);
        TrackADT track12 = new Track("Trofa - Nine", 0.2, station10, station13);

        StationADT station14 = new Station("Braga", 6);
        TrackADT track13 = new Track("Nine - Braga", 0.1, station13, station14);

        // LINE OF AVEIRO

        StationADT station15 = new Station("Gaia", 3);
        TrackADT track14 = new Track("Campanhã  - Gaia", 0.2, station2, station15);

        StationADT station16 = new Station("Espinho", 4);
        TrackADT track15 = new Track("Gaia  - Espinho", 0.1, station15, station16);

        StationADT station17 = new Station("Ovar", 3);
        TrackADT track16 = new Track("Espinho - Ovar", 0.2, station16, station17);

        StationADT station18 = new Station("Aveiro", 6);
        TrackADT track17 = new Track("Ovar - Aveiro", 0.1, station17, station18);


        stations.addAll(Arrays.asList(station1, station2, station3, station4, station5, station6, station7, station8,
                station9, station10, station11, station12, station13, station14, station15, station16, station17, station18));

        tracks.addAll(Arrays.asList(track1, track2, track3, track4, track5, track6, track7, track8, track9, track10,
                track11, track12, track13, track14, track15, track16, track17));

        //ALL TRAINS AND SCHEDULES
        TrainADT train1 = new Train("São Bento - Aveiro", 25, station1);
        ScheduleADT schedule1 = new Schedule(station2, 0, 0.1, Direction.FORWARD);
        ScheduleADT schedule2 = new Schedule(station15, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule3 = new Schedule(station16, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule4 = new Schedule(station17, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule5 = new Schedule(station18, 0.1, 0.1, Direction.FORWARD);

        train1.getSchedules().addAll(Arrays.asList(schedule1, schedule2, schedule3, schedule4, schedule5));

        TrainADT train2 = new Train("Aveiro - São Bento", 25, station18);
        ScheduleADT schedule6 = new Schedule(station17, 0, 0.2, Direction.BACKWARD);
        ScheduleADT schedule7 = new Schedule(station16, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule8 = new Schedule(station15, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule9 = new Schedule(station2, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule10 = new Schedule(station1, 0.1, 0.1, Direction.BACKWARD);

        train2.getSchedules().addAll(Arrays.asList(schedule6, schedule7, schedule8, schedule9, schedule10));

        TrainADT train3 = new Train("São Bento - Braga", 25, station1);
        ScheduleADT schedule11 = new Schedule(station2, 0, 0.1, Direction.FORWARD);
        ScheduleADT schedule12 = new Schedule(station3, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule13 = new Schedule(station4, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule14 = new Schedule(station9, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule15 = new Schedule(station10, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule16 = new Schedule(station13, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule17 = new Schedule(station14, 0.1, 0.1, Direction.FORWARD);

        train3.getSchedules().addAll(Arrays.asList(schedule11, schedule12, schedule13, schedule14, schedule15,
                schedule16, schedule17));

        TrainADT train4 = new Train("Braga - São Bento", 25, station14);
        ScheduleADT schedule18 = new Schedule(station13, 0, 0.2, Direction.BACKWARD);
        ScheduleADT schedule19 = new Schedule(station10, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule20 = new Schedule(station9, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule21 = new Schedule(station4, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule22 = new Schedule(station3, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule23 = new Schedule(station2, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule24 = new Schedule(station1, 0.1, 0.1, Direction.BACKWARD);

        train4.getSchedules().addAll(Arrays.asList(schedule18, schedule19, schedule20, schedule21, schedule22,
                schedule23, schedule24));

        TrainADT train5 = new Train("Guimarães - Marco", 25, station12);
        ScheduleADT schedule25 = new Schedule(station11, 0, 0.1, Direction.BACKWARD);
        ScheduleADT schedule26 = new Schedule(station10, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule27 = new Schedule(station9, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule28 = new Schedule(station4, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule29 = new Schedule(station5, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule30 = new Schedule(station6, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule31 = new Schedule(station7, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule32 = new Schedule(station8, 0.1, 0.1, Direction.FORWARD);

        train5.getSchedules().addAll(Arrays.asList(schedule25, schedule26, schedule27, schedule28, schedule29,
                schedule30, schedule31, schedule32));

        TrainADT train6 = new Train("Marco - Guimarães", 25, station8);
        ScheduleADT schedule33 = new Schedule(station7, 0, 0.2, Direction.BACKWARD);
        ScheduleADT schedule34 = new Schedule(station6, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule35 = new Schedule(station5, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule36 = new Schedule(station4, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule37 = new Schedule(station9, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule38 = new Schedule(station10, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule39 = new Schedule(station11, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule40 = new Schedule(station12, 0.1, 0.1, Direction.FORWARD);

        train6.getSchedules().addAll(Arrays.asList(schedule33, schedule34, schedule35, schedule36, schedule37,
                schedule38, schedule39, schedule40));

        TrainADT train7 = new Train("Braga - Aveiro", 25, station14);
        ScheduleADT schedule41 = new Schedule(station13, 0, 0.1, Direction.BACKWARD);
        ScheduleADT schedule42 = new Schedule(station10, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule43 = new Schedule(station9, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule44 = new Schedule(station4, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule45 = new Schedule(station3, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule46 = new Schedule(station2, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule47 = new Schedule(station15, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule48 = new Schedule(station16, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule49 = new Schedule(station17, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule50 = new Schedule(station18, 0.1, 0.1, Direction.FORWARD);

        train7.getSchedules().addAll(Arrays.asList(schedule41, schedule42, schedule43, schedule44, schedule45,
                schedule46, schedule47, schedule48, schedule49, schedule50));

        TrainADT train8 = new Train("Aveiro - Braga", 25, station18);
        ScheduleADT schedule51 = new Schedule(station17, 0, 0.2, Direction.BACKWARD);
        ScheduleADT schedule52 = new Schedule(station16, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule53 = new Schedule(station15, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule54 = new Schedule(station2, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule55 = new Schedule(station3, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule56 = new Schedule(station4, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule57 = new Schedule(station9, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule58 = new Schedule(station10, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule59 = new Schedule(station13, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule60 = new Schedule(station14, 0.1, 0.1, Direction.FORWARD);

        train8.getSchedules().addAll(Arrays.asList(schedule51, schedule52, schedule53, schedule54, schedule55,
                schedule56, schedule57, schedule58, schedule59, schedule60));

        TrainADT train9 = new Train("Campanhã - Aveiro", 25, station2);
        ScheduleADT schedule61 = new Schedule(station15, 0, 0.1, Direction.FORWARD);
        ScheduleADT schedule62 = new Schedule(station16, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule63 = new Schedule(station17, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule64 = new Schedule(station18, 0.1, 0.1, Direction.FORWARD);

        train9.getSchedules().addAll(Arrays.asList(schedule61, schedule62, schedule63, schedule64));

        TrainADT train10 = new Train("Aveiro - Campanhã", 25, station18);
        ScheduleADT schedule65 = new Schedule(station17, 0, 0.2, Direction.BACKWARD);
        ScheduleADT schedule66 = new Schedule(station16, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule67 = new Schedule(station15, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule68 = new Schedule(station2, 0.1, 0.1, Direction.BACKWARD);

        train10.getSchedules().addAll(Arrays.asList(schedule65, schedule66, schedule67, schedule68));

        TrainADT train11 = new Train("Trofa - Ovar", 25, station10);
        ScheduleADT schedule69 = new Schedule(station9, 0, 0.1, Direction.BACKWARD);
        ScheduleADT schedule70 = new Schedule(station4, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule71 = new Schedule(station3, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule72 = new Schedule(station2, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule73 = new Schedule(station15, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule74 = new Schedule(station16, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule75 = new Schedule(station17, 0.1, 0.1, Direction.FORWARD);

        train11.getSchedules().addAll(Arrays.asList(schedule69, schedule70, schedule71, schedule72, schedule73,
                schedule74, schedule75));

        TrainADT train12 = new Train("Ovar - Trofa", 25, station17);
        ScheduleADT schedule76 = new Schedule(station16, 0, 0.2, Direction.BACKWARD);
        ScheduleADT schedule77 = new Schedule(station15, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule78 = new Schedule(station2, 0.1, 0.1, Direction.BACKWARD);
        ScheduleADT schedule79 = new Schedule(station3, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule80 = new Schedule(station4, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule81 = new Schedule(station9, 0.1, 0.1, Direction.FORWARD);
        ScheduleADT schedule82 = new Schedule(station10, 0.1, 0.1, Direction.FORWARD);

        train12.getSchedules().addAll(Arrays.asList(schedule76, schedule77, schedule78, schedule79, schedule80,
                schedule81, schedule82));

        trains.addAll(Arrays.asList(train1, train2, train3, train4, train5, train6, train7, train8, train9, train10,
                train11, train12));

        //ALL CARDS AND PASSENGERS
        CardADT card1 = new Card(1, 2023, station2, station6, 5);
        PassengerADT passenger1 = new Passenger("Rosa Limões", 34, card1, station2, station6);

        CardADT card2 = new Card(2, 2024, station18, station15);
        PassengerADT passenger2 = new Passenger("Tomas Silva", 14, card2, station18, station15);

        CardADT card3 = new Card(3, 2023, station11, station6,1);
        PassengerADT passenger3 = new Passenger("Felicidade Barros", 22, card3, station11, station6);

        CardADT card4 = new Card(4, 2025, station17, station16,2);
        PassengerADT passenger4 = new Passenger("Antonio Pereira", 38, card4, station17, station16);

        CardADT card5 = new Card(5, 2023, station15, station13,1);
        PassengerADT passenger5 = new Passenger("Romeu Moreira", 25, card5, station15, station13);

        CardADT card6 = new Card(6, 2024, station8, station1,2);
        PassengerADT passenger6 = new Passenger("Tristeza Girassol", 65, card6, station8, station1);

        CardADT card7 = new Card(7, 2025, station1, station8,1);
        PassengerADT passenger7 = new Passenger("Maria João", 45, card7, station1, station8);

        CardADT card8 = new Card(8, 2023, station16, station3, 1);
        PassengerADT passenger8 = new Passenger("João Pedro", 15, card8, station16, station3);

        CardADT card9 = new Card(9, 2024, station18, station1, 5);
        PassengerADT passenger9 = new Passenger("Rosinha Felicidade", 28, card9, station18, station1);

        CardADT card10 = new Card(10, 2023, station2, station12, 1);
        PassengerADT passenger10 = new Passenger("Luis Aloado", 37, card10, station2, station12);

        CardADT card11 = new Card(11, 2025, station2, station13, 1);
        PassengerADT passenger11 = new Passenger("Jaquim Benardino", 49, card11, station2, station13);

        CardADT card12 = new Card(12, 2023, station3, station5, 3);
        PassengerADT passenger12 = new Passenger("Felizberta Pereira", 57, card12, station3, station5);

        CardADT card13 = new Card(13, 2024, station2, station16, 1);
        PassengerADT passenger13 = new Passenger("Roberto Carneiro", 12, card13, station2, station16);

        CardADT card14 = new Card(14, 2023, station6, station4, 1);
        PassengerADT passenger14 = new Passenger("Jonas Pistolas", 31, card14, station6, station4);

        CardADT card15 = new Card(15, 2025, station5, station17, 1);
        PassengerADT passenger15 = new Passenger("Floribela Flor", 19, card15, station5, station17);

        CardADT card16 = new Card(16, 2023, station6, station1, 1);
        PassengerADT passenger16 = new Passenger("Generosa Farias", 69, card16, station6, station1);

        CardADT card17 = new Card(17, 2024, station1, station3);
        PassengerADT passenger17 = new Passenger("Izabel Portugal", 82, card17, station1, station3);

        CardADT card18 = new Card(18, 2023, station15, station6, 4);
        PassengerADT passenger18 = new Passenger("Janice Roma", 26, card18, station15, station6);

        CardADT card19 = new Card(19, 2025, station8, station15);
        PassengerADT passenger19 = new Passenger("Ótima Dantas", 22, card19, station8, station15);

        CardADT card20 = new Card(20, 2023, station13, station3, 2);
        PassengerADT passenger20 = new Passenger("Ivete Chaminé", 20, card20, station13, station3);

        CardADT card21 = new Card(21, 2023, station2, station17, 2);
        PassengerADT passenger21 = new Passenger("Davi Rodrigues", 21, card21, station2, station17);

        CardADT card22 = new Card(22, 2023, station11, station16, 3);
        PassengerADT passenger22 = new Passenger("Isabel Cruz", 17, card22, station11, station16);

        CardADT card23 = new Card(23, 2023, station8, station1, 8);
        PassengerADT passenger23 = new Passenger("Evelyn Jesus", 9, card23, station8, station1);

        CardADT card24 = new Card(24, 2023, station14, station7, 4);
        PassengerADT passenger24 = new Passenger("Fernando Moreira", 6, card24, station14, station7);

        CardADT card25 = new Card(25, 2023, station15, station8, 1);
        PassengerADT passenger25 = new Passenger("Larissa Cardoso", 86, card25, station15, station8);

        CardADT card26 = new Card(26, 2023, station10, station8, 1);
        PassengerADT passenger26 = new Passenger("Pedro Conceição", 92, card26, station10, station8);

        CardADT card27 = new Card(27, 2023, station10, station1);
        PassengerADT passenger27 = new Passenger("Agatha Oliveira", 43, card27, station10, station1);

        CardADT card28 = new Card(28, 2023, station16, station1, 2);
        PassengerADT passenger28 = new Passenger("Vinicius Freitas", 78, card28, station16, station1);

        CardADT card29 = new Card(29, 2023, station1, station11, 3);
        PassengerADT passenger29 = new Passenger("Natália Carvalho", 52, card29, station1, station11);

        CardADT card30 = new Card(30, 2023, station2, station18, 1);
        PassengerADT passenger30 = new Passenger("Nina Gonçalves", 20, card30, station2, station18);

        passengers.addAll(Arrays.asList(passenger1, passenger2, passenger3, passenger4, passenger5, passenger6,
                passenger7, passenger8, passenger9, passenger10, passenger11, passenger12, passenger13, passenger14,
                passenger15, passenger16, passenger17, passenger18, passenger19, passenger20, passenger21, passenger22,
                passenger23, passenger24, passenger25, passenger26, passenger27, passenger28, passenger29, passenger30));

        // generate random passengers
        generatePassengers(passengers, stations, 520);
    }

    /**
     * This method is used to choose a path to load/save the data
     * @return the path
     * @throws IOException if no path is chosen
     */
    public static String choosePath() throws IOException {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Choose directory");
        chooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //detect if the operating system is windows or linux
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                return chooser.getSelectedFile().getAbsolutePath() +"\\";
            } else {
                return chooser.getSelectedFile().getAbsolutePath() +"/";
            }
        } else {
            throw new IOException("No directory selected");
        }
    }

    /**
     * This method is used to save the data
     *
     * @param stations the list of stations
     * @param tracks the list of tracks
     * @param trains the list of trains
     * @param passengers the list of passengers
     */
    public static void saveData(List<StationADT> stations, List<TrackADT> tracks, List<TrainADT> trains, List<PassengerADT> passengers) {
        try {
            if (BASE_PATH == null) {
                BASE_PATH = choosePath();
            } else {
                int option = JOptionPane.showConfirmDialog(null,"Do you want to save in the same folder?","Save", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    BASE_PATH = choosePath();
                } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                    return;
                }
            }
            // save data in the chosen path for stations
            FileOutputStream stationsStream = new FileOutputStream(BASE_PATH +"stations.bin");
            ObjectOutputStream stationsObjectStream = new ObjectOutputStream(stationsStream);
            stationsObjectStream.writeObject(stations);
            stationsObjectStream.close();

            // save data in the chosen path for tracks
            FileOutputStream tracksStream = new FileOutputStream(BASE_PATH +"tracks.bin");
            ObjectOutputStream tracksObjectStream = new ObjectOutputStream(tracksStream);
            tracksObjectStream.writeObject(tracks);
            tracksObjectStream.close();

            // save data in the chosen path for trains
            FileOutputStream trainsStream = new FileOutputStream(BASE_PATH +"trains.bin");
            ObjectOutputStream trainsObjectStream = new ObjectOutputStream(trainsStream);
            trainsObjectStream.writeObject(trains);
            trainsObjectStream.close();

            // save data in the chosen path for passengers
            FileOutputStream passengersStream = new FileOutputStream(BASE_PATH +"passengers.bin");
            ObjectOutputStream passengersObjectStream = new ObjectOutputStream(passengersStream);
            passengersObjectStream.writeObject(passengers);
            passengersObjectStream.close();

            // Message to show that the data was saved
            JOptionPane.showMessageDialog(null,"Data saved successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error saving data!");
        }


    }

    /**
     * This method is used to load the data
     *
     * @param stations the list of stations
     * @param tracks the list of tracks
     * @param trains the list of trains
     * @param passengers the list of passengers
     *
     * @throws IOException if no path is chosen
     */
    public static void loadData(List<StationADT> stations, List<TrackADT> tracks, List<TrainADT> trains, List<PassengerADT> passengers) throws IOException {
        FileInputStream inputStream = null;
        ObjectInputStream objectStream = null;
        try {

            BASE_PATH = choosePath();

            inputStream = new FileInputStream(BASE_PATH +"stations.bin");
            objectStream = new ObjectInputStream(inputStream);
            stations.clear();
            stations.addAll((List<StationADT>) objectStream.readObject());

            inputStream = new FileInputStream(BASE_PATH +"tracks.bin");
            objectStream = new ObjectInputStream(inputStream);
            tracks.clear();
            tracks.addAll((List<TrackADT>) objectStream.readObject());

            inputStream = new FileInputStream(BASE_PATH +"trains.bin");
            objectStream = new ObjectInputStream(inputStream);
            trains.clear();
            trains.addAll((List<TrainADT>) objectStream.readObject());

            inputStream = new FileInputStream(BASE_PATH +"passengers.bin");
            objectStream = new ObjectInputStream(inputStream);
            passengers.clear();
            passengers.addAll((List<PassengerADT>) objectStream.readObject());
        } catch (ClassNotFoundException ignored) {
            JOptionPane.showMessageDialog(null,"Error loading a class that doesn't exists!");
            System.exit(1);
        } finally {
            try {
                if (objectStream != null) {
                    objectStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ignored) {}
        }
    }
}
