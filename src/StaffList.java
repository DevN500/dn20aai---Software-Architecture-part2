import java.util.ArrayList;

public class StaffList {
    private ArrayList<Staff> StaffList;

    public StaffList() {
        this.StaffList = new ArrayList<Staff>();
        preAddANDLoadStaff();
    }

    private void preAddANDLoadStaff() {
        Staff staffOfficial = new Staff("001", StaffParent.Official);
        Staff staffEmergency= new Staff("002", StaffParent.Emergency);
        StaffList.add(staffOfficial);
        StaffList.add(staffEmergency);
    }

    //public methods
    public void addStaff(Staff staff) {
        this.StaffList.add(staff);
    }

    public void removeStaff(Staff staff) {
        this.StaffList.remove(staff);
    }

    public Staff getStaffFromID(String ID) {
        for (Staff staff : this.StaffList) {
            if (staff.getStaffID().equals(ID)) {
                return staff;
            }
        }
        return null;
    }

    public ArrayList<Staff> getsStaffList(){
        return this.StaffList;
    }

}