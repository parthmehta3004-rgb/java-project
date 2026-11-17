
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    Dashboard() {

        setTitle("Lost and Found Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        // Heading
        JLabel heading = new JLabel("Lost & Found Management System");
        heading.setBounds(200, 30, 550, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        panel.add(heading);

        // Lost Item Button
        JButton lostButton = new JButton("Report Lost Item");
        lostButton.setBounds(300, 150, 250, 50);
        panel.add(lostButton);

        // Found Item Button
        JButton foundButton = new JButton("Report Found Item");
        foundButton.setBounds(300, 230, 250, 50);
        panel.add(foundButton);

        // Claim Item Button
        JButton claimButton = new JButton("Claim Item");
        claimButton.setBounds(300, 310, 250, 50);
        panel.add(claimButton);

        // Add panel to window
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
