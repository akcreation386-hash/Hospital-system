package HOSPITAL.SYSTEM;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Searchroom extends JFrame {

    JTable table;
    JComboBox<String> choice;

    Searchroom() {



        // 🌈 INNER GRADIENT PANEL
        JPanel panel = new JPanel() {
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
        add(panel);

        // Heading
        JLabel heading = new JLabel("Search For Room");
        heading.setBounds(280, 20, 350, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        heading.setForeground(Color.BLACK);
        panel.add(heading);

        // Status Label
        JLabel status = new JLabel("Status:");
        status.setBounds(150, 100, 100, 30);
        status.setFont(new Font("Tahoma", Font.BOLD, 18));
        status.setForeground(Color.BLACK);
        panel.add(status);

        // ComboBox
        choice = new JComboBox<>();
        choice.addItem("Available");
        choice.addItem("Occupied");
        choice.setBounds(250, 100, 180, 30);
        choice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(choice);

        // SEARCH BUTTON
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(450, 100, 120, 30);
        searchBtn.setBackground(new Color(1, 18, 46));
        searchBtn.setForeground(Color.WHITE);
        panel.add(searchBtn);

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String q = "select * from rooms where lower(trim(Availability)) = lower('"
                        + choice.getSelectedItem() + "')";

                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);

                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                    table.revalidate();
                    table.repaint();

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        // BACK BUTTON
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(600, 100, 120, 30);
        backBtn.setBackground(new Color(229, 62, 62));
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);

        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        // TABLE
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setBackground(new Color(90, 156, 162));
        table.setForeground(Color.WHITE);

        try {
            conn c = new conn();
            String q = "select * from rooms";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception E) {
            E.printStackTrace();
        }

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 16));
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 180, 750, 200);
        panel.add(scrollPane);

        // FRAME SETTINGS
        setUndecorated(true);
        setSize(850, 450);
        setLocation(250, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Searchroom();
    }
}