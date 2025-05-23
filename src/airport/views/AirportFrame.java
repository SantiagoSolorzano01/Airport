/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package airport.views;

import airport.controllers.FlightController;
import airport.controllers.LocationController;
import airport.controllers.PassengerController;
import airport.controllers.PlaneController;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Flight;
import airport.models.Location;
import airport.models.Passenger;
import airport.models.Plane;
import airport.models.storages.FlightStorage;
import airport.models.storages.LocationStorage;
import airport.models.storages.PassengerStorage;
import airport.models.storages.PlaneStorage;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edangulo
 */
public class AirportFrame extends javax.swing.JFrame {

    /**
     * Creates new form AirportFrame
     */
    private int x, y;
    private ArrayList<Passenger> passengers;
    private ArrayList<Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList<Flight> flights;

    public AirportFrame() throws Exception {
        initComponents();
        loadFlightFormData(); // Cargar IDs al iniciar
        loadFlightIds();
        updateFlightLists();
        LocationController locationController = new LocationController();
        // Configuración inicial de los ComboBox
        List<Location> locationsList = locationController.getAllLocationsSorted();

        // Convertir la lista de Location a un array de Strings (nombres o IDs)
        String[] locationsArray = locationsList.stream()
                .map(Location::getAirportId) // o .map(Location::getName) según lo que quieras mostrar
                .toArray(String[]::new);

        // ComboBox de salida y llegada con modelos independientes
        FlightRegistration_ChooseDepartureLocation.setModel(new DefaultComboBoxModel<>(locationsArray));
        FlightRegistration_ChooseArrivalLocation.setModel(new DefaultComboBoxModel<>(locationsArray));

        // ComboBox de escala con opción nula
        String[] locationsWithNull = new String[locationsArray.length + 1];
        locationsWithNull[0] = ""; // o null si prefieres
        System.arraycopy(locationsArray, 0, locationsWithNull, 1, locationsArray.length);
        FlightRegistration_ChooseScaleLocation.setModel(new DefaultComboBoxModel<>(locationsWithNull));

        PassengerController controller = new PassengerController();
        this.passengers = (ArrayList<Passenger>) controller.getAllPassengersSorted();
        this.planes = new ArrayList<>();
        this.locations = new ArrayList<>();
        FlightController controllerF = new FlightController();
        this.flights = (ArrayList<Flight>) controllerF.getAllFlightsSorted();

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);

