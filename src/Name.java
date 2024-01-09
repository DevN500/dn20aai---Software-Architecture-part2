public class Name {
    private String firstname;
    private String middlename;
    private String lastname;

    public Name(String firstname, String lastname, String middlename) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
    }

    public Name(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = "";
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getMiddleName() {
        return this.middlename;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getFullName() {
        return firstname + " " + (middlename != null && !middlename.isEmpty() ? middlename + " " : "") + lastname;
    }

    public String getInitials() {
        return (this.firstname.isEmpty() ? "" : this.firstname.substring(0, 1)) +
                (this.middlename.isEmpty() ? "" : this.middlename.substring(0, 1)) +
                (this.lastname.isEmpty() ? "" : this.lastname.substring(0, 1));
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    public void setMiddleName(String middlename) {
        this.middlename = middlename;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstname + "\n" +
                "Middle Name: " + this.middlename + "\n" +
                "Last Name: " + this.lastname;
    }

}