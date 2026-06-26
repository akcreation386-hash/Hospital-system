package HOSPITAL.SYSTEM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, cancelBtn;

    login() {

        setTitle("Hospital Management System - Login");
        setSize(750, 300);
        setLocation(400, 270);
        setLayout(null);

        // 🔷 SINGLE FULL GRADIENT PANEL
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                Color c1 = new Color(26, 230, 199);   // light blue
                Color c2 = new Color(255, 255, 255);    // dark blue

                GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBounds(0, 0, 750, 300);
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        mainPanel.setLayout(null);
        add(mainPanel);
        // 📦 FORM BOX (CARD STYLE)
        JPanel formBox = new JPanel();
        formBox.setBounds(60, 40, 380, 220);
        formBox.setBackground(new Color(255, 255, 255, 202)); // slightly lighter box
        formBox.setLayout(null);
        formBox.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        mainPanel.add(formBox);

        // 🏥 Title
        JLabel title = new JLabel("LOGIN");
        title.setBounds(150, 10, 100, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.black);
        formBox.add(title);

        // 👤 Username
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(30, 60, 100, 25);
        userLabel.setForeground(Color.black);
        formBox.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 60, 200, 30);
        usernameField.setBackground(new Color(173, 190, 225));
        usernameField.setForeground(Color.black);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // no visible border
        formBox.add(usernameField);

        // 🔒 Password
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(30, 110, 100, 25);
        passLabel.setForeground(Color.black);
        formBox.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 110, 200, 30);
        passwordField.setBackground(new Color(173, 190, 225));
        passwordField.setForeground(Color.black);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        formBox.add(passwordField);

        // 🔘 Login Button
        loginBtn = new JButton("Login");
        loginBtn.setBounds(60, 160, 100, 35);
        styleButton(loginBtn, new Color(0, 37, 159));
        formBox.add(loginBtn);

        // ❌ Cancel Button
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(200, 160, 100, 35);
        styleButton(cancelBtn, new Color(239, 35, 35));
        formBox.add(cancelBtn);



        // 🖼 LOGO
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/1.png"));
        Image img = logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        logoLabel.setBounds(500, 50, 200, 200);
        mainPanel.add(logoLabel);


        loginBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        setUndecorated(true);
        setVisible(true);
    }

    // 🎨 Button Styling
    private void styleButton(JButton button, Color color) {
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
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
        new login();
    }

    // 🔐 LOGIN LOGIC
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            try {
                conn c = new conn();

                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                String query = "SELECT * FROM Login WHERE username=? AND Password=?";
                PreparedStatement ps = c.connection.prepareStatement(query);

                ps.setString(1, user);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful ✅");
                    new reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password ❌");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            System.exit(0);
        }
    }
}
