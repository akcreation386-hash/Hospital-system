package HOSPITAL.SYSTEM;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class All_patient_info extends JFrame {

    All_patient_info(){

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

        panel.setLayout(null);   // 🔥 IMPORTANT (missing tha)
        add(panel);

        // 🔷 TITLE
        JLabel heading = new JLabel("ALL PATIENT INFORMATION");
        heading.setBounds(220, 15, 450, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setForeground(Color.BLACK);
        panel.add(heading);

        // 🔷 TABLE
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setRowHeight(22);   // ✅ better row height

        // ✅ HEADER STYLE
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));

        // 🔷 SCROLL (TABLE SIZE FIXED NICELY)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 70, 780, 280);   // ✅ width + height improved
        panel.add(scrollPane);

        try{
            conn c = new conn();
            String q ="select * from patient_information";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e){
            e.printStackTrace();
        }

        JButton b2 = new JButton("BACK");
        b2.setBounds(350, 355, 140, 40);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setBackground(new Color(229,62,62));
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);

        // ✅ EXIT ACTION
        // ✅ Action
        b2.addActionListener(e -> {
            setVisible(false);   // hide window
            dispose();           // free memory
        });

        panel.add(b2);

        // 🔧 FRAME SETTINGS
        setUndecorated(true);
        setSize(850,450);
        setLocation(250,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new All_patient_info();
    }
}