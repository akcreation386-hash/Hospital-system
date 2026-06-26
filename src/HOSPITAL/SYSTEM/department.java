package HOSPITAL.SYSTEM;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class department extends JFrame {

    department(){

        // 🔷 Gradient Panel
        JPanel panel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                Color c1 = new Color(14, 241, 208);
                Color c2 = new Color(255, 255, 255);

                GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(null);

        // 🔷 TITLE
        JLabel heading = new JLabel("Department & Phone Number");
        heading.setBounds(250, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setForeground(Color.BLACK);
        panel.add(heading);

        // 🔷 TABLE
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));

        // HEADER STYLE
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 750, 250);
        panel.add(scrollPane);

        // 🔷 DATABASE FETCH
        try {
            conn c = new conn();
            String q = "select * from departments";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔙 BACK BUTTON
        JButton b2 = new JButton("BACK");
        b2.setBounds(350, 340, 140, 40);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setBackground(new Color(229,62,62));
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);

        // ✅ Action
        b2.addActionListener(e -> {
            setVisible(false);   // hide window
            dispose();           // free memory
        });

        panel.add(b2);

        // 🔷 FRAME SETTINGS
        panel.setBounds(0, 0, 900, 500);
        add(panel);

        setUndecorated(true);

        setSize(850,450);
        setLocation(250,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new department();
    }
}