import java.util.ArrayList;
public abstract class Competitor {
    private String competitorID;
    private Name competitorName;
    private Level competitorLevel;
    private int competitorAge;
    private String competitorGender;
    private String competitorCountry;
    private ArrayList<Integer> competitorScore;

    public Competitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        this.competitorID = competitorID;
        this.competitorName = competitorName;
        this.competitorLevel = competitorLevel;
        this.competitorAge = competitorAge;
        this.competitorGender = competitorGender;
        this.competitorCountry = competitorCountry;
        this.competitorScore = new ArrayList<Integer>();
    }

    public String getCompetitorID() {
        return this.competitorID;
    }

    public Name getCompetitorName() {
        return this.competitorName;
    }

    public Level getCompetitorLevel() {
        return this.competitorLevel;
    }

    public int getCompetitorAge() {
        return this.competitorAge;
    }

    public String getCompetitorGender() {
        return this.competitorGender;
    }

    public String getCompetitorCountry() {
        return this.competitorCountry;
    }

    public String getCompetitorFullName() {
        return this.competitorName.getFullName();
    }

    public String getCompetitorLevelString() {
        return this.competitorLevel.toString();
    }

    public ArrayList<Integer> getScoreArray() {
        return this.competitorScore;
    }

    public abstract double getOverallScore();

    public abstract void addScore(int score);

    public void setCompetitorID(String competitorID) {
        this.competitorID = competitorID;
    }

    public void setCompetitorLevel(Level competitorLevel) {
        this.competitorLevel = competitorLevel;
    }

    public void setCompetitorName(String firstName, String middleName, String lastName) {
        this.competitorName.setFirstName(firstName);
        this.competitorName.setMiddleName(middleName);
        this.competitorName.setLastName(lastName);
    }

    public boolean hasScores() {
        return this.competitorScore.size() > 0;
    }

    public void setCompetitorAge(int competitorAge) {
        this.competitorAge = competitorAge;
    }

    public void setCompetitorGender(String competitorGender) {
        this.competitorGender = competitorGender;
    }

    public void setCompetitorCountry(String competitorCountry) {
        this.competitorCountry = competitorCountry;
    }

    public String getShortDetails() {
        return "CN " + this.competitorID +
                ", " + this.competitorName.getInitials() +
                ", Score: " + this.getOverallScore();
    }

    public String getFullDetails() {
        return "Competitor ID: " + this.competitorID + "\n" +
                "Competitor Name: " + this.competitorName.getFullName() + "\n" +
                "Competitor Country: " + this.competitorCountry + "\n" +
                "Competitor Level: " + this.competitorLevel.toString() + "\n" +
                "Competitor Age: " + this.competitorAge + "\n" +
                "Competitor Overall Score: " + this.getOverallScore();
    }

    public String getCompetitorExtraDetails(){
        return "Competitor ID: " + this.getCompetitorID() + "\n" +
                "Competitor Name: " + this.getCompetitorName().getFullName() + "\n" +
                "Competitor Country: " + this.getCompetitorCountry() + "\n" +
                "Competitor Level: " + this.getCompetitorLevel().toString() + "\n" +
                "Competitor Age: " + this.getCompetitorAge() + "\n" +
                "Competitor Gender: " + this.getCompetitorGender() + "\n" +
                "Competitor Overall Score: " + this.getOverallScore();
    }

}