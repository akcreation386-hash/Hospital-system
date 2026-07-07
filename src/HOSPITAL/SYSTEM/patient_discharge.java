package HOSPITAL.SYSTEM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {

    patient_discharge() {

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
        panel.setBounds(0, 0, 800, 400);
        add(panel);

        //================ TITLE =================

        JLabel title = new JLabel("Check-Out");
        title.setBounds(170, 25, 200, 35);
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setForeground(Color.BLACK);
        panel.add(title);

        //================ Patient ID =================

        JLabel lblID = new JLabel("Patient ID");
        lblID.setBounds(50, 80, 120, 25);
        lblID.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblID.setForeground(Color.BLACK);
        panel.add(lblID);

        Choice choice = new Choice();
        choice.setBounds(180, 80, 180, 25);
        panel.add(choice);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from patient_information");
            while (rs.next()) {
                choice.add(rs.getString("PatientID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //================ Name =================

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(50, 120, 120, 25);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblName.setForeground(Color.BLACK);
        panel.add(lblName);

        JLabel NAME = new JLabel("");
        NAME.setBounds(180, 120, 180, 25);
        NAME.setFont(new Font("Tahoma", Font.BOLD, 15));
        NAME.setForeground(Color.BLACK);
        panel.add(NAME);

        //================ Room Number =================

        JLabel lblRoom = new JLabel("Room Number");
        lblRoom.setBounds(50, 160, 120, 25);
        lblRoom.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblRoom.setForeground(Color.BLACK);
        panel.add(lblRoom);

        JLabel RN = new JLabel("");
        RN.setBounds(180, 160, 180, 25);
        RN.setFont(new Font("Tahoma", Font.BOLD, 15));
        RN.setForeground(Color.BLACK);
        panel.add(RN);

        //================ In Time =================

        JLabel lblIn = new JLabel("In Time");
        lblIn.setBounds(50, 200, 120, 25);
        lblIn.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIn.setForeground(Color.BLACK);
        panel.add(lblIn);

        JLabel INtime = new JLabel("");
        INtime.setBounds(180, 200, 220, 25);
        INtime.setFont(new Font("Tahoma", Font.BOLD, 12));
        INtime.setForeground(Color.BLACK);
        panel.add(INtime);

        //================ Out Time =================

        JLabel lblOut = new JLabel("Out Time");
        lblOut.setBounds(50, 240, 120, 25);
        lblOut.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblOut.setForeground(Color.BLACK);
        panel.add(lblOut);

        Date date = new Date();

        JLabel OUTtime = new JLabel("" + date);
        OUTtime.setBounds(180, 240, 220, 25);
        OUTtime.setFont(new Font("Tahoma", Font.BOLD, 12));
        OUTtime.setForeground(Color.BLACK);
        panel.add(OUTtime);

        //================ Buttons =================

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(50, 300, 120, 35);
        discharge.setBackground(new Color(1, 18, 46));
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);

        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                conn c = new conn();

                try {

                    c.statement.executeUpdate(
                            "delete from patient_information where PatientID='"
                                    + choice.getSelectedItem() + "'");

                    c.statement.executeUpdate(
                            "update rooms set Availability='Available' where Room_no='"
                                    + RN.getText() + "'");
                    JOptionPane.showMessageDialog(null,"Patient Discharged Successfully");

                    NAME.setText("");
                    RN.setText("");
                    INtime.setText("");
                    OUTtime.setText("");

                    choice.remove(choice.getSelectedIndex());

                    setVisible(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(185, 300, 100, 35);
        check.setBackground(new Color(1, 18, 46));
        check.setForeground(Color.WHITE);
        panel.add(check);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                conn c = new conn();

                try {

                    ResultSet rs = c.statement.executeQuery(
                            "select * from patient_information where PatientID='"
                                    + choice.getSelectedItem() + "'");
                    if (rs.next()) {

                        NAME.setText(rs.getString("name"));
                        RN.setText(rs.getString("Room"));
                        INtime.setText(rs.getString("Time"));
                        OUTtime.setText(new Date().toString());

                    } else {

                        JOptionPane.showMessageDialog(null,"Patient Record Not Found");

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(300, 300, 100, 35);
        back.setBackground(new Color(229, 62, 62));
        back.setForeground(Color.WHITE);

        back.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        panel.add(back);

        //================ IMAGE =================

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icon/dis2.png"));
        Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        JLabel image = new JLabel(new ImageIcon(img));
        image.setBounds(430, 40, 300, 300);
        panel.add(image);

        setUndecorated(true);
        setSize(800, 400);
        setLocation(300, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}