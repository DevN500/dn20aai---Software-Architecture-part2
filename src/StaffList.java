import java.util.ArrayList;

public class StaffList {
    private ArrayList<Staff> staffList;

    public StaffList() {
        this.staffList = new ArrayList<Staff>();
        preAddANDLoadStaff();
    }

    private void preAddANDLoadStaff() {
        Staff staffOfficial = new Staff("101", new Name("John", "Doe", "Smith"), StaffTypes.Official);
        Staff staffReferee= new Staff("102", new Name("Jane", "Doe", "Smith"), StaffTypes.Referee);
        Staff staffDataEntry= new Staff("103", new Name("John", "Doe", "Smith"), StaffTypes.DataEntry);
        Staff staffEmergency= new Staff("104", new Name("Jane", "Doe", "Smith"), StaffTypes.Emergency);
        Staff staffAudience= new Staff("105", new Name("John", "Doe", "Smith"), StaffTypes.Audience);
        staffList.add(staffOfficial);
        staffList.add(staffReferee);
        staffList.add(staffDataEntry);
        staffList.add(staffEmergency);
        staffList.add(staffAudience);
    }

    //public methods
    public void addStaff(Staff staff) {
        this.staffList.add(staff);
    }

    public void removeStaff(Staff staff) {
        this.staffList.remove(staff);
    }

    public Staff getStaffFromID(String ID) {
        for (Staff staff : this.staffList) {
            if (staff.getStaffID().equals(ID)) {
                return staff;
            }
        }
        return null;
    }

    public ArrayList<Staff> getsStaffList(){
        return this.staffList;
    }

}