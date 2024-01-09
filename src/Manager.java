import javax.swing.SwingUtilities;

public class Manager {
    private static CompetitorList competitorList;
    private static StaffList staffList;

    //"IceSkating" or "JavelinThrow" for Filetype
    public Manager() {
        competitorList = new CompetitorList();
        staffList = new StaffList();
    }


    // Main method to run the application
    public static void main(String[] args) {
        //temporary placeholder
        Manager manager = new Manager();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI frame = new GUI(staffList, competitorList);
                frame.setVisible(true);
            }
        });
    }
}