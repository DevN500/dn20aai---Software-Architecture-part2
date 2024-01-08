import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class CompetitorList {
    private ArrayList<Competitor> competitors;

    // Constructor
    //public methods
    public CompetitorList() {
        this.competitors = new ArrayList<Competitor>();
    }
    public CompetitorList(String filename) {
        this.competitors = new ArrayList<Competitor>();
        this.LoadData(filename);
    }

    // Generic method to read competitors from a file
    public void LoadData(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Competitor competitor = parseCompetitor(line);
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
    private Competitor parseCompetitor(String line) {
        String[] data = line.split(",");

        if (data.length >= 10) {
            String CompetitorNumber = data[0];
            String[] nameParts = data[1].trim().split(" ");
            String name;

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

            LevelOfCompetitor level = LevelOfCompetitor.valueOf(data[2]);
            int Age = Integer.parseInt(data[3]);
            String Gender = data[4];
            String country = data[5];

            ArrayList<Integer> scores = new ArrayList<>();
            // Start from index 6 to parse scores
            for (int i = 6; i < data.length; i++) {
                if (!data[i].isEmpty()) {  // Ensure non-empty score data
                    scores.add(Integer.parseInt(data[i]));
                }
            }

            Competitor competitor = new LongJump(CompetitorNumber, competitorName, level, Age, Gender, country);

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
        csvLine.append(competitor.getLevel().toString()).append(",");
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
    public void LoadLongJumpCompetitorsFromFile(String filename) {
        LoadData(filename);
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
            System.out.println("An error occurred while writing to the file: " + filename);
            e.printStackTrace();
        }
    }

    public ArrayList<Competitor> getCompetitorList() {
        return this.competitors;
    }

    public boolean addCompetitor(Competitor competitor) {
        // Check if the competitor is an instance of IceSkatingCompetitor or JavelinThrowCompetitor
        if (competitor instanceof LongJump ) {
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

    public void alterCompetitorDetails(int CompetitorNumber, Name newName, LevelOfCompetitor newLevel, int newAge, String newGender, String newCountry) {
        for (Competitor competitor : competitors) {
            if (competitor.getCompetitorNumber().equals(CompetitorNumber)) {
                competitor.setCompetitorName(newName.getFirstName(), newName.getMiddleName(), newName.getLastName());
                competitor.setLevel(newLevel);
                competitor.setAge(newAge);
                competitor.setGender(newGender);
                competitor.setCountry(newCountry);
                break;
            }
        }
    }

    public Competitor getCompetitorByID(int CompetitorNumber) {
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




    public String getFullCompetitorDetails() {
        StringBuilder details = new StringBuilder();
        for (Competitor competitor : competitors) {
            details.append(competitor.getFullDetails()).append("\n");
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
    public Competitor getHighestOverallScore() {
        Competitor HighestOverallScore = null;
        for (Competitor competitor : competitors) {
            if (HighestOverallScore == null || competitor.getOverallScore() > HighestOverallScore.getOverallScore()) {
                HighestOverallScore = competitor;
            }
        }
        return HighestOverallScore;
    }


    // Example method for a summary statistic - average score
    public double getTotal() {
        double totalScore = 0;
        for (Competitor competitor : competitors) {
            totalScore += competitor.getOverallScore();
        }
        return totalScore;
    }


    // Example method for a summary statistic - average score
    public double getAverage() {
        double totalScore = 0;
        for (Competitor competitor : competitors) {
            totalScore += competitor.getOverallScore();
        }
        return totalScore / competitors.size();
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



    public String generateReport() {
        String report = "Full Details of Competitors \n" + this.getFullCompetitorDetails();
        report += "\nDetails of the competitor with the highest overall score  " + this.getHighestOverallScore();
        report += "\nSummary Statistics of Competition";
        report += "\nAverage Score of Competition is  " + this.getAverage();
        report += "\nMax Score of Competition is " + this.getMaxScore();
        report += "\nMin Score of Competition is " + this.getMinScore();
        report += "\nTotal Score of the Competition is " + this.getTotal();
        report += "\nFrequency Report " + this.getScoreFrequency().toString();

        System.out.println(report);
        return report;
    }

    public String generateLimitedReport() {
        String report = "Competitor Limited Details:\n" + this.getAllShortDetails();
        report += "\n-------Score Statistics:-------";
        report += "\nAverage Score: " + this.getAverage();
        report += "\nMax Score: " + this.getMaxScore();
        report += "\nMin Score: " + this.getMinScore();
        // Output the report to a file or System.out
        System.out.println(report); // Temporary for debugging
        return report;
    }

}