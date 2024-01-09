import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField staffIDField;
    private StaffList staffList;
    private CompetitorList competitorList;

    public GUI(StaffList staffList, CompetitorList competitorList) {
        this.staffList = staffList;
        this.competitorList = competitorList;
        setTitle("Manage Competitors");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        Menu();
        StaffSections();

        add(cardPanel);
    }

    private void Menu() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel idInputPanel = new JPanel();
        JLabel idInputLabel = new JLabel("Please type in your Staff ID ");
        staffIDField = new JTextField(20);
        JButton submitButton = new JButton("Enter ");
        submitButton.addActionListener(e -> processStaffID());

        idInputPanel.add(idInputLabel);
        idInputPanel.add(staffIDField);
        idInputPanel.add(submitButton);
        mainPanel.add(idInputPanel, BorderLayout.SOUTH);


        JPanel filePanel = new JPanel(new GridLayout(1, 1));

        JButton loadButton = new JButton("Load data from csv file");
        loadButton.addActionListener(e -> loadCompetitors());
        filePanel.add(loadButton);

        mainPanel.add(filePanel, BorderLayout.NORTH);
        cardPanel.add(mainPanel, "Main");
    }

    private void processStaffID() {
        String staffID = staffIDField.getText();
        Staff staff = staffList.getStaffFromID(staffID);

        if (staff != null) {
            cardLayout.show(cardPanel, staff.getStaffType().toString());
        } else {
            JOptionPane.showMessageDialog(GUI.this, "Staff ID is invalid");
        }
    }

    private void loadCompetitors() {
        String filename = JOptionPane.showInputDialog(this, "Enter filename to load competitors of LongJump:", "Load Competitors", JOptionPane.QUESTION_MESSAGE);
        if (filename != null && !filename.trim().isEmpty()) {
            Object[] options = {"LongJump"};
            String Competition = (String) JOptionPane.showInputDialog(this, "Select Competition ", "Load Competitors", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (Competition != null) {
                competitorList.LoadData(filename.trim(), Competition);
                JOptionPane.showMessageDialog(this, "Competitors loaded from " + filename, "Data Loading Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void StaffSections() {
        JPanel emergency = BackButton(Color.RED, "Emergency");
        JPanel official = BackButton(Color.BLUE, "Official");

        cardPanel.add(emergency, "Emergency");
        cardPanel.add(official, "Official");
    }

    private JPanel BackButton(Color backgroundColor, String name) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(backgroundColor);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        switch (name) {
            case "Official":
                JPanel OfficialPanel = new JPanel(new GridLayout(5, 1));
                addOfficialPanelButtons(OfficialPanel);
                panel.add(OfficialPanel, BorderLayout.CENTER);
                break;
            default:
                break;
        }

        return panel;
    }


    private void addOfficialPanelButtons(JPanel officialPanel) {
        JButton searchCompetitorButton = new JButton("Details of Competitors");
        searchCompetitorButton.addActionListener(e -> searchForCompetitor());
        officialPanel.add(searchCompetitorButton);

        JButton addScoresButton = new JButton("Add Scores for a Competitor");
        addScoresButton.addActionListener(e -> addingScoresForCompetitor());
        officialPanel.add(addScoresButton);

        JButton viewResultsButton = new JButton("View Scores for a Competitor");
        viewResultsButton.addActionListener(e -> searchCompetitorforScores());
        officialPanel.add(viewResultsButton);

        JButton removeCompetitorButton = new JButton("Remove a Competitor");
        removeCompetitorButton.addActionListener(e -> removeCompetitor());
        officialPanel.add(removeCompetitorButton);

        JButton alterCompetitorDetails = new JButton("Alter existing Competitor Details");
        alterCompetitorDetails.addActionListener(e -> alterCompetitorDetail());
        officialPanel.add(alterCompetitorDetails);

        JButton showGamesButton = new JButton("Show Report");
        showGamesButton.addActionListener(e -> showAllGamesReport());
        officialPanel.add(showGamesButton);

        JButton showGamesLimitedButton = new JButton("Show summarized Report");
        showGamesLimitedButton.addActionListener(e -> showAllGamesLimitedReport());
        officialPanel.add(showGamesLimitedButton);
    }

    private void showAllGamesLimitedReport(){
        String report = competitorList.generateReport();


        JTextArea textArea = new JTextArea(15, 50);
        textArea.setText(report);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Summarized Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAllGamesReport() {
        String report = competitorList.generateReport();


        JTextArea textArea = new JTextArea(15, 50);
        textArea.setText(report);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Show Report", JOptionPane.INFORMATION_MESSAGE);
    }


    private void searchForCompetitor() {
        String competitorID = JOptionPane.showInputDialog(this, "Please type Competitor number", "Search Competitor", JOptionPane.QUESTION_MESSAGE);

        if (competitorID != null && !competitorID.trim().isEmpty()) {
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());

            if (competitor != null) {
                // Check if competitor has scores
                if (!competitor.hasScores()) {
                    JOptionPane.showMessageDialog(this, "Competitor has no scores", "No Scores", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int response = JOptionPane.showConfirmDialog(this, "Would you like to view full details for a competitor", "Competitor Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    viewCompetitorDetails(competitor);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor has been found with this number: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please type a valid Competitor number", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void viewCompetitorDetails(Competitor competitor) {
        String details = competitorList.getCompetitorDetails(competitor);
        JOptionPane.showMessageDialog(this, details, "Competitor Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchCompetitorforScores() {
        String competitorID = JOptionPane.showInputDialog(this, "Please type Competitor number", "Search Competitor", JOptionPane.QUESTION_MESSAGE);

        if (competitorID != null && !competitorID.trim().isEmpty()) {
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());

            if (competitor != null) {

                int response = JOptionPane.showConfirmDialog(this, "Would you like to view all Scores?", "Competitor Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {

                    viewCompetitorScores(competitor);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor has been found with this number " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please type in a valid Competitor number", "Invalid", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void viewCompetitorScores(Competitor competitor) {
        String details = competitorList.getCompetitorScores(competitor);
        JOptionPane.showMessageDialog(this, details, "Competitor Scores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addingScoresForCompetitor(){

        String competitorID = JOptionPane.showInputDialog(this, "Please type in a  Competitor number", "Add Scores", JOptionPane.QUESTION_MESSAGE);
        if (competitorID != null && !competitorID.trim().isEmpty()) {
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());
            if (competitor != null) {

                String scoreInput = JOptionPane.showInputDialog(this, "Enter scores using commas after each score", "Add Scores", JOptionPane.QUESTION_MESSAGE);
                if (scoreInput != null && !scoreInput.trim().isEmpty()) {
                    String[] scoresStr = scoreInput.split(",");
                    try {
                        for (String scoreStr : scoresStr) {
                            int score = Integer.parseInt(scoreStr.trim());
                            competitor.addScore(score);
                        }
                        JOptionPane.showMessageDialog(this, "Scores have been added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid score format. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor found with this number " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor number.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void removeCompetitor() {

        String competitorID = JOptionPane.showInputDialog(this, "PLease type Competitor number to remove ", "Remove Competitor", JOptionPane.QUESTION_MESSAGE);

        if (competitorID != null && !competitorID.trim().isEmpty()) {

            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());
            if (competitor != null) {

                competitorList.removeCompetitor(competitor);
                JOptionPane.showMessageDialog(this, "Competitor with number " + competitorID + " has been removed.", "Competitor Removed", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(this, "No competitor found with number " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {

            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor number.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void alterCompetitorDetail() {
        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor number to alter:", "Alter Competitor Details", JOptionPane.QUESTION_MESSAGE);
        if (competitorID == null || competitorID.trim().isEmpty()) return;

        // Check to see if the competitor exists
        Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());
        if (competitor == null) {
            JOptionPane.showMessageDialog(this, "No competitor found with number " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            return;
        }


        String firstName = JOptionPane.showInputDialog(this, "Please type new First Name:", competitor.getName().getFirstName());
        String middleName = JOptionPane.showInputDialog(this, "Please type new Middle Name (optional):", competitor.getName().getMiddleName());
        String lastName = JOptionPane.showInputDialog(this, "Please type new Last Name:", competitor.getName().getLastName());
        String levelStr = JOptionPane.showInputDialog(this, "Please type new level", competitor.getLevelOfCompetitor().toString());
        String ageStr = JOptionPane.showInputDialog(this, "Please type  new Age:", Integer.toString(competitor.getAge()));
        String gender = JOptionPane.showInputDialog(this, "Please type new Gender:", competitor.getGender());
        String country = JOptionPane.showInputDialog(this, "Please type new Country:", competitor.getCountry());

        // Validate and parse inputs
        if (firstName == null || lastName == null || levelStr == null || ageStr == null || gender == null || country == null) {
            JOptionPane.showMessageDialog(this, "Invalid Information", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Level currentLevel = competitor.getLevelOfCompetitor();
        Level[] levels = Level.values();
        Level newLevel = (Level) JOptionPane.showInputDialog(this, "Select new Level:", "Alter Competitor Details", JOptionPane.QUESTION_MESSAGE, null, levels, currentLevel);

        if (newLevel == null) {
            JOptionPane.showMessageDialog(this, "Level selection is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newAge;
        try {
            newAge = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update competitor details
        Name newName = new Name(firstName.trim(), lastName.trim(), middleName != null ? middleName.trim() : "");
        competitorList.alterCompetitorDetails(competitorID.trim(), newName, newLevel, newAge, gender.trim(), country.trim());
        JOptionPane.showMessageDialog(this, "Competitor details updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
    }
}