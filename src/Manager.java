import javax.swing.SwingUtilities;

public class Manager {
    private static CompetitorList competitors;

    public Manager() {
        competitors = new CompetitorList();
    }


    // Main method to run the application
    public static void main(String[] args) {
        //temporary placeholder
        Manager manager = new Manager();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI frame = new GUI(competitors);
                frame.setVisible(true);
            }
        });
    }
}
