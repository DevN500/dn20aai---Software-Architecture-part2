public class Staff {
    private int staff_ID;
    private String staffName;
    private TypesOfStaff TypeOfStaff;

    public Staff(int staff_ID, String staffName, TypesOfStaff TypeOfStaff) {
        this.staff_ID = staff_ID;
        this.staffName = staffName;
        this.TypeOfStaff = TypeOfStaff;
    }

    public int getStaff_ID() {
        return this.staff_ID;
    }

    public String getStaffName() {
        return this.staffName;
    }

    public TypesOfStaff getType() {
        return this.TypeOfStaff;
    }

    public String getStaffFullName() {
        return this.staffName.getFullName();
    }

    public String getStaffTypeString() {
        return this.TypeOfStaff.toString();
    }

    public void setStaffID(int staff_ID) {
        this.staff_ID = staff_ID;
    }

    public void setStaffType(TypesOfStaff TypeOfStaff) {
        this.TypeOfStaff = TypeOfStaff;
    }

    public void setStaffName(String firstName, String middleName, String lastName) {
        this.staffName.setFirstName(firstName);
        this.staffName.setMiddleName(middleName);
        this.staffName.setLastName(lastName);
    }

    @Override
    public String toString() {
        return "Staff ID: " + this.staff_ID + "\n" +
                "Staff Name: " + this.staffName.getFullName() + "\n" +
                "Staff Type: " + this.TypeOfStaff.toString();
    }

}
