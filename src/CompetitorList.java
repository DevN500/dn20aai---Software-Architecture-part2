import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
public class CompetitorList {
    private ArrayList<Competitor> competitorList;

    // Constructor
    //public methods
    public CompetitorList() {
        this.competitorList = new ArrayList<Competitor>();
    }
    public CompetitorList(String competitorType) {
        String filename = "src/LongJumpCompetition.csv";
        this.competitorList = new ArrayList<Competitor>();
        this.loadCompetitorsFromFile(filename, competitorType);
    }

    // Generic method to read competitors from a file
    public void loadCompetitorsFromFile(String filename, String type) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Competitor competitor = parseCompetitor(line, type);
                if (competitor != null) {
                    competitorList.add(competitor);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    // Method to parse a line of CSV and create a Competitor object
    private Competitor parseCompetitor(String line, String type) {
        String[] data = line.split(",");

        if (data.length >= 10) {
            String competitorID = data[0];
            String[] nameParts = data[1].trim().split(" ");
            Name competitorName;

            // Parsing name based on the number of parts
            switch (nameParts.length) {
                case 2:
                    // Assuming format: First Name, Last Name
                    competitorName = new Name(nameParts[0], nameParts[1]);
                    break;
                case 3:
                    // Assuming format: First Name, Middle Name, Last Name
                    competitorName = new Name(nameParts[0], nameParts[2], nameParts[1]);
                    break;
                default:
                    // Handle other cases or throw an exception
                    throw new IllegalArgumentException("Invalid name format: " + data[1]);
            }

            Level competitorLevel = Level.valueOf(data[2]);
            int competitorAge = Integer.parseInt(data[3]);
            String competitorGender = data[4];
            String competitorCountry = data[5];

            ArrayList<Integer> scores = new ArrayList<>();
            // Start from index 6 to parse scores
            for (int i = 6; i < data.length; i++) {
                if (!data[i].isEmpty()) {  // Ensure non-empty score data
                    scores.add(Integer.parseInt(data[i]));
                }
            }

            Competitor competitor;

            if ("IceSkating".equals(type)) {
                competitor = new LongJump(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
            } else {
                return null; // Or throw an exception for unknown type
            }

            // Add scores to the competitor
            for (int score : scores) {
                competitor.addScore(score);
            }

            return competitor;
        }
        return null;
    }

    // Helper method to format a Competitor object into a CSV line
    private String formatCompetitorForCsv(Competitor competitor) {
        StringBuilder csvLine = new StringBuilder();
        csvLine.append(competitor.getCompetitorID()).append(",");
        csvLine.append(competitor.getCompetitorName().getFullName()).append(",");
        csvLine.append(competitor.getCompetitorLevel().toString()).append(",");
        csvLine.append(competitor.getCompetitorAge()).append(",");
        csvLine.append(competitor.getCompetitorGender()).append(",");
        csvLine.append(competitor.getCompetitorCountry());

        // Append scores
        ArrayList<Integer> scores = competitor.getScoreArray();
        for (int score : scores) {
            csvLine.append(",").append(score);
        }

        return csvLine.toString();
    }

    // Method to load Ice Skating Competitors
    public void loadIceSkatingCompetitorsFromFile(String filename) {
        loadCompetitorsFromFile(filename, "IceSkating");
    }

    // Method to load Javelin Throw Competitors
    // Method to save competitors to a file
    public void saveCompetitorsToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Competitor competitor : competitorList) {
                writer.write(formatCompetitorForCsv(competitor) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + filename);
            e.printStackTrace();
        }
    }

    public ArrayList<Competitor> getCompetitorList() {
        return this.competitorList;
    }

    public boolean addCompetitor(Competitor competitor) {
        // Check if the competitor is an instance of IceSkatingCompetitor or JavelinThrowCompetitor
        if (competitor instanceof LongJump) {
            // Check if a competitor with the same ID already exists in the list
            for (Competitor existingCompetitor : competitorList) {
                if (existingCompetitor.getCompetitorID().equals(competitor.getCompetitorID())) {
                    // Competitor with this ID already exists, do not add
                    return false;
                }
            }
            // Competitor is unique, add to list
            competitorList.add(competitor);
            return true;
        }
        // If competitor is not of the specified types, return false or handle differently
        return false;
    }


    public void removeCompetitor(Competitor competitor) {
        this.competitorList.remove(competitor);
    }

    public void alterCompetitorDetails(String competitorID, Name newName, Level newLevel, int newAge, String newGender, String newCountry) {
        for (Competitor competitor : competitorList) {
            if (competitor.getCompetitorID().equals(competitorID)) {
                competitor.setCompetitorName(newName.getFirstName(), newName.getMiddleName(), newName.getLastName());
                competitor.setCompetitorLevel(newLevel);
                competitor.setCompetitorAge(newAge);
                competitor.setCompetitorGender(newGender);
                competitor.setCompetitorCountry(newCountry);
                break;
            }
        }
    }

