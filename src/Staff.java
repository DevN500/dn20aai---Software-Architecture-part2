public class Staff {
    private String staffID;
    private Name staffName;
    private StaffTypes staffType;

    public Staff(String staffID, Name staffName, StaffTypes staffType) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffType = staffType;
    }

    public String getStaffID() {
        return this.staffID;
    }

    public Name getStaffName() {
        return this.staffName;
    }

    public StaffTypes getStaffType() {
        return this.staffType;
    }

    public String getStaffFullName() {
        return this.staffName.getFullName();
    }

    public String getStaffTypeString() {
        return this.staffType.toString();
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setStaffType(StaffTypes staffType) {
        this.staffType = staffType;
    }

    public void setStaffName(String firstName, String middleName, String lastName) {
        this.staffName.setFirstName(firstName);
        this.staffName.setMiddleName(middleName);
        this.staffName.setLastName(lastName);
    }

    @Override
    public String toString() {
        return "Staff ID: " + this.staffID + "\n" +
                "Staff Name: " + this.staffName.getFullName() + "\n" +
                "Staff Type: " + this.staffType.toString();
    }

}