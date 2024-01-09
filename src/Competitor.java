import java.util.ArrayList;
public abstract class Competitor {
    private String CompetitorNumber;
    private Name name;

    private String Category;

    private String Email;

    private String DateOfBirth;
    private Level LevelOfCompetitor;
    private int Age;
    private String Gender;
    private String Country;

    private int CompetitionNumber;
    private ArrayList<Integer> competitors;

    public Competitor(String CompetitorNumber, Name name, Level LevelOfCompetitor, int Age, String Gender, String Country) {
        this.CompetitorNumber = CompetitorNumber;
        this.name = name;
        this.Category = Category;
        this.Email = Email;
        this.DateOfBirth = DateOfBirth;
        this.LevelOfCompetitor = LevelOfCompetitor;
        this.Age = Age;
        this.Gender = Gender;
        this.Country = Country;
        this.CompetitionNumber = CompetitionNumber;

        this.competitors = new ArrayList<Integer>();
    }

    public String getCompetitorNumber() {
        return this.CompetitorNumber;
    }

    public Name getName() {
        return this.name;
    }

    public String getCategory() {
        return this.Category;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getDateOfBirth() {
        return this.DateOfBirth;
    }

    public Level getLevelOfCompetitor() {
        return this.LevelOfCompetitor;
    }

    public int getAge() {
        return this.Age;
    }

    public String getGender() {
        return this.Gender;
    }

    public String getCountry() {
        return this.Country;
    }

    public String getCompetitorFullName() {
        return this.name.getFullName();
    }

    public String getLevelOfCompetitorString() {
        return this.LevelOfCompetitor.toString();
    }

    public ArrayList<Integer> getScoreArray() {
        return this.competitors;
    }

    public abstract double getOverallScore();

    public abstract void addScore(int score);

    public void setCompetitorNumber(String CompetitorNumber) {
        this.CompetitorNumber = CompetitorNumber;
    }

    public void setLevelOfCompetitor(Level LevelOfCompetitor) {
        this.LevelOfCompetitor = LevelOfCompetitor;
    }

    public void setName(String firstName, String middleName, String lastName) {
        this.name.setFirstName(firstName);
        this.name.setMiddleName(middleName);
        this.name.setLastname(lastName);
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public boolean hasScores() {
        return this.competitors.size() > 0;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getShortDetails() {
        return "CN " + this.CompetitorNumber +
                ", " + this.name.getInitials() +
                ", has overall score  " + this.getOverallScore();
    }

    public String getFullDetails() {
        return "Competitor Number " + this.CompetitorNumber + "\n" + ", name " + this.name.getFullName() + "\n" + ", country " + this.Country + "\n" + ". Is a " + this.LevelOfCompetitor.toString() + "\n" + " aged " + this.Age + "\n" + ", and has an overall score: " + this.getOverallScore() + "\n" + ". Category =  " + this.Category + "\n" + ", their email is " + this.Email;
    }

}

