package HOSPITAL.SYSTEM;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class room extends JFrame implements ActionListener {

    JTable table;
    JButton b2;

    room(){

        // 🌈 MAIN PANEL
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

        panel.setBounds(0,0,900,500);
        panel.setLayout(null);
        add(panel);

        // 🏷️ HEADING
        JLabel heading = new JLabel("ROOM DETAILS");
        heading.setBounds(300, 10, 300, 30);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(Color.BLACK);
        panel.add(heading);

        // 🖼 IMAGE
        ImageIcon img2 = new ImageIcon(ClassLoader.getSystemResource("Icon/room.png"));
        Image i2 = img2.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(i2));
        label.setBounds(650,150,200,200);
        panel.add(label);

        // 📋 TABLE
        table = new JTable();
        table.setBounds(20,80,600,300);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setRowHeight(22);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,80,600,300);
        panel.add(scrollPane);

        // 🧾 FETCH DATA
        try{
            conn c = new conn();
            String q = "Select * from rooms";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            e.printStackTrace();
        }


        // 🔙 BACK BUTTON
        b2 = new JButton("BACK");
        b2.setBounds(350, 400, 120, 35);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setBackground(new Color(229,62,62));
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        panel.add(b2);

        // 🪟 FRAME SETTINGS
        setUndecorated(true);
                setSize(850,450);
                setLayout(null);
                setLocation(250,250);
             setVisible(true);
    }

    // 🔘 BUTTON ACTION
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b2){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new room();
    }
}