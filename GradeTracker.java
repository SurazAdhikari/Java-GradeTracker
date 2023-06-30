import javax.swing.*;

public class GradeTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserInterface userInterface = new UserInterface();
                userInterface.setVisible(true);
            }
        });
    }
}
// 21185184