        this.generateMonths();
        this.generateDays();
        this.generateHours();
        this.generateMinutes();
        this.blockPanels();
    }

    private void blockPanels() {
        //9, 11
        for (int i = 1; i < PrincipalPanel.getTabCount(); i++) {
            if (i != 9 && i != 11) {
                PrincipalPanel.setEnabledAt(i, false);
            }
        }
    }

    private void generateMonths() {
        for (int i = 1; i < 13; i++) {
            PassangerRegistration_ChooseMonth.addItem("" + i);
            FlightRegistration_ChooseMonth.addItem("" + i);
            UpdateInfo_MonthTextField.addItem("" + i);
        }
    }

    private void generateDays() {
        for (int i = 1; i < 32; i++) {
            PassangerRegistration_ChooseDay.addItem("" + i);
            FlightRegistration_ChooseDay.addItem("" + i);
            UpdateInfo_DayTextField.addItem("" + i);
        }
    }

    private void generateHours() {
        for (int i = 0; i < 24; i++) {
            FlightRegistration_DepartureDateHour.addItem("" + i);
            FlightRegistration_Duration1Hour.addItem("" + i);
            FlightRegistration_Duration2Hour.addItem("" + i);
            DelayFlight_ChooseHour.addItem("" + i);
        }
    }

    private void generateMinutes() {
        for (int i = 0; i < 60; i++) {
            FlightRegistration_DepartureDateMinute.addItem("" + i);
            FlightRegistration_Duration1Minute.addItem("" + i);
            FlightRegistration_Duration2Minute.addItem("" + i);
            DelayFlight_ChooseMinute.addItem("" + i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new airport.views.PanelRound();
        panelRound2 = new airport.views.PanelRound();
        ClosePageButton = new javax.swing.JButton();
        PrincipalPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Administration_User = new javax.swing.JRadioButton();
        Administration_administrator = new javax.swing.JRadioButton();
        Administration_SelectUser = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        PassangerRegistration_CountryLabel = new javax.swing.JLabel();
        PassangerRegistration_IdLabel = new javax.swing.JLabel();
        PassangerRegistration_FirstNameLabel = new javax.swing.JLabel();
        PassangerRegistration_LastNameLabel = new javax.swing.JLabel();
        PassangerRegistration_BirthdateLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PassangerRegistration_CodeNumberTextField = new javax.swing.JTextField();
        PassangerRegistration_IdTextField = new javax.swing.JTextField();
        PassangerRegistration_YearTextField = new javax.swing.JTextField();
        PassangerRegistration_CountryTextField = new javax.swing.JTextField();
        PassangerRegistration_NumberTextField = new javax.swing.JTextField();
        PassangerRegistration_PhoneLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PassangerRegistration_LastNameTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PassangerRegistration_ChooseMonth = new javax.swing.JComboBox<>();
        PassangerRegistration_FirstNameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PassangerRegistration_ChooseDay = new javax.swing.JComboBox<>();
        PassangerRegistration_RegisterButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        AirplaneRegistration_IdLabel = new javax.swing.JLabel();
        AirplaneRegistration_IdTextField = new javax.swing.JTextField();
        AirplaneRegistration_BrandLabel = new javax.swing.JLabel();
        AirplaneRegistration_BrandTextField = new javax.swing.JTextField();
        AirplaneRegistration_ModelTextField = new javax.swing.JTextField();
        AirplaneRegistration_ModelLabel = new javax.swing.JLabel();
        AirplaneRegistration_MaxCapacityTextField = new javax.swing.JTextField();
        AirplaneRegistration_MaxCapacityLabel = new javax.swing.JLabel();
        AirplaneRegistration_AirlineTextField = new javax.swing.JTextField();
        AirplaneRegistration_AirLineLabel = new javax.swing.JLabel();
        AirplaneRegistration_CreateButton = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        LocationRegistration_IdLabel = new javax.swing.JLabel();
        LocationRegistration_IdTextField = new javax.swing.JTextField();
        LocationRegistration_NameLabel = new javax.swing.JLabel();
        LocationRegistration_NameTextField = new javax.swing.JTextField();
        LocationRegistration_CityTextField = new javax.swing.JTextField();
        LocationRegistration_CityLabel = new javax.swing.JLabel();
        LocationRegistration_CountryLabel = new javax.swing.JLabel();
        LocationRegistration_CountryTextField = new javax.swing.JTextField();
        LocationRegistration_LatitudeTextField = new javax.swing.JTextField();
        LocationRegistration_LatitudeLabel = new javax.swing.JLabel();
        LocationRegistration_LongitudeLabel = new javax.swing.JLabel();
        LocationRegistration_LongitudeTextField = new javax.swing.JTextField();
        LocationRegistration_CreateButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        FlightRegistration_IdLabel = new javax.swing.JLabel();
        FlightRegistration_IdTextField = new javax.swing.JTextField();
        FlightRegistration_PlaneLabel = new javax.swing.JLabel();
        FlightRegistration_ChoosePlane = new javax.swing.JComboBox<>();
        FlightRegistration_ChooseDepartureLocation = new javax.swing.JComboBox<>();
        FlightRegistration_DepartureLocationLabel = new javax.swing.JLabel();
        FlightRegistration_ChooseArrivalLocation = new javax.swing.JComboBox<>();
        FlightRegistration_ArrivalLocationLabel = new javax.swing.JLabel();
        FlightRegistration_ScaleLocationLabel = new javax.swing.JLabel();
        FlightRegistration_ChooseScaleLocation = new javax.swing.JComboBox<>();
        FlightRegistration_Duration2Label = new javax.swing.JLabel();
        FlightRegistration_DurationLabel = new javax.swing.JLabel();
        FlightRegistration_DepartureDateLabel = new javax.swing.JLabel();
        FlightRegistration_Year = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        FlightRegistration_ChooseMonth = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        FlightRegistration_ChooseDay = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        FlightRegistration_DepartureDateHour = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        FlightRegistration_DepartureDateMinute = new javax.swing.JComboBox<>();
        FlightRegistration_Duration1Hour = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        FlightRegistration_Duration1Minute = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        FlightRegistration_Duration2Hour = new javax.swing.JComboBox<>();
        FlightRegistration_Duration2Minute = new javax.swing.JComboBox<>();
        FlightRegistration_CreateButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        UpdateInfo_IdLabel = new javax.swing.JLabel();
        UpdateInfo_IdTextField = new javax.swing.JTextField();
        UpdateInfo_FirstNameLabel = new javax.swing.JLabel();
        UpdateInfo_FirstNameTextField = new javax.swing.JTextField();
        UpdateInfo_LastNameLabel = new javax.swing.JLabel();
        UpdateInfo_LastNameTextField = new javax.swing.JTextField();
        UpdateInfo_BirthdateLabel = new javax.swing.JLabel();
        UpdateInfo_YearTextField = new javax.swing.JTextField();
        UpdateInfo_MonthTextField = new javax.swing.JComboBox<>();
        UpdateInfo_DayTextField = new javax.swing.JComboBox<>();
        UpdateInfo_NumberTextField = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        UpdateInfo_NumberCodeTextField = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        UpdateInfo_PhoneLabel = new javax.swing.JLabel();
        UpdateInfo_CountryLabel = new javax.swing.JLabel();
        UpdateInfo_CountryTextField = new javax.swing.JTextField();
        UpdateInfo_UpdateButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        AddToFlight_IdTextField = new javax.swing.JTextField();
        AddToFlight_IdLabel = new javax.swing.JLabel();
        AddToFlight_FlightLabel = new javax.swing.JLabel();
        AddToFlight_ChooseFlight = new javax.swing.JComboBox<>();
        AddToFlight_AddButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowMyFlights_Table = new javax.swing.JTable();
        ShowMyFlights_RefreshButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ShowAllPassengers_Table = new javax.swing.JTable();
        ShowAllPassengers_RefreshButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ShowAllFlights_Table = new javax.swing.JTable();
        ShowAllFlights_RefreshButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        ShowAllPlanes_RefreshButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ShowAllPlanes_Table = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ShowAllLocations_Table = new javax.swing.JTable();
        ShowAllLocations_RefreshButton = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        DelayFlight_ChooseHour = new javax.swing.JComboBox<>();
        DelayFlight_HoursLabel = new javax.swing.JLabel();
        DelayFlight_IdLabel = new javax.swing.JLabel();
        DelayFlight_ChooseId = new javax.swing.JComboBox<>();
        DelayFlight_MinutesLabel = new javax.swing.JLabel();
        DelayFlight_ChooseMinute = new javax.swing.JComboBox<>();
        DelayFlight_DelayButton = new javax.swing.JButton();
        panelRound3 = new airport.views.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setRadius(40);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelRound2MouseDragged(evt);
            }
        });
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRound2MousePressed(evt);
            }
        });

        ClosePageButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ClosePageButton.setText("X");
        ClosePageButton.setBorderPainted(false);
        ClosePageButton.setContentAreaFilled(false);
        ClosePageButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClosePageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosePageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(1083, Short.MAX_VALUE)
                .addComponent(ClosePageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(ClosePageButton)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        PrincipalPanel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Administration_User.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        Administration_User.setText("User");
        Administration_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Administration_UserActionPerformed(evt);
            }
        });
        jPanel1.add(Administration_User, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        Administration_administrator.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        Administration_administrator.setText("Administrator");
        Administration_administrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Administration_administratorActionPerformed(evt);
            }
        });
        jPanel1.add(Administration_administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 164, -1, -1));

        Administration_SelectUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        Administration_SelectUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User" }));
        Administration_SelectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Administration_SelectUserActionPerformed(evt);
            }
        });
        jPanel1.add(Administration_SelectUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        PrincipalPanel.addTab("Administration", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PassangerRegistration_CountryLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_CountryLabel.setText("Country:");
        jPanel2.add(PassangerRegistration_CountryLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        PassangerRegistration_IdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_IdLabel.setText("ID:");
        jPanel2.add(PassangerRegistration_IdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        PassangerRegistration_FirstNameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_FirstNameLabel.setText("First Name:");
        jPanel2.add(PassangerRegistration_FirstNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        PassangerRegistration_LastNameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_LastNameLabel.setText("Last Name:");
        jPanel2.add(PassangerRegistration_LastNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        PassangerRegistration_BirthdateLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_BirthdateLabel.setText("Birthdate:");
        jPanel2.add(PassangerRegistration_BirthdateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("+");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 20, -1));

        PassangerRegistration_CodeNumberTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassangerRegistration_CodeNumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 50, -1));

        PassangerRegistration_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassangerRegistration_IdTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        PassangerRegistration_YearTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassangerRegistration_YearTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, -1));

        PassangerRegistration_CountryTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassangerRegistration_CountryTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, -1));

        PassangerRegistration_NumberTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassangerRegistration_NumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 130, -1));

        PassangerRegistration_PhoneLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_PhoneLabel.setText("Phone:");
        jPanel2.add(PassangerRegistration_PhoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("-");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 30, -1));

        PassangerRegistration_LastNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassangerRegistration_LastNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 130, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("-");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        PassangerRegistration_ChooseMonth.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_ChooseMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        jPanel2.add(PassangerRegistration_ChooseMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        PassangerRegistration_FirstNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassangerRegistration_FirstNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("-");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 30, -1));

        PassangerRegistration_ChooseDay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_ChooseDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        jPanel2.add(PassangerRegistration_ChooseDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        PassangerRegistration_RegisterButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassangerRegistration_RegisterButton.setText("Register");
        PassangerRegistration_RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassangerRegistration_RegisterButtonActionPerformed(evt);
            }
        });
        jPanel2.add(PassangerRegistration_RegisterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        PrincipalPanel.addTab("Passenger registration", jPanel2);

        jPanel3.setLayout(null);

        AirplaneRegistration_IdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplaneRegistration_IdLabel.setText("ID:");
        jPanel3.add(AirplaneRegistration_IdLabel);
        AirplaneRegistration_IdLabel.setBounds(53, 96, 22, 25);

        AirplaneRegistration_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRegistration_IdTextField);
        AirplaneRegistration_IdTextField.setBounds(180, 93, 130, 31);

        AirplaneRegistration_BrandLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplaneRegistration_BrandLabel.setText("Brand:");
        jPanel3.add(AirplaneRegistration_BrandLabel);
        AirplaneRegistration_BrandLabel.setBounds(53, 157, 52, 25);

        AirplaneRegistration_BrandTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRegistration_BrandTextField);
        AirplaneRegistration_BrandTextField.setBounds(180, 154, 130, 31);

        AirplaneRegistration_ModelTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRegistration_ModelTextField);
        AirplaneRegistration_ModelTextField.setBounds(180, 213, 130, 31);

        AirplaneRegistration_ModelLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplaneRegistration_ModelLabel.setText("Model:");
        jPanel3.add(AirplaneRegistration_ModelLabel);
        AirplaneRegistration_ModelLabel.setBounds(53, 216, 57, 25);

        AirplaneRegistration_MaxCapacityTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRegistration_MaxCapacityTextField);
        AirplaneRegistration_MaxCapacityTextField.setBounds(180, 273, 130, 31);

        AirplaneRegistration_MaxCapacityLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplaneRegistration_MaxCapacityLabel.setText("Max Capacity:");
        jPanel3.add(AirplaneRegistration_MaxCapacityLabel);
        AirplaneRegistration_MaxCapacityLabel.setBounds(53, 276, 114, 25);

        AirplaneRegistration_AirlineTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRegistration_AirlineTextField);
        AirplaneRegistration_AirlineTextField.setBounds(180, 333, 130, 31);

        AirplaneRegistration_AirLineLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplaneRegistration_AirLineLabel.setText("Airline:");
        jPanel3.add(AirplaneRegistration_AirLineLabel);
        AirplaneRegistration_AirLineLabel.setBounds(53, 336, 70, 25);

        AirplaneRegistration_CreateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplaneRegistration_CreateButton.setText("Create");
        AirplaneRegistration_CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AirplaneRegistration_CreateButtonActionPerformed(evt);
            }
        });
        jPanel3.add(AirplaneRegistration_CreateButton);
        AirplaneRegistration_CreateButton.setBounds(490, 480, 120, 40);

        PrincipalPanel.addTab("Airplane registration", jPanel3);

        LocationRegistration_IdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_IdLabel.setText("Airport ID:");

        LocationRegistration_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_NameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_NameLabel.setText("Airport name:");

        LocationRegistration_NameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_CityTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_CityLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_CityLabel.setText("Airport city:");

        LocationRegistration_CountryLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_CountryLabel.setText("Airport country:");

        LocationRegistration_CountryTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_LatitudeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_LatitudeLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_LatitudeLabel.setText("Airport latitude:");

        LocationRegistration_LongitudeLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_LongitudeLabel.setText("Airport longitude:");

        LocationRegistration_LongitudeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_CreateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_CreateButton.setText("Create");
        LocationRegistration_CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocationRegistration_CreateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LocationRegistration_IdLabel)
                            .addComponent(LocationRegistration_NameLabel)
                            .addComponent(LocationRegistration_CityLabel)
                            .addComponent(LocationRegistration_CountryLabel)
                            .addComponent(LocationRegistration_LatitudeLabel)
                            .addComponent(LocationRegistration_LongitudeLabel))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LocationRegistration_LongitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_CityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_CountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_LatitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(LocationRegistration_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(515, 515, 515))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(LocationRegistration_IdLabel)
                        .addGap(36, 36, 36)
                        .addComponent(LocationRegistration_NameLabel)
                        .addGap(34, 34, 34)
                        .addComponent(LocationRegistration_CityLabel)
                        .addGap(35, 35, 35)
                        .addComponent(LocationRegistration_CountryLabel)
                        .addGap(35, 35, 35)
                        .addComponent(LocationRegistration_LatitudeLabel))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(LocationRegistration_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(LocationRegistration_NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(LocationRegistration_CityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(LocationRegistration_CountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(LocationRegistration_LatitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LocationRegistration_LongitudeLabel)
                    .addComponent(LocationRegistration_LongitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(LocationRegistration_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        PrincipalPanel.addTab("Location registration", jPanel13);

        FlightRegistration_IdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_IdLabel.setText("ID:");

        FlightRegistration_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_IdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightRegistration_IdTextFieldActionPerformed(evt);
            }
        });

        FlightRegistration_PlaneLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_PlaneLabel.setText("Plane:");

        FlightRegistration_ChoosePlane.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ChoosePlane.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plane" }));

        FlightRegistration_ChooseDepartureLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ChooseDepartureLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        FlightRegistration_DepartureLocationLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_DepartureLocationLabel.setText("Departure location:");

        FlightRegistration_ChooseArrivalLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ChooseArrivalLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));
        FlightRegistration_ChooseArrivalLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightRegistration_ChooseArrivalLocationActionPerformed(evt);
            }
        });

        FlightRegistration_ArrivalLocationLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ArrivalLocationLabel.setText("Arrival location:");

        FlightRegistration_ScaleLocationLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ScaleLocationLabel.setText("Scale location:");

        FlightRegistration_ChooseScaleLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ChooseScaleLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        FlightRegistration_Duration2Label.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_Duration2Label.setText("Duration:");

        FlightRegistration_DurationLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_DurationLabel.setText("Duration:");

        FlightRegistration_DepartureDateLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_DepartureDateLabel.setText("Departure date:");

        FlightRegistration_Year.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setText("-");

        FlightRegistration_ChooseMonth.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ChooseMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("-");

        FlightRegistration_ChooseDay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_ChooseDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("-");

        FlightRegistration_DepartureDateHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_DepartureDateHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("-");

        FlightRegistration_DepartureDateMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_DepartureDateMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        FlightRegistration_Duration1Hour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_Duration1Hour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("-");

        FlightRegistration_Duration1Minute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_Duration1Minute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("-");

        FlightRegistration_Duration2Hour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_Duration2Hour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        FlightRegistration_Duration2Minute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_Duration2Minute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        FlightRegistration_CreateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightRegistration_CreateButton.setText("Create");
        FlightRegistration_CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightRegistration_CreateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(FlightRegistration_ScaleLocationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FlightRegistration_ChooseScaleLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(FlightRegistration_ArrivalLocationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FlightRegistration_ChooseArrivalLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(FlightRegistration_DepartureLocationLabel)
                        .addGap(46, 46, 46)
                        .addComponent(FlightRegistration_ChooseDepartureLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FlightRegistration_IdLabel)
                            .addComponent(FlightRegistration_PlaneLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FlightRegistration_IdTextField)
                            .addComponent(FlightRegistration_ChoosePlane, 0, 130, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FlightRegistration_Duration2Label)
                    .addComponent(FlightRegistration_DurationLabel)
                    .addComponent(FlightRegistration_DepartureDateLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(FlightRegistration_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FlightRegistration_ChooseMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FlightRegistration_ChooseDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FlightRegistration_DepartureDateHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(FlightRegistration_DepartureDateMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(FlightRegistration_Duration1Hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(FlightRegistration_Duration1Minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(FlightRegistration_Duration2Hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(FlightRegistration_Duration2Minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FlightRegistration_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(FlightRegistration_IdLabel))
                    .addComponent(FlightRegistration_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FlightRegistration_PlaneLabel)
                    .addComponent(FlightRegistration_ChoosePlane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FlightRegistration_DepartureDateHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(FlightRegistration_DepartureDateMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FlightRegistration_DepartureLocationLabel)
                                .addComponent(FlightRegistration_ChooseDepartureLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FlightRegistration_DepartureDateLabel))
                            .addComponent(FlightRegistration_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FlightRegistration_ChooseMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(FlightRegistration_ChooseDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FlightRegistration_ArrivalLocationLabel)
                                .addComponent(FlightRegistration_ChooseArrivalLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FlightRegistration_DurationLabel))
                            .addComponent(FlightRegistration_Duration1Hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(FlightRegistration_Duration1Minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FlightRegistration_Duration2Hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(FlightRegistration_Duration2Minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FlightRegistration_ScaleLocationLabel)
                                .addComponent(FlightRegistration_ChooseScaleLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FlightRegistration_Duration2Label)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(FlightRegistration_CreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        PrincipalPanel.addTab("Flight registration", jPanel4);

        UpdateInfo_IdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_IdLabel.setText("ID:");

        UpdateInfo_IdTextField.setEditable(false);
        UpdateInfo_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_IdTextField.setEnabled(false);

        UpdateInfo_FirstNameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_FirstNameLabel.setText("First Name:");

        UpdateInfo_FirstNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UpdateInfo_LastNameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_LastNameLabel.setText("Last Name:");

        UpdateInfo_LastNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UpdateInfo_BirthdateLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_BirthdateLabel.setText("Birthdate:");

        UpdateInfo_YearTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UpdateInfo_MonthTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_MonthTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        UpdateInfo_DayTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_DayTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        UpdateInfo_NumberTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel40.setText("-");

        UpdateInfo_NumberCodeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel41.setText("+");

        UpdateInfo_PhoneLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_PhoneLabel.setText("Phone:");

        UpdateInfo_CountryLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_CountryLabel.setText("Country:");

        UpdateInfo_CountryTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UpdateInfo_UpdateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfo_UpdateButton.setText("Update");
        UpdateInfo_UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateInfo_UpdateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(UpdateInfo_IdLabel)
                                .addGap(108, 108, 108)
                                .addComponent(UpdateInfo_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(UpdateInfo_FirstNameLabel)
                                .addGap(41, 41, 41)
                                .addComponent(UpdateInfo_FirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(UpdateInfo_LastNameLabel)
                                .addGap(43, 43, 43)
                                .addComponent(UpdateInfo_LastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(UpdateInfo_BirthdateLabel)
                                .addGap(55, 55, 55)
                                .addComponent(UpdateInfo_YearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(UpdateInfo_MonthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(UpdateInfo_DayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(UpdateInfo_PhoneLabel)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(UpdateInfo_NumberCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(UpdateInfo_NumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(UpdateInfo_CountryLabel)
                                .addGap(63, 63, 63)
                                .addComponent(UpdateInfo_CountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(UpdateInfo_UpdateButton)))
                .addContainerGap(586, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpdateInfo_IdLabel)
                    .addComponent(UpdateInfo_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpdateInfo_FirstNameLabel)
                    .addComponent(UpdateInfo_FirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpdateInfo_LastNameLabel)
                    .addComponent(UpdateInfo_LastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpdateInfo_BirthdateLabel)
                    .addComponent(UpdateInfo_YearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateInfo_MonthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateInfo_DayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpdateInfo_PhoneLabel)
                    .addComponent(jLabel41)
                    .addComponent(UpdateInfo_NumberCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(UpdateInfo_NumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpdateInfo_CountryLabel)
                    .addComponent(UpdateInfo_CountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(UpdateInfo_UpdateButton)
                .addGap(113, 113, 113))
        );

        PrincipalPanel.addTab("Update info", jPanel5);

        AddToFlight_IdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFlight_IdTextField.setEnabled(false);

        AddToFlight_IdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFlight_IdLabel.setText("ID:");

        AddToFlight_FlightLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFlight_FlightLabel.setText("Flight:");

        AddToFlight_ChooseFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFlight_ChooseFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flight" }));

        AddToFlight_AddButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFlight_AddButton.setText("Add");
        AddToFlight_AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToFlight_AddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddToFlight_IdLabel)
                    .addComponent(AddToFlight_FlightLabel))
                .addGap(79, 79, 79)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddToFlight_ChooseFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddToFlight_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(860, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddToFlight_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(509, 509, 509))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(AddToFlight_IdLabel))
                    .addComponent(AddToFlight_IdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddToFlight_FlightLabel)
                    .addComponent(AddToFlight_ChooseFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(AddToFlight_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        PrincipalPanel.addTab("Add to flight", jPanel6);

        ShowMyFlights_Table.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowMyFlights_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Departure Date", "Arrival Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ShowMyFlights_Table);

        ShowMyFlights_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowMyFlights_RefreshButton.setText("Refresh");
        ShowMyFlights_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowMyFlights_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(322, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ShowMyFlights_RefreshButton)
                .addGap(527, 527, 527))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(ShowMyFlights_RefreshButton)
                .addContainerGap())
        );

        PrincipalPanel.addTab("Show my flights", jPanel7);

        ShowAllPassengers_Table.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllPassengers_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ShowAllPassengers_Table);

        ShowAllPassengers_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllPassengers_RefreshButton.setText("Refresh");
        ShowAllPassengers_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllPassengers_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(ShowAllPassengers_RefreshButton))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ShowAllPassengers_RefreshButton)
                .addContainerGap())
        );

        PrincipalPanel.addTab("Show all passengers", jPanel8);

        ShowAllFlights_Table.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllFlights_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ShowAllFlights_Table);

        ShowAllFlights_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllFlights_RefreshButton.setText("Refresh");
        ShowAllFlights_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllFlights_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(ShowAllFlights_RefreshButton)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ShowAllFlights_RefreshButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        PrincipalPanel.addTab("Show all flights", jPanel9);

        ShowAllPlanes_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllPlanes_RefreshButton.setText("Refresh");
        ShowAllPlanes_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllPlanes_RefreshButtonActionPerformed(evt);
            }
        });

        ShowAllPlanes_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(ShowAllPlanes_Table);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(ShowAllPlanes_RefreshButton))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(ShowAllPlanes_RefreshButton)
                .addGap(17, 17, 17))
        );

        PrincipalPanel.addTab("Show all planes", jPanel10);

        ShowAllLocations_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Airport ID", "Airport Name", "City", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(ShowAllLocations_Table);

        ShowAllLocations_RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllLocations_RefreshButton.setText("Refresh");
        ShowAllLocations_RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllLocations_RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(ShowAllLocations_RefreshButton))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(ShowAllLocations_RefreshButton)
                .addGap(17, 17, 17))
        );

        PrincipalPanel.addTab("Show all locations", jPanel11);

        DelayFlight_ChooseHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlight_ChooseHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        DelayFlight_HoursLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlight_HoursLabel.setText("Hours:");

        DelayFlight_IdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlight_IdLabel.setText("ID:");

        DelayFlight_ChooseId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlight_ChooseId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID" }));

        DelayFlight_MinutesLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlight_MinutesLabel.setText("Minutes:");

        DelayFlight_ChooseMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlight_ChooseMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        DelayFlight_DelayButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlight_DelayButton.setText("Delay");
        DelayFlight_DelayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelayFlight_DelayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(DelayFlight_MinutesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DelayFlight_ChooseMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DelayFlight_IdLabel)
                            .addComponent(DelayFlight_HoursLabel))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DelayFlight_ChooseHour, 0, 136, Short.MAX_VALUE)
                            .addComponent(DelayFlight_ChooseId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(820, 820, 820))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DelayFlight_DelayButton)
                .addGap(531, 531, 531))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DelayFlight_IdLabel)
                    .addComponent(DelayFlight_ChooseId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DelayFlight_HoursLabel)
                    .addComponent(DelayFlight_ChooseHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DelayFlight_MinutesLabel)
                    .addComponent(DelayFlight_ChooseMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(DelayFlight_DelayButton)
                .addGap(33, 33, 33))
        );

        PrincipalPanel.addTab("Delay flight", jPanel12);

        panelRound1.add(PrincipalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 1150, 620));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 660, 1150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelRound2MousePressed

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelRound2MouseDragged

    private void Administration_administratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Administration_administratorActionPerformed
        if (Administration_User.isSelected()) {
            Administration_User.setSelected(false);
            Administration_SelectUser.setSelectedIndex(0);

        }
        for (int i = 1; i < PrincipalPanel.getTabCount(); i++) {
            PrincipalPanel.setEnabledAt(i, true);
        }
        PrincipalPanel.setEnabledAt(5, false);
        PrincipalPanel.setEnabledAt(6, false);
    }//GEN-LAST:event_Administration_administratorActionPerformed

    private void Administration_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Administration_UserActionPerformed
        if (Administration_administrator.isSelected()) {
            Administration_administrator.setSelected(false);
        }
        for (int i = 1; i < PrincipalPanel.getTabCount(); i++) {

            PrincipalPanel.setEnabledAt(i, false);

        }
        PrincipalPanel.setEnabledAt(9, true);
        PrincipalPanel.setEnabledAt(5, true);
        PrincipalPanel.setEnabledAt(6, true);
        PrincipalPanel.setEnabledAt(7, true);
        PrincipalPanel.setEnabledAt(11, true);
    }//GEN-LAST:event_Administration_UserActionPerformed

    private void PassangerRegistration_RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassangerRegistration_RegisterButtonActionPerformed
        PassengerController controller = null;
        try {
            controller = new PassengerController();
        } catch (Exception ex) {
            Logger.getLogger(AirportFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        Response response = controller.registerPassenger(
                PassangerRegistration_IdTextField.getText(),
                PassangerRegistration_FirstNameTextField.getText(),
                PassangerRegistration_LastNameTextField.getText(),
                PassangerRegistration_YearTextField.getText(),
                PassangerRegistration_ChooseMonth.getItemAt(PassangerRegistration_ChooseMonth.getSelectedIndex()),
                PassangerRegistration_ChooseDay.getItemAt(PassangerRegistration_ChooseDay.getSelectedIndex()),
                PassangerRegistration_CodeNumberTextField.getText(),
                PassangerRegistration_NumberTextField.getText(),
                PassangerRegistration_CountryTextField.getText()
        );

        // Manejar la respuesta
        if (response.getStatus() == Status.CREATED) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            Passenger newPassenger = (Passenger) response.getObject();
            // Obtener todos los IDs ordenados
            List<Passenger> sortedPassengers = controller.getAllPassengersSorted();
            // Actualizar el JComboBox
            updatePassengerComboBox(sortedPassengers);
            clearPassengerRegistrationForm();

            try {
                // DEBUG: Verificar que se guardó en JSON
                System.out.println("Pasajero guardado. Total en storage: "
                        + PassengerStorage.getInstance().getAllPassengers().size());
            } catch (Exception ex) {
                Logger.getLogger(AirportFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_PassangerRegistration_RegisterButtonActionPerformed

    private void updatePassengerComboBox(List<Passenger> passengers) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Selecciona un pasajero"); // <-- Esto va SIEMPRE primero
        for (Passenger p : passengers) {
            model.addElement(String.valueOf(p.getId()));
        }
        this.Administration_SelectUser.setModel(model);
        this.Administration_SelectUser.setSelectedIndex(0); // Selecciona siempre el "nulo"
    }

    private void clearPassengerRegistrationForm() {
        // Limpiar todos los campos del formulario
        // Limpiar todos los campos del formulario
        PassangerRegistration_IdTextField.setText("");
        PassangerRegistration_FirstNameTextField.setText("");
        PassangerRegistration_LastNameTextField.setText("");
        PassangerRegistration_YearTextField.setText("");
        PassangerRegistration_ChooseMonth.setSelectedIndex(0); // Asume que el primer item es vacío o default
        PassangerRegistration_ChooseDay.setSelectedIndex(0);
        PassangerRegistration_CodeNumberTextField.setText("");
        PassangerRegistration_NumberTextField.setText("");
        PassangerRegistration_CountryTextField.setText("");
    }

    private void AirplaneRegistration_CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AirplaneRegistration_CreateButtonActionPerformed
        try {
            String id = AirplaneRegistration_IdTextField.getText();
            String brand = AirplaneRegistration_BrandTextField.getText();
            String model = AirplaneRegistration_ModelTextField.getText();
            String maxCapacity = AirplaneRegistration_MaxCapacityTextField.getText();
            String airline = AirplaneRegistration_AirlineTextField.getText();

            PlaneController controller = new PlaneController();
            Response response = controller.registerPlane(
                    id,
                    brand,
                    model,
                    maxCapacity,
                    airline
            );

            if (response.getStatus() == Status.CREATED) {
                JOptionPane.showMessageDialog(this, "Avión registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                clearAirplaneRegistrationForm();
                List<Plane> sortedPlanes = controller.getAllPlanesSorted();
                updatePlaneComboBox(sortedPlanes);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_AirplaneRegistration_CreateButtonActionPerformed
    //Actualiza un JComboBox con los IDs de aviones ordenados (formato AB12345)

    private void updatePlaneComboBox(List<Plane> sortedPlanes) {
        try {
            PlaneController controller = new PlaneController();
            List<Plane> sortedPlanes1 = controller.getAllPlanesSorted();

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (Plane plane : sortedPlanes1) {
                model.addElement(plane.getId());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar lista de aviones: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAirplaneRegistrationForm() {
        AirplaneRegistration_IdTextField.setText("");
        AirplaneRegistration_BrandTextField.setText("");
        AirplaneRegistration_ModelTextField.setText("");
        AirplaneRegistration_MaxCapacityTextField.setText("");
        AirplaneRegistration_AirlineTextField.setText("");
        AirplaneRegistration_IdTextField.requestFocus();
    }

    private void LocationRegistration_CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocationRegistration_CreateButtonActionPerformed
        try {
            LocationController controller = new LocationController();

            Response response = controller.registerLocation(
                    LocationRegistration_IdTextField.getText(),
                    LocationRegistration_NameTextField.getText(),
                    LocationRegistration_CityTextField.getText(),
                    LocationRegistration_CountryTextField.getText(),
                    LocationRegistration_LatitudeTextField.getText(),
                    LocationRegistration_LongitudeTextField.getText()
            );

            if (response.getStatus() == Status.CREATED) {
                Location newLocation = (Location) response.getObject();
                // Obtener todas las ubicaciones ordenadas
                List<Location> sortedLocations = controller.getAllLocationsSorted();
                // Actualizar los combos
                updateLocationCombos(sortedLocations);
                // Agregar a los combos de vuelos
                FlightRegistration_ChooseDepartureLocation.addItem(newLocation.getAirportId());
                FlightRegistration_ChooseArrivalLocation.addItem(newLocation.getAirportId());
                FlightRegistration_ChooseScaleLocation.addItem(newLocation.getAirportId());

                clearLocationRegistrationForm();
                JOptionPane.showMessageDialog(this, "Ubicación registrada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_LocationRegistration_CreateButtonActionPerformed
    // Método para actualizar los combos de ubicaciones

    private void updateLocationCombos(List<Location> locations) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Location loc : locations) {
            model.addElement(loc.getAirportId());
        }

        // Aplicar el mismo modelo ordenado a todos los combos
        FlightRegistration_ChooseDepartureLocation.setModel(model);
        FlightRegistration_ChooseArrivalLocation.setModel(model);
        FlightRegistration_ChooseScaleLocation.setModel(model);
    }

    private void clearLocationRegistrationForm() {
        LocationRegistration_IdTextField.setText("");
        LocationRegistration_NameTextField.setText("");
        LocationRegistration_CityTextField.setText("");
        LocationRegistration_CountryTextField.setText("");
        LocationRegistration_LatitudeTextField.setText("");
        LocationRegistration_LongitudeTextField.setText("");
        LocationRegistration_IdTextField.requestFocus();
    }

    private void FlightRegistration_CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightRegistration_CreateButtonActionPerformed
        try {
            FlightController controller = new FlightController();

            // Obtener los valores seleccionados - ahora maneja correctamente el valor nulo
            String scaleLocId = (FlightRegistration_ChooseScaleLocation.getSelectedItem() != null)
                    ? FlightRegistration_ChooseScaleLocation.getSelectedItem().toString() : null;

            Response response = controller.registerFlight(
                    FlightRegistration_IdTextField.getText().trim(),
                    FlightRegistration_ChoosePlane.getSelectedItem().toString(),
                    FlightRegistration_ChooseDepartureLocation.getSelectedItem().toString(),
                    FlightRegistration_ChooseArrivalLocation.getSelectedItem().toString(),
                    scaleLocId,
                    FlightRegistration_Year.getText(),
                    FlightRegistration_ChooseMonth.getSelectedItem().toString(),
                    FlightRegistration_ChooseDay.getSelectedItem().toString(),
                    FlightRegistration_DepartureDateHour.getSelectedItem().toString(),
                    FlightRegistration_DepartureDateMinute.getSelectedItem().toString(),
                    FlightRegistration_Duration1Hour.getSelectedItem().toString(),
                    FlightRegistration_Duration1Minute.getSelectedItem().toString(),
                    scaleLocId != null ? FlightRegistration_Duration2Hour.getSelectedItem().toString() : "0",
                    scaleLocId != null ? FlightRegistration_Duration2Minute.getSelectedItem().toString() : "0"
            );

            if (response.getStatus() == Status.CREATED) {
                // Registro exitoso
                refreshFlightLists();
                Flight newFlight = (Flight) response.getObject();
                AddToFlight_ChooseFlight.addItem(newFlight.getId());
                clearFlightRegistrationForm();
                updateFlightLists();

                String scaleId = (newFlight.getScaleLocation() != null)
                        ? newFlight.getScaleLocation().getAirportId()
                        : "Sin escala";

                System.out.println("Vuelo registrado: Avion: " + newFlight.getPlane().getId()
                        + " DepartureID: " + newFlight.getDepartureLocation().getAirportId()
                        + " ArrivalID: " + newFlight.getArrivalLocation().getAirportId()
                        + " ScaleID: " + scaleId);

                JOptionPane.showMessageDialog(this, "Vuelo registrado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_FlightRegistration_CreateButtonActionPerformed
    private void updateFlightLists() {
        try {
            FlightController controller = new FlightController();
            List<Flight> sortedFlights = controller.getAllFlightsSorted();

            // Actualizar el comboBox de selección de vuelos
            DefaultComboBoxModel<String> flightModel = new DefaultComboBoxModel<>();
            for (Flight flight : sortedFlights) {
                String displayText = String.format("%s - %s a %s (%s)",
                        flight.getId(),
                        flight.getDepartureLocation().getAirportId(),
                        flight.getArrivalLocation().getAirportId(),
                        flight.getDepartureDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                flightModel.addElement(displayText);
            }
            AddToFlight_ChooseFlight.setModel(flightModel);

            // Si tienes otras listas/tablas de vuelos, actualízalas aquí también
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar lista de vuelos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFlightRegistrationForm() {
        FlightRegistration_IdTextField.setText("");
        FlightRegistration_Year.setText("");
        FlightRegistration_ChoosePlane.setSelectedIndex(0);
        FlightRegistration_ChooseDepartureLocation.setSelectedIndex(0);
        FlightRegistration_ChooseArrivalLocation.setSelectedIndex(0);
        FlightRegistration_ChooseScaleLocation.setSelectedIndex(-1);
        FlightRegistration_ChooseMonth.setSelectedIndex(0);
        FlightRegistration_ChooseDay.setSelectedIndex(0);
        FlightRegistration_DepartureDateHour.setSelectedIndex(0);
        FlightRegistration_DepartureDateMinute.setSelectedIndex(0);
        FlightRegistration_Duration1Hour.setSelectedIndex(0);
        FlightRegistration_Duration1Minute.setSelectedIndex(0);
        FlightRegistration_Duration2Hour.setSelectedIndex(0);
        FlightRegistration_Duration2Minute.setSelectedIndex(0);
        FlightRegistration_IdTextField.requestFocus();
    }

    private void loadFlightFormData() {
        try {
            // Limpiar ComboBoxes
            FlightRegistration_ChoosePlane.removeAllItems();
            FlightRegistration_ChooseDepartureLocation.removeAllItems();
            FlightRegistration_ChooseArrivalLocation.removeAllItems();
            FlightRegistration_ChooseScaleLocation.removeAllItems();

            // Cargar solo IDs de aviones
            List<Plane> allPlanes = PlaneStorage.getInstance().getAllPlanes();
            for (Plane plane : allPlanes) {
                FlightRegistration_ChoosePlane.addItem(plane.getId()); // Solo el ID
            }

            // Cargar solo IDs de ubicaciones
            List<Location> allLocations = LocationStorage.getInstance().getAllLocations();
            for (Location location : allLocations) {
                String airportId = location.getAirportId(); // Solo el ID
                FlightRegistration_ChooseDepartureLocation.addItem(airportId);
                FlightRegistration_ChooseArrivalLocation.addItem(airportId);
                FlightRegistration_ChooseScaleLocation.addItem(airportId);
            }

            // Opcional: Añadir item vacío para escala (si es opcional)
            FlightRegistration_ChooseScaleLocation.insertItemAt("", 0);
            FlightRegistration_ChooseScaleLocation.setSelectedIndex(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void UpdateInfo_UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateInfo_UpdateButtonActionPerformed
        try {
            PassengerController controller = new PassengerController();

            String idText = UpdateInfo_IdTextField.getText();
            String firstName = UpdateInfo_FirstNameTextField.getText();
            String lastName = UpdateInfo_LastNameTextField.getText();
            String yearText = UpdateInfo_YearTextField.getText();
            String monthText = String.valueOf(UpdateInfo_MonthTextField.getSelectedItem());
            String dayText = String.valueOf(UpdateInfo_DayTextField.getSelectedItem());
            String phoneCodeText = UpdateInfo_NumberCodeTextField.getText();
            String phoneText = UpdateInfo_NumberTextField.getText();
            String country = UpdateInfo_CountryTextField.getText();

            Response response = controller.updatePassenger(
                    idText,
                    firstName,
                    lastName,
                    yearText,
                    monthText,
                    dayText,
                    phoneCodeText,
                    phoneText,
                    country
            );

            if (response.getStatus() == Status.OK) {
                JOptionPane.showMessageDialog(this, "Pasajero actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                UpdateInfo_IdTextField.setText("");
                UpdateInfo_FirstNameTextField.setText("");
                UpdateInfo_LastNameTextField.setText("");
                UpdateInfo_YearTextField.setText("");
                UpdateInfo_MonthTextField.setSelectedIndex(0);
                UpdateInfo_DayTextField.setSelectedIndex(0);
                UpdateInfo_NumberCodeTextField.setText("");
                UpdateInfo_NumberTextField.setText("");
                UpdateInfo_CountryTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_UpdateInfo_UpdateButtonActionPerformed

    private void AddToFlight_AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToFlight_AddButtonActionPerformed
        try {
            String passengerId = AddToFlight_IdTextField.getText();
            String flightComplete = AddToFlight_ChooseFlight.getItemAt(AddToFlight_ChooseFlight.getSelectedIndex());
            String flightId = flightComplete.substring(0, 6);

            PassengerController controller = new PassengerController();
            Response response = controller.addPassengerToFlight(passengerId, flightId);

            if (response.getStatus() == Status.OK) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddToFlight_AddButtonActionPerformed
    private void loadFlightIds() throws Exception {
        FlightController controller = new FlightController();
        List<Flight> flights = controller.getAllFlights(); // Asume que tienes este método

        DelayFlight_ChooseId.removeAllItems(); // Limpiar items existentes

        for (Flight flight : flights) {
            DelayFlight_ChooseId.addItem(flight.getId());
        }
    }

    private void DelayFlight_DelayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelayFlight_DelayButtonActionPerformed
        try {
            // Obtener parámetros del formulario
            String flightId = (String) DelayFlight_ChooseId.getSelectedItem();
            int hours = Integer.parseInt(DelayFlight_ChooseHour.getSelectedItem().toString());
            int minutes = Integer.parseInt(DelayFlight_ChooseMinute.getSelectedItem().toString());

            // Usar el controlador
            FlightController controller = new FlightController();
            Response response = controller.delayFlight(flightId, String.valueOf(hours), String.valueOf(minutes));

            // Manejar la respuesta
            if (response.getStatus() == Status.OK) {
                JOptionPane.showMessageDialog(this,
                        response.getMessage(),
                        "Retraso aplicado",
                        JOptionPane.INFORMATION_MESSAGE);

                // Actualizar la lista de vuelos en la interfaz
                refreshFlightLists();
            } else {
                JOptionPane.showMessageDialog(this,
                        response.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Horas y minutos deben ser números válidos",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_DelayFlight_DelayButtonActionPerformed
    private void refreshFlightLists() throws Exception {
        loadFlightIds();
        // Actualizar ComboBox de selección de vuelos
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        FlightStorage.getInstance().getAllFlights()
                .forEach(f -> model.addElement(f.getId()));
        DelayFlight_ChooseId.setModel(model);

        // Actualizar otras vistas que muestren vuelos si es necesario
        ShowAllFlights_RefreshButtonActionPerformed(null);
    }
    private void ShowMyFlights_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowMyFlights_RefreshButtonActionPerformed
        try {
            // Obtener el ID del pasajero seleccionado
            String passengerIdStr = Administration_SelectUser.getItemAt(Administration_SelectUser.getSelectedIndex()).toString();

            // Crear controlador y obtener respuesta
            PassengerController controller = new PassengerController();
            Response response = controller.getPassengerFlights(passengerIdStr);

            // Manejar la respuesta del controlador
            if (response.getStatus() == Status.OK) {
                // Mostrar los vuelos en la tabla
                DefaultTableModel model = (DefaultTableModel) ShowMyFlights_Table.getModel();
                model.setRowCount(0);

                @SuppressWarnings("unchecked")
                List<Flight> flights = (List<Flight>) response.getObject();

                if (flights.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El pasajero no tiene vuelos registrados",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (Flight flight : flights) {
                        model.addRow(new Object[]{
                            flight.getId(),
                            flight.getDepartureDate(),
                            flight.calculateArrivalDate()
                        });
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ShowMyFlights_RefreshButtonActionPerformed

    private void ShowAllPassengers_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllPassengers_RefreshButtonActionPerformed
        try {
            PassengerController controller = new PassengerController();
            DefaultTableModel model = (DefaultTableModel) ShowAllPassengers_Table.getModel();
            model.setRowCount(0); // Limpiar tabla

            // Obtener pasajeros desde el Storage (no de this.passengers)
            //List<Passenger> passengers = PassengerStorage.getInstance().getAllPassengers();
            // Obtener todos los IDs ordenados
            List<Passenger> sortedPassengers = controller.getAllPassengersSorted();
            // Actualizar el JComboBox
            updatePassengerComboBox(sortedPassengers);
            // Formateador de fecha
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Passenger passenger : sortedPassengers) {
                model.addRow(new Object[]{
                    passenger.getId(),
                    passenger.getFirstname() + " " + passenger.getLastname(), // o passenger.getFullname() si existe
                    passenger.getBirthDate().format(dateFormatter), // Fecha formateada
                    passenger.calculateAge(), // Asumiendo que existe este método
                    "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone(),
                    passenger.getCountry(),
                    passenger.getFlights().size() // Número de vuelos
                });
            }

            // Opcional: Mostrar mensaje si no hay pasajeros
            if (sortedPassengers.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay pasajeros registrados", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar pasajeros: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ShowAllPassengers_RefreshButtonActionPerformed

    private void ShowAllFlights_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllFlights_RefreshButtonActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) ShowAllFlights_Table.getModel();
            model.setRowCount(0);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (Flight flight : FlightStorage.getInstance().getAllFlights()) {
                model.addRow(new Object[]{
                    flight.getId(),
                    flight.getDepartureLocation().getAirportId(),
                    flight.getArrivalLocation().getAirportId(),
                    (flight.getScaleLocation() == null ? "-"
                    : flight.getScaleLocation().getAirportId()),
                    flight.getDepartureDate().format(dateFormatter),
                    flight.calculateArrivalDate().format(dateFormatter),
                    flight.getPlane().getId(),
                    flight.getPassengers().size()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar vuelos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ShowAllFlights_RefreshButtonActionPerformed

    private void ShowAllPlanes_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllPlanes_RefreshButtonActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) ShowAllPlanes_Table.getModel();
            model.setRowCount(0);

            for (Plane plane : PlaneStorage.getInstance().getAllPlanes()) {
                model.addRow(new Object[]{
                    plane.getId(),
                    plane.getBrand(),
                    plane.getModel(),
                    plane.getMaxCapacity(),
                    plane.getAirline(),
                    plane.getFlights().size(),
                    (plane.getFlights().size() > 0
                    ? String.format("%.1f%%", (double) plane.getFlights().size() / plane.getMaxCapacity() * 100) : "0%")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar aviones: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ShowAllPlanes_RefreshButtonActionPerformed

    private void ShowAllLocations_RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllLocations_RefreshButtonActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) ShowAllLocations_Table.getModel();
            model.setRowCount(0);

            for (Location location : LocationStorage.getInstance().getAllLocations()) {
                model.addRow(new Object[]{
                    location.getAirportId(),
                    location.getAirportName(),
                    location.getAirportCity(),
                    location.getAirportCountry(),
                    String.format("%.6f, %.6f", location.getAirportLatitude(), location.getAirportLongitude()),
                    location.getDepartureFlights().size() + location.getArrivalFlights().size()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar ubicaciones: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ShowAllLocations_RefreshButtonActionPerformed

    private void ClosePageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosePageButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ClosePageButtonActionPerformed

    private void Administration_SelectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Administration_SelectUserActionPerformed
        try {

            String id = Administration_SelectUser.getSelectedItem().toString();
            if (!id.equals(Administration_SelectUser.getItemAt(0))) {
                UpdateInfo_IdTextField.setText(id);
                AddToFlight_IdTextField.setText(id);
            } else {
                UpdateInfo_IdTextField.setText("");
                AddToFlight_IdTextField.setText("");
            }
        } catch (Exception e) {
            // Es mejor registrar el error
            System.err.println("Error al actualizar selección: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_Administration_SelectUserActionPerformed

    private void FlightRegistration_IdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightRegistration_IdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FlightRegistration_IdTextFieldActionPerformed

    private void FlightRegistration_ChooseArrivalLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightRegistration_ChooseArrivalLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FlightRegistration_ChooseArrivalLocationActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToFlight_AddButton;
    private javax.swing.JComboBox<String> AddToFlight_ChooseFlight;
    private javax.swing.JLabel AddToFlight_FlightLabel;
    private javax.swing.JLabel AddToFlight_IdLabel;
    private javax.swing.JTextField AddToFlight_IdTextField;
    private javax.swing.JComboBox<String> Administration_SelectUser;
    private javax.swing.JRadioButton Administration_User;
    private javax.swing.JRadioButton Administration_administrator;
    private javax.swing.JLabel AirplaneRegistration_AirLineLabel;
    private javax.swing.JTextField AirplaneRegistration_AirlineTextField;
    private javax.swing.JLabel AirplaneRegistration_BrandLabel;
    private javax.swing.JTextField AirplaneRegistration_BrandTextField;
    private javax.swing.JButton AirplaneRegistration_CreateButton;
    private javax.swing.JLabel AirplaneRegistration_IdLabel;
    private javax.swing.JTextField AirplaneRegistration_IdTextField;
    private javax.swing.JLabel AirplaneRegistration_MaxCapacityLabel;
    private javax.swing.JTextField AirplaneRegistration_MaxCapacityTextField;
    private javax.swing.JLabel AirplaneRegistration_ModelLabel;
    private javax.swing.JTextField AirplaneRegistration_ModelTextField;
    private javax.swing.JButton ClosePageButton;
    private javax.swing.JComboBox<String> DelayFlight_ChooseHour;
    private javax.swing.JComboBox<String> DelayFlight_ChooseId;
    private javax.swing.JComboBox<String> DelayFlight_ChooseMinute;
    private javax.swing.JButton DelayFlight_DelayButton;
    private javax.swing.JLabel DelayFlight_HoursLabel;
    private javax.swing.JLabel DelayFlight_IdLabel;
    private javax.swing.JLabel DelayFlight_MinutesLabel;
    private javax.swing.JLabel FlightRegistration_ArrivalLocationLabel;
    private javax.swing.JComboBox<String> FlightRegistration_ChooseArrivalLocation;
    private javax.swing.JComboBox<String> FlightRegistration_ChooseDay;
    private javax.swing.JComboBox<String> FlightRegistration_ChooseDepartureLocation;
    private javax.swing.JComboBox<String> FlightRegistration_ChooseMonth;
    private javax.swing.JComboBox<String> FlightRegistration_ChoosePlane;
    private javax.swing.JComboBox<String> FlightRegistration_ChooseScaleLocation;
    private javax.swing.JButton FlightRegistration_CreateButton;
    private javax.swing.JComboBox<String> FlightRegistration_DepartureDateHour;
    private javax.swing.JLabel FlightRegistration_DepartureDateLabel;
    private javax.swing.JComboBox<String> FlightRegistration_DepartureDateMinute;
    private javax.swing.JLabel FlightRegistration_DepartureLocationLabel;
    private javax.swing.JComboBox<String> FlightRegistration_Duration1Hour;
    private javax.swing.JComboBox<String> FlightRegistration_Duration1Minute;
    private javax.swing.JComboBox<String> FlightRegistration_Duration2Hour;
    private javax.swing.JLabel FlightRegistration_Duration2Label;
    private javax.swing.JComboBox<String> FlightRegistration_Duration2Minute;
    private javax.swing.JLabel FlightRegistration_DurationLabel;
    private javax.swing.JLabel FlightRegistration_IdLabel;
    private javax.swing.JTextField FlightRegistration_IdTextField;
    private javax.swing.JLabel FlightRegistration_PlaneLabel;
    private javax.swing.JLabel FlightRegistration_ScaleLocationLabel;
    private javax.swing.JTextField FlightRegistration_Year;
    private javax.swing.JLabel LocationRegistration_CityLabel;
    private javax.swing.JTextField LocationRegistration_CityTextField;
    private javax.swing.JLabel LocationRegistration_CountryLabel;
    private javax.swing.JTextField LocationRegistration_CountryTextField;
    private javax.swing.JButton LocationRegistration_CreateButton;
    private javax.swing.JLabel LocationRegistration_IdLabel;
    private javax.swing.JTextField LocationRegistration_IdTextField;
    private javax.swing.JLabel LocationRegistration_LatitudeLabel;
    private javax.swing.JTextField LocationRegistration_LatitudeTextField;
    private javax.swing.JLabel LocationRegistration_LongitudeLabel;
    private javax.swing.JTextField LocationRegistration_LongitudeTextField;
    private javax.swing.JLabel LocationRegistration_NameLabel;
    private javax.swing.JTextField LocationRegistration_NameTextField;
    private javax.swing.JLabel PassangerRegistration_BirthdateLabel;
    private javax.swing.JComboBox<String> PassangerRegistration_ChooseDay;
    private javax.swing.JComboBox<String> PassangerRegistration_ChooseMonth;
    private javax.swing.JTextField PassangerRegistration_CodeNumberTextField;
    private javax.swing.JLabel PassangerRegistration_CountryLabel;
    private javax.swing.JTextField PassangerRegistration_CountryTextField;
    private javax.swing.JLabel PassangerRegistration_FirstNameLabel;
    private javax.swing.JTextField PassangerRegistration_FirstNameTextField;
    private javax.swing.JLabel PassangerRegistration_IdLabel;
    private javax.swing.JTextField PassangerRegistration_IdTextField;
    private javax.swing.JLabel PassangerRegistration_LastNameLabel;
    private javax.swing.JTextField PassangerRegistration_LastNameTextField;
    private javax.swing.JTextField PassangerRegistration_NumberTextField;
    private javax.swing.JLabel PassangerRegistration_PhoneLabel;
    private javax.swing.JButton PassangerRegistration_RegisterButton;
    private javax.swing.JTextField PassangerRegistration_YearTextField;
    private javax.swing.JTabbedPane PrincipalPanel;
    private javax.swing.JButton ShowAllFlights_RefreshButton;
    private javax.swing.JTable ShowAllFlights_Table;
    private javax.swing.JButton ShowAllLocations_RefreshButton;
    private javax.swing.JTable ShowAllLocations_Table;
    private javax.swing.JButton ShowAllPassengers_RefreshButton;
    private javax.swing.JTable ShowAllPassengers_Table;
    private javax.swing.JButton ShowAllPlanes_RefreshButton;
    private javax.swing.JTable ShowAllPlanes_Table;
    private javax.swing.JButton ShowMyFlights_RefreshButton;
    private javax.swing.JTable ShowMyFlights_Table;
    private javax.swing.JLabel UpdateInfo_BirthdateLabel;
    private javax.swing.JLabel UpdateInfo_CountryLabel;
    private javax.swing.JTextField UpdateInfo_CountryTextField;
    private javax.swing.JComboBox<String> UpdateInfo_DayTextField;
    private javax.swing.JLabel UpdateInfo_FirstNameLabel;
    private javax.swing.JTextField UpdateInfo_FirstNameTextField;
    private javax.swing.JLabel UpdateInfo_IdLabel;
    private javax.swing.JTextField UpdateInfo_IdTextField;
    private javax.swing.JLabel UpdateInfo_LastNameLabel;
    private javax.swing.JTextField UpdateInfo_LastNameTextField;
    private javax.swing.JComboBox<String> UpdateInfo_MonthTextField;
    private javax.swing.JTextField UpdateInfo_NumberCodeTextField;
    private javax.swing.JTextField UpdateInfo_NumberTextField;
    private javax.swing.JLabel UpdateInfo_PhoneLabel;
    private javax.swing.JButton UpdateInfo_UpdateButton;
    private javax.swing.JTextField UpdateInfo_YearTextField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private airport.views.PanelRound panelRound1;
    private airport.views.PanelRound panelRound2;
    private airport.views.PanelRound panelRound3;
    // End of variables declaration//GEN-END:variables

}