    public Competitor getCompetitorByID(String competitorID) {
        for (Competitor competitor : competitorList) {
            if (competitor.getCompetitorID().equals(competitorID)) {
                return competitor;
            }
        }
        return null;
    }

    public String getCompetitorDetails(Competitor competitor) {
        return competitor.getFullDetails();
    }

    public String getCompetitorExtraDetails(Competitor competitor) {
        return competitor.getCompetitorExtraDetails();
    }

    // Method to get full details of all competitors
    public String getAllCompetitorDetails() {
        StringBuilder details = new StringBuilder();
        for (Competitor competitor : competitorList) {
            details.append(competitor.getFullDetails()).append("\n");
            details.append("------------------------------- \n");
        }
        return details.toString();
    }

    public String getCompetitorScores(Competitor competitor) {
        return competitor.getScoreArray().toString();
    }

    public String getAllShortDetails() {
        StringBuilder details = new StringBuilder();
        for (Competitor competitor : competitorList) {
            details.append(competitor.getShortDetails()).append("\n");
            details.append("------------------------------- \n");
        }
        return details.toString();
    }

    // Method to find the competitor with the highest score
    public Competitor getTopCompetitor() {
        Competitor topCompetitor = null;
        for (Competitor competitor : competitorList) {
            if (topCompetitor == null || competitor.getOverallScore() > topCompetitor.getOverallScore()) {
                topCompetitor = competitor;
            }
        }
        return topCompetitor;
    }

    // Example method for a summary statistic - average score
    public double getAverageScore() {
        double totalScore = 0;
        for (Competitor competitor : competitorList) {
            totalScore += competitor.getOverallScore();
        }
        return competitorList.isEmpty() ? 0 : totalScore / competitorList.size();
    }

    // Method to generate a frequency report of scores
    public Map<Integer, Integer> getScoreFrequency() {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Competitor competitor : competitorList) {
            for (int score : competitor.getScoreArray()) {
                frequency.put(score, frequency.getOrDefault(score, 0) + 1);
            }
        }
        return frequency;
    }

    public int getMaxScore() {
        int maxScore = Integer.MIN_VALUE;
        for (Competitor competitor : competitorList) {
            for (int score : competitor.getScoreArray()) {
                if (score > maxScore) {
                    maxScore = score;
                }
            }
        }
        return maxScore;
    }

    public int getMinScore() {
        int minScore = Integer.MAX_VALUE;
        for (Competitor competitor : competitorList) {
            for (int score : competitor.getScoreArray()) {
                if (score < minScore) {
                    minScore = score;
                }
            }
        }
        return minScore;
    }

    public Map<Integer, Integer> getScoreDistribution() {
        Map<Integer, Integer> scoreDistribution = new HashMap<>();
        for (Competitor competitor : competitorList) {
            for (int score : competitor.getScoreArray()) {
                scoreDistribution.put(score, scoreDistribution.getOrDefault(score, 0) + 1);
            }
        }
        return scoreDistribution;
    }

    public String generateReport() {
        String report = "Competitor Details:\n" + this.getAllCompetitorDetails();
        report += "\n-------Top Competitor:-------\n" + (this.getTopCompetitor() != null ? this.getTopCompetitor().getFullDetails() : "No competitors found.");
        report += "\n-------Score Statistics:-------";
        report += "\nAverage Score: " + this.getAverageScore();
        report += "\nMax Score: " + this.getMaxScore();
        report += "\nMin Score: " + this.getMinScore();
        report += "\nScore Distribution: " + this.getScoreDistribution().toString();
        report += "\nScore Frequency Report: " + this.getScoreFrequency().toString();
        // Output the report to a file or System.out
        System.out.println(report); // Temporary for debbugging
        return report;
    }

    public String generateLimitedReport() {
        String report = "Competitor Limited Details:\n" + this.getAllShortDetails();
        report += "\n-------Top Competitor:-------\n" + (this.getTopCompetitor() != null ? this.getTopCompetitor().getShortDetails() : "No competitors found.");
        report += "\n-------Score Statistics:-------";
        report += "\nAverage Score: " + this.getAverageScore();
        report += "\nMax Score: " + this.getMaxScore();
        report += "\nMin Score: " + this.getMinScore();
        report += "\nScore Distribution: " + this.getScoreDistribution().toString();
        // Output the report to a file or System.out
        System.out.println(report); // Temporary for debugging
        return report;
    }

}