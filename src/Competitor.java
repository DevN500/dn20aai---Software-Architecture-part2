import java.util.ArrayList;

public class Competitor {
    private int CompetitorNumber;
    private String name;

    private int Age;
    private String DateOfBirth;
    private String level;
    private String country;
    private String email;

    private String category;

    private int CompetitionNumber;

    private ArrayList<Integer> scores = new ArrayList<Integer>();




    public Competitor (int newCompetitorNumber, String newName, int newAge, String newDateOfBirth, String newLevel, String newCountry, String newEmail, String newCategory, int newCompetitionNumber, ArrayList<Integer> newScores){
        this.CompetitorNumber = newCompetitorNumber;
        this.name = newName;
        this.Age = newAge;
        this.DateOfBirth = newDateOfBirth;
        this.level = newLevel;
        this.country = newCountry;
        this.email = newEmail;
        this.category = newCategory;
        this.CompetitionNumber = newCompetitionNumber;
        this.scores = newScores;
    }

    public int getCompetitorNumber() {
        return CompetitorNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return Age;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public String getLevel() {
        return level;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getCategory() { return category; }

    public int getCompetitionNumber() { return CompetitionNumber; }

    public void setCompetitorNumber(int CompetitorNumber) {
        this.CompetitorNumber = CompetitorNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setDateOfBirth(String DoB) {
        this.DateOfBirth = DoB;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategory(String category) { this.category = category; }

    public void setCompetitionNumber(int CompetitionNumber) { this.CompetitionNumber = CompetitionNumber; }

    public String scoresToString(ArrayList<Integer> scores)
    {
        String line = String.valueOf(scores.get(0));
        for (int i = 1; i < scores.size(); i++) {
            line = line + ", "+ scores.get(i);
        }
        return line;
    }



    public double getOverallScore(ArrayList<Integer> driverScores) {
        int score = 0;

        for (int i = 0; i < driverScores.size(); i++) {
            score += driverScores.get(i);
        }

        return score;
    }

    public String getFullDetails() {
        String fullDetails = "competitor number: " + this.CompetitorNumber + " is " + this.level + ", " + this.name+ ". Born " + this.DateOfBirth + " from " + this.country + "\ntheir email is: " + this.email + ". their scores are " + scoresToString(this.scores) + "\nThis gives him an overall score of" + getOverallScore(this.scores);
        return fullDetails;
    }

    public String getShortDetails() {
        String shortDetails = "CN " + this.CompetitorNumber + "(" +this.name+ ") has overall score" + getOverallScore(this.scores);
        return shortDetails;
    }

    public static void main(String[] args) {
        //Competitor comp1 = new Competitor(1,"Mario", "15/10/2001","Pro","itialy","mario@superbros.com");
        //System.out.println(comp1.getFullDetails());

    }
}