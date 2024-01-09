public class Staff {
    private String staffID;
    private Name staffName;
    private StaffParent staffType;

    public Staff(String staffID, StaffParent staffType) {
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

    public StaffParent getStaffType() {
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

    public void setStaffType(StaffParent staffType) {
        this.staffType = staffType;
    }

    public void setStaffName(String firstName, String middleName, String lastName) {
        this.staffName.setFirstName(firstName);
        this.staffName.setMiddleName(middleName);
        this.staffName.setLastname(lastName);
    }

    @Override
    public String toString() {
        return "Staff ID: " + this.staffID + "\n" +
                "Staff Name: " + this.staffName.getFullName() + "\n" +
                "Staff Type: " + this.staffType.toString();
    }

}