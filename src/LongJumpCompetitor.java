import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LongJumpCompetitor extends Competitor {
    public LongJumpCompetitor(String CompetitorNumber, Name name, Level LevelOfCompetitor, int Age, String Gender, String Country) {
        super(CompetitorNumber, name, LevelOfCompetitor, Age, Gender, Country);
    }
    public String getCompetitorNumber() {
        return super.getCompetitorNumber();
    }

    public Name getName() {
        return super.getName();
    }

    public Level getLevelOfCompetitor() {
        return super.getLevelOfCompetitor();
    }

    public int getAge() {
        return super.getAge();
    }

    public String getGender() {
        return super.getGender();
    }

    public String getCountry() {
        return super.getCountry();
    }

    public String getCompetitorFullName() {
        return super.getCompetitorFullName();
    }

    public String getLevelOfCompetitorString() {
        return super.getLevelOfCompetitorString();
    }

    public ArrayList<Integer> getScoreArray() {
        return super.getScoreArray();
    }

    public boolean hasScores() {
        return super.hasScores();
    }

    public void setLevelOfCompetitor(Level LevelOfCompetitor) {
        super.setLevelOfCompetitor(LevelOfCompetitor);
    }

    public void setName(String firstName, String middleName, String lastName) {
        super.setName(firstName, middleName, lastName);
    }

    public void setAge(int Age) {
        super.setAge(Age);
    }

    public void setCompetitorNumber(String CompetitorNumber) {
        super.setCompetitorNumber(CompetitorNumber);
    }

    public void setGender(String Gender) {
        super.setGender(Gender);
    }

    public void setCountry(String Country) {
        super.setCountry(Country);
    }

    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && this.getScoreArray().size() < 6) {
            this.getScoreArray().add(score);
        }
    }

    @Override
    public double getOverallScore() {
        ArrayList<Integer> scores = getScoreArray();
        int maxScore = Collections.max(this.getScoreArray());
        int minScore = Collections.min(this.getScoreArray());

        int sum = 0;
        for (int score : scores) {
            if (score != maxScore && score != minScore) {
                sum += score;
            }
        }

        return (double) sum / (scores.size() - 2);
    }

    @Override
    public String getShortDetails() {
        return "CN " + this.getCompetitorNumber() +
                ", " + this.getName().getInitials() +
                ", Score: " + this.getOverallScore();
    }

    @Override
    public String getFullDetails() {
        return "Competitor Number " + this.getCompetitorNumber() + "\n" + ", name " + this.getName().getFullName() + "\n" + ", country " + this.getCountry() + "\n" + ". Is a " + this.getLevelOfCompetitor().toString() + "\n" + " aged " + this.getAge() + "\n" + ", and has an overall score: " + this.getOverallScore() + "\n" + ". Category =  " + this.getCategory() + "\n" + ", their email is " + this.getEmail();
    }

}
