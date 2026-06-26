package HOSPITAL.SYSTEM;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {

    patient_discharge(){

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
        panel.setBounds(0,0,800,400);
        add(panel);

        // TITLE
        JLabel label = new JLabel("Check-Out");
        label.setBounds(180, 40, 200, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        panel.add(label);

        // Customer ID
        JLabel label2 = new JLabel("Customer ID");
        label2.setBounds(60, 100, 120, 30);
        label2.setFont(new Font("Tahoma", Font.BOLD, 15));
        label2.setForeground(Color.BLACK);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(180,100,180,25);
        panel.add(choice);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_information");
            while (resultSet.next()){
                choice.add(resultSet.getString("Number"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // Room Number
        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(60, 150, 120, 30);
        label3.setFont(new Font("Tahoma", Font.BOLD, 15));
        label3.setForeground(Color.BLACK);
        panel.add(label3);

        JLabel RN = new JLabel("");
        RN.setBounds(180, 150, 180, 30);
        RN.setForeground(Color.BLACK);
        panel.add(RN);

        // In Time
        JLabel label4 = new JLabel("In Time");
        label4.setBounds(60, 200, 120, 30);
        label4.setFont(new Font("Tahoma", Font.BOLD, 15));
        label4.setForeground(Color.BLACK);
        panel.add(label4);

        JLabel INtime = new JLabel("");
        INtime.setBounds(180, 200, 200, 30);
        INtime.setForeground(Color.BLACK);
        panel.add(INtime);

        // Out Time
        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(60, 250, 120, 30);
        label5.setFont(new Font("Tahoma", Font.BOLD, 15));
        label5.setForeground(Color.BLACK);
        panel.add(label5);

        Date date = new Date();

        JLabel OUTtime = new JLabel(""+date);
        OUTtime.setBounds(180, 250, 200, 30);
        OUTtime.setForeground(Color.BLACK);
        panel.add(OUTtime);

        // BUTTONS
        JButton discharge = new JButton("Discharge");
        discharge.setBounds(60,310,120,35);
        discharge.setBackground(new Color(1,18,46));
        discharge.setForeground(Color.white);
        panel.add(discharge);

        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try{
                    c.statement.executeUpdate("delete from patient_information where Number = '"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("update rooms set Availability = 'Available' where Room_no = '"+RN.getText()+"'");

                    JOptionPane.showMessageDialog(null,"Done");
                    setVisible(false);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(190,310,100,35);
        check.setBackground(new Color(1,18,46));
        check.setForeground(Color.white);
        panel.add(check);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try{
                    ResultSet resultSet = c.statement.executeQuery(
                            "select * from patient_information where Number = '"+choice.getSelectedItem()+"'"
                    );
                    while(resultSet.next()) {
                        RN.setText(resultSet.getString("Room"));
                        INtime.setText(resultSet.getString("Time"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(310,310,100,35);
        back.setBackground(new Color(229,62,62));
        back.setForeground(Color.white);

        back.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        panel.add(back);

        // IMAGE
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icon/dis2.png"));
        Image img = icon.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBounds(400,-100,427,585);
        panel.add(imgLabel);

        setUndecorated(true);
        setSize(800,400);
        setLocation(250,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}