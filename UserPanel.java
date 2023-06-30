import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private JTextField symbolNumberField;
    private JTextArea resultArea;

    public UserPanel() {
        setLayout(new BorderLayout());
        resultArea = new JTextArea(20, this.getWidth());
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Customize the appearance
        resultArea.setBackground(new Color(40, 40, 43));
        resultArea.setForeground(new Color(200, 200, 200));
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16));
        // resultArea.setEnabled(false);
        resultArea.getCaret().deinstall(resultArea);

        // resultArea.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        // resultArea.setCaretColor(new Color(200, 200, 200));
        // resultArea.setMargin(new Insets(5, 5, 5, 5));

        // Welcome part
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(30, 30, 30));
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel welcomeLabel = new JLabel("<html>Welcome!<br/>Enter symbol number for result.</html>");

        // Customize the appearance
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(200, 200, 200));

        welcomePanel.add(welcomeLabel);

        // Input part
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(30, 30, 30));
        inputPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));

        JLabel symbolNumberLabel = new JLabel("Symbol Number");
        symbolNumberField = new JTextField(15);
        JButton fetchButton = new JButton("🔍");

        // Customize the appearance
        symbolNumberField.setBackground(new Color(50, 50, 50));
        symbolNumberField.setForeground(new Color(200, 200, 200));
        symbolNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        symbolNumberField.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        symbolNumberField.setCaretColor(new Color(200, 200, 200));
        symbolNumberField.setMargin(new Insets(5, 5, 5, 5));

        // Label properties
        symbolNumberLabel.setFont(new Font("Arial", Font.BOLD, 25));
        symbolNumberLabel.setForeground(new Color(150, 150, 150));

        inputPanel.add(symbolNumberLabel);
        inputPanel.add(symbolNumberField);
        inputPanel.add(fetchButton);

        add(welcomePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Set preferred size for the label and button
        symbolNumberLabel.setPreferredSize(new Dimension(230, symbolNumberLabel.getPreferredSize().height));
        fetchButton.setPreferredSize(new Dimension(70, 80));
        symbolNumberField.setPreferredSize(new Dimension(220, 30));

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Customize the appearance of the button
        fetchButton.setBackground(new Color(7, 164, 121));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFont(new Font("Arial", Font.BOLD, 20));
        fetchButton.setFocusPainted(false);
        fetchButton.setBorderPainted(false);
        fetchButton.setMargin(new Insets(5, 5, 5, 5));

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.setText("");
                String symbolNumber = symbolNumberField.getText();
                fetchUserData(symbolNumber);
            }

            private void fetchUserData(String symbolNumber) {
                FetchData fetchData = new FetchData("localhost", 1234); // Modify server address and port accordingly
                String result = fetchData.fetchUserData(symbolNumber);
                resultArea.append(result);
            }
        });
    }

    // Custom paintComponent method for dark theme and glassy look
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a glassy look with a translucent overlay
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}
