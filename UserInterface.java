
// UserInterface.java
import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {

    UserInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Grade Tracker");
        this.setSize(780, 800);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.add(new UserPanel(), BorderLayout.CENTER);
    }
}
