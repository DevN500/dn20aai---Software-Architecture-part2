import java.util.ArrayList;
import java.util.Collections;
public class LongJump extends Competitor {
    public LongJump(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    public String getCompetitorID() {
        return super.getCompetitorID();
    }

    public Name getCompetitorName() {
        return super.getCompetitorName();
    }

    public Level getCompetitorLevel() {
        return super.getCompetitorLevel();
    }

    public int getCompetitorAge() {
        return super.getCompetitorAge();
    }

    public String getCompetitorGender() {
        return super.getCompetitorGender();
    }

    public String getCompetitorCountry() {
        return super.getCompetitorCountry();
    }

    public String getCompetitorFullName() {
        return super.getCompetitorFullName();
    }

    public String getCompetitorLevelString() {
        return super.getCompetitorLevelString();
    }

    public ArrayList<Integer> getScoreArray() {
        return super.getScoreArray();
    }

    public boolean hasScores() {
        return super.hasScores();
    }

    public void setCompetitorLevel(Level competitorLevel) {
        super.setCompetitorLevel(competitorLevel);
    }

    public void setCompetitorName(String firstName, String middleName, String lastName) {
        super.setCompetitorName(firstName, middleName, lastName);
    }

    public void setCompetitorAge(int competitorAge) {
        super.setCompetitorAge(competitorAge);
    }

    public void setCompetitorGender(String competitorGender) {
        super.setCompetitorGender(competitorGender);
    }

    public void setCompetitorCountry(String competitorCountry) {
        super.setCompetitorCountry(competitorCountry);
    }

    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && this.getScoreArray().size() < 6) {
            this.getScoreArray().add(score);
        }
    }

    @Override
    public double getOverallScore() {
        int maxScore = Collections.max(this.getScoreArray());
        int minScore = Collections.min(this.getScoreArray());

        double sum = 0;
        int count = 0;
        for (int score : this.getScoreArray()) {
            if (score != maxScore && score != minScore) {
                sum += score;
                count++;
            } else {
                // Ensure only one instance of max and min are removed
                if (score == maxScore) maxScore = Integer.MIN_VALUE;
                if (score == minScore) minScore = Integer.MIN_VALUE;
            }
        }
        return count > 0 ? sum / count : Double.NaN;
    }

    @Override
    public String getShortDetails() {
        return "CN " + this.getCompetitorID() +
                ", " + this.getCompetitorName().getInitials() +
                ", Score: " + this.getOverallScore();
    }

    @Override
    public String getFullDetails() {
        return "Competitor ID: " + this.getCompetitorID() + "\n" +
                "Competitor Name: " + this.getCompetitorName().getFullName() + "\n" +
                "Competitor Country: " + this.getCompetitorCountry() + "\n" +
                "Competitor Level: " + this.getCompetitorLevel().toString() + "\n" +
                "Competitor Age: " + this.getCompetitorAge() + "\n" +
                "Competitor Overall Score: " + this.getOverallScore();
    }

    @Override
    public String getCompetitorExtraDetails(){
        return "Competitor ID: " + this.getCompetitorID() + "\n" +
                "Competitor Name: " + this.getCompetitorName().getFullName() + "\n" +
                "Competitor Country: " + this.getCompetitorCountry() + "\n" +
                "Competitor Level: " + this.getCompetitorLevel().toString() + "\n" +
                "Competitor Age: " + this.getCompetitorAge() + "\n" +
                "Competitor Gender: " + this.getCompetitorGender();
    }
}