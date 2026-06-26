package HOSPITAL.SYSTEM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class reception extends JFrame {

    JPanel content;

    reception() {

        setTitle("Hospital Reception");
        setSize(1550, 850);
        setLocationRelativeTo(null);
        setLayout(null);

        // 🌈 Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1550, 850);
        mainPanel.setBackground(new Color(18, 24, 38));
        add(mainPanel);

        // 🏥 HEADER
        JPanel header = new JPanel();
        header.setBounds(0, 0, 1550, 50);
        header.setBackground(new Color(18, 24, 38));
        header.setLayout(null);
        mainPanel.add(header);

        JLabel title = new JLabel("Hospital Reception Dashboard");
        title.setBounds(30, 10, 500, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(title);

        // 📌 TOP BAR
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBounds(0, 50, 1550, 150);
        topBar.setBackground(new Color(26, 32, 44));
        mainPanel.add(topBar);

        // 🎨 Colors
        Color primary = new Color(0, 184, 148);
        Color danger = new Color(214, 48, 49);

        // 🔘 ROW 1
        JButton b1 = new JButton("Add Patient");
        b1.setBounds(20, 20, 140, 40);
        styleButton(b1, primary);
        topBar.add(b1);
        b1.addActionListener(e -> new new_patient());

        JButton b2 = new JButton("Room");
        b2.setBounds(180, 20, 140, 40);
        styleButton(b2, primary);
        topBar.add(b2);
        b2.addActionListener(e -> new room());

        JButton b3 = new JButton("Department");
        b3.setBounds(340, 20, 140, 40);
        styleButton(b3, primary);
        topBar.add(b3);
        b3.addActionListener(e -> new department());


        JButton b4 = new JButton("Employees");
        b4.setBounds(500, 20, 140, 40);
        styleButton(b4, primary);
        topBar.add(b4);
        b4.addActionListener(e -> new Employee_info());

        JButton b5 = new JButton("Patient Info");
        b5.setBounds(660, 20, 140, 40);
        styleButton(b5, primary);
        topBar.add(b5);
        b5.addActionListener(e -> new All_patient_info());

        // 🔘 ROW 2
        JButton b6 = new JButton("Discharge");
        b6.setBounds(20, 80, 140, 40);
        styleButton(b6, primary);
        topBar.add(b6);
        b6.addActionListener(e -> new patient_discharge());

        JButton b7 = new JButton("Update");
        b7.setBounds(180, 80, 140, 40);
        styleButton(b7, primary);
        topBar.add(b7);
        b7.addActionListener(e -> new update());

        JButton b8 = new JButton("Ambulance");
        b8.setBounds(340, 80, 140, 40);
        styleButton(b8, primary);
        topBar.add(b8);
        b8.addActionListener(e -> new ambulance());

        JButton b9 = new JButton("Search Room");
        b9.setBounds(500, 80, 140, 40);
        styleButton(b9, primary);
        topBar.add(b9);
        b9.addActionListener(e -> new Searchroom());

        // 🔴 LOGOUT
        JButton logout = new JButton("Logout");
        logout.setBounds(660, 80, 140, 40);
        styleButton(logout, danger);
        topBar.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new login();
            }
        });

        // 🖼 LOGOS
        ImageIcon logoIcon2 = new ImageIcon(ClassLoader.getSystemResource("Icon/mdr2.png"));
        Image img2 = logoIcon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel logoLabel2 = new JLabel(new ImageIcon(img2));
        logoLabel2.setBounds(850, -10, 200, 200);
        topBar.add(logoLabel2);

        ImageIcon logoIcon3 = new ImageIcon(ClassLoader.getSystemResource("Icon/fdr.png"));
        Image img3 = logoIcon3.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel logoLabel3 = new JLabel(new ImageIcon(img3));
        logoLabel3.setBounds(1150, -25, 200, 200);
        topBar.add(logoLabel3);

        ImageIcon logoIcon4 = new ImageIcon(ClassLoader.getSystemResource("Icon/1.png"));
        Image img4 = logoIcon4.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel logoLabel4 = new JLabel(new ImageIcon(img4));
        logoLabel4.setBounds(1000, -18, 180, 180);
        topBar.add(logoLabel4);


        // 📌 CONTENT AREA
        content = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                Color c1 = new Color(255, 255, 255);
                Color c2 = new Color(26, 230, 199);

                GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        content.setLayout(null);
        content.setBounds(0, 200, 1550, 650);
        mainPanel.add(content);

        JLabel text = new JLabel("Workspace Area");
        text.setBounds(650, 300, 300, 40);
        text.setFont(new Font("Segoe UI", Font.BOLD, 24));
        text.setForeground(Color.BLACK);
        content.add(text);

setUndecorated(true);
        setVisible(true);
    }

    // 🎨 BUTTON STYLE
    private void styleButton(JButton button, Color color) {
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }

    public static void main(String[] args) {
        new reception();
    }
}