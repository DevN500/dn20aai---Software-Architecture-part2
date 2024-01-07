import java.util.ArrayList;
import java.util.Collections;
public class LongJump extends Competitor {
    public LongJump(int newCompetitorNumber, String newName, int newAge, String newDateOfBirth, LevelOfCompetitor newLevel, String newCountry, String newEmail, String newCategory, int newCompetitionNumber) {
        super(newCompetitorNumber, newName, newAge, newDateOfBirth, newLevel, newCountry, newEmail, newCategory, newCompetitionNumber);
    }

    public int getCompetitorNumber() {
        return super.getCompetitorNumber();
    }

    public String getName() {
        return super.getName();
    }

    public int getAge() {
        return super.getAge();
    }

    public String getDateOfBirth() {
        return super.getDateOfBirth();
    }

    public LevelOfCompetitor getLevel() {
        return super.getLevel();
    }

    public String getCountry() {
        return super.getCountry();
    }

    public String getEmail() {
        return super.getEmail();
    }

    public String getCategory() {
        return super.getCategory();
    }

    public int getCompetitionNumber() {
        return super.getCompetitionNumber();
    }


    public ArrayList<Integer> getScoreArray() {
        return super.getScoreArray();
    }

    public boolean ScoreEligibility() {
        return super.ScoreEligibility();
    }


    public String scoresToString(ArrayList<Integer> Scores)
    {
        String list = String.valueOf(Scores.get(0));
        for (int i = 1; i < Scores.size(); i++) {
            list = list + ", "+ Scores.get(i);
        }
        return super.scoresToString(Scores);
    }
    public void setCompetitorNumber(int CompetitorNumber) {
        super.setCompetitorNumber(CompetitorNumber);
    }

    public void setName(String name) {
        super.setName(name);
    }

    public void setAge(int Age) {
        super.setAge(Age);
    }

    public void setDateOfBirth(String DoB) {
        super.setDateOfBirth(DoB);
    }

    public void setLevel(LevelOfCompetitor level) {
        super.setLevel(level);
    }

    public void setCountry(String country) {
        super.setCountry(country);
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public void setCategory(String category) {
        super.setCategory(category);
    }

    public void setCompetitionNumber(int CompetitionNumber) {
        super.setCompetitionNumber(CompetitionNumber);
    }

    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && this.getScoreArray().size() == 5) {
            this.getScoreArray().add(score);
        }
    }

    @Override
    public double getOverallScore() {
        ArrayList<Integer> scores = getScoreArray();
        int maxScore = Collections.max(scores);
        int minScore = Collections.min(scores);

        double sum = 0;
        for (int score : scores) {
            if (score != maxScore && score != minScore) {
                sum += score;
            }
        }
        return sum / (scores.size()-2);
    }

    @Override
    public String getShortDetails() {
        return "CN " + this.getCompetitorNumber() +
                ", " + this.getName() +
                ", has overall score " + this.getOverallScore();
    }

    @Override
    public String getFullDetails() {
        return "Competitor number " + this.getCompetitorNumber() + "," + this.getName() + ", " + this.getCountry() + ". Born " + this.getDateOfBirth() + " is a " + this.getLevel() + "\ntheir email is: " + this.getEmail() + ". Their Category is " + this.getCategory() + ". Their Age is " + this.getAge() + ". Their Competition Number is " + this.getCompetitorNumber() + "\nThis gives him an overall score of" + this.getOverallScore();
    }
}

