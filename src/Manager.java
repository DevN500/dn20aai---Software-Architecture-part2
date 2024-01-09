import javax.swing.SwingUtilities;

public class Manager {
    private static CompetitorList competitorList;
    private static StaffList staffList;

    public Manager() {
        competitorList = new CompetitorList();
        staffList = new StaffList();
    }


    public static void main(String[] args) {
        Manager manager = new Manager();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI frame = new GUI(staffList, competitorList);
                frame.setVisible(true);
            }
        });
    }
}