public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = "";
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return firstName + " " + (middleName != null && !middleName.isEmpty() ? middleName + " " : "") + lastName;
    }

    public String getInitials() {
        return (this.firstName.isEmpty() ? "" : this.firstName.substring(0, 1)) +
                (this.middleName.isEmpty() ? "" : this.middleName.substring(0, 1)) +
                (this.lastName.isEmpty() ? "" : this.lastName.substring(0, 1));
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName + "\n" +
                "Middle Name: " + this.middleName + "\n" +
                "Last Name: " + this.lastName;
    }

}