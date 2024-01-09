import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
public class CompetitorList {
    private ArrayList<Competitor> competitors;

    // Constructor
    //public methods
    public CompetitorList() {
        this.competitors = new ArrayList<Competitor>();
    }
    public CompetitorList(String CompetitionType) {
        String filename = "src/LongJumpCompetitors.csv";
        this.competitors = new ArrayList<Competitor>();
        this.LoadData(filename, CompetitionType);
    }

    // Generic method to read competitors from a file
    public void LoadData(String filename, String type) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Competitor competitor = parseCompetitor(line, type);
                if (competitor != null) {
                    competitors.add(competitor);
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
            String CompetitorNumber = data[0];
            String[] nameParts = data[1].trim().split(" ");
            Name name;

            // Parsing name based on the number of parts
            switch (nameParts.length) {
                case 2:
                    // Assuming format: First Name, Last Name
                    name = new Name(nameParts[0], nameParts[1]);
                    break;
                case 3:
                    // Assuming format: First Name, Middle Name, Last Name
                    name = new Name(nameParts[0], nameParts[2], nameParts[1]);
                    break;
                default:
                    // Handle other cases or throw an exception
                    throw new IllegalArgumentException("N/A " + data[1]);
            }

            Level LevelOfCompetitor = Level.valueOf(data[2]);
            int Age = Integer.parseInt(data[3]);
            String Gender = data[4];
            String Country = data[5];

            ArrayList<Integer> scores = new ArrayList<>();
            // Start from index 6 to parse scores
            for (int i = 6; i < data.length; i++) {
                if (!data[i].isEmpty()) {  // Ensure non-empty score data
                    scores.add(Integer.parseInt(data[i]));
                }
            }

            Competitor competitor;

            if ("LongJump".equals(type)) {
                competitor = new LongJumpCompetitor(CompetitorNumber, name, LevelOfCompetitor, Age, Gender, Country);
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
        csvLine.append(competitor.getCompetitorNumber()).append(",");
        csvLine.append(competitor.getName().getFullName()).append(",");
        csvLine.append(competitor.getLevelOfCompetitor().toString()).append(",");
        csvLine.append(competitor.getAge()).append(",");
        csvLine.append(competitor.getGender()).append(",");
        csvLine.append(competitor.getCountry());

        // Append scores
        ArrayList<Integer> scores = competitor.getScoreArray();
        for (int score : scores) {
            csvLine.append(",").append(score);
        }

        return csvLine.toString();
    }

    // Method to load Ice Skating Competitors
    public void loadLongJumpCompetitorsFromFile(String filename) {
        LoadData(filename, "LongJump");
    }

    // Method to save competitors to a file
    public void saveCompetitorsToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Competitor competitor : competitors) {
                writer.write(formatCompetitorForCsv(competitor) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not Write to file " + filename);
            e.printStackTrace();
        }
    }

    public ArrayList<Competitor> getCompetitorList() {
        return this.competitors;
    }

    public boolean addCompetitor(Competitor competitor) {
        if (competitor instanceof LongJumpCompetitor) {
            // Check if a competitor with the same ID already exists in the list
            for (Competitor existingCompetitor : competitors) {
                if (existingCompetitor.getCompetitorNumber().equals(competitor.getCompetitorNumber())) {
                    // Competitor with this ID already exists, do not add
                    return false;
                }
            }
            // Competitor is unique, add to list
            competitors.add(competitor);
            return true;
        }
        // If competitor is not of the specified types, return false or handle differently
        return false;
    }


    public void removeCompetitor(Competitor competitor) {
        this.competitors.remove(competitor);
    }

    public void alterCompetitorDetails(String CompetitorNumber, Name newName, Level newLevel, int newAge, String newGender, String newCountry) {
        for (Competitor competitor : competitors) {
            if (competitor.getCompetitorNumber().equals(CompetitorNumber)) {
                competitor.setName(newName.getFirstName(), newName.getMiddleName(), newName.getLastName());
                competitor.setLevelOfCompetitor(newLevel);
                competitor.setAge(newAge);
                competitor.setGender(newGender);
                competitor.setCountry(newCountry);
                break;
            }
        }
    }

    public Competitor getCompetitorByID(String CompetitorNumber) {
        for (Competitor competitor : competitors) {
            if (competitor.getCompetitorNumber().equals(CompetitorNumber)) {
                return competitor;
            }
        }
        return null;
    }

    public String getCompetitorDetails(Competitor competitor) {
        return competitor.getFullDetails();
    }


    // Method to get full details of all competitors
    public String getAllCompetitorDetails() {
        StringBuilder details = new StringBuilder();
        for (Competitor competitor : competitors) {
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
        for (Competitor competitor : competitors) {
            details.append(competitor.getShortDetails()).append("\n");
            details.append("------------------------------- \n");
        }
        return details.toString();
    }

    // Method to find the competitor with the highest score
    public Competitor getTopCompetitor() {
        Competitor topCompetitor = null;
        for (Competitor competitor : competitors) {
            if (topCompetitor == null || competitor.getOverallScore() > topCompetitor.getOverallScore()) {
                topCompetitor = competitor;
            }
        }
        return topCompetitor;
    }

    // Example method for a summary statistic - average score
    public double getAverageScore() {
        double totalScore = 0;
        for (Competitor competitor : competitors) {
            totalScore += competitor.getOverallScore();
        }
        return competitors.isEmpty() ? 0 : totalScore / competitors.size();
    }

    // Method to generate a frequency report of scores
    public Map<Integer, Integer> getScoreFrequency() {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Competitor competitor : competitors) {
            for (int score : competitor.getScoreArray()) {
                frequency.put(score, frequency.getOrDefault(score, 0) + 1);
            }
        }
        return frequency;
    }

    public int getMaxScore() {
        int maxScore = Integer.MIN_VALUE;
        for (Competitor competitor : competitors) {
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
        for (Competitor competitor : competitors) {
            for (int score : competitor.getScoreArray()) {
                if (score < minScore) {
                    minScore = score;
                }
            }
        }
        return minScore;
    }


    public String generateReport() {
        String report = "Competitor Details:\n" + this.getAllCompetitorDetails();
        report += "\nTop Competitor - \n" + (this.getTopCompetitor() != null ? this.getTopCompetitor().getFullDetails() : "No competitors found.");
        report += "\nScore Statistics: ";
        report += "\nAverage - " + this.getAverageScore();
        report += "\nMax Score -  " + this.getMaxScore();
        report += "\nMin Score -  " + this.getMinScore();
        report += "\nFrequency Report -  " + this.getScoreFrequency().toString();
        // Output the report to a file or System.out
        System.out.println(report);
        return report;
    }


}