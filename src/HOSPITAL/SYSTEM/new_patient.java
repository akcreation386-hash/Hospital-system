package HOSPITAL.SYSTEM;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class new_patient extends JFrame implements ActionListener {

    JComboBox comboBox;
    JTextField textFieldnumber , textName , textfieldisease, textFieldDeposite ;
    JRadioButton r1,r2;
    Choice c1;
    JLabel date;

    JButton b1,b2;

    new_patient(){

        // ================= FRAME LAYER =================
        setUndecorated(true);
        setSize(850,450);
        setLocation(250,250);
        setLayout(null);

        // ================= BACKGROUND IMAGE (BACK LAYER) =================
        ImageIcon bg = new ImageIcon(ClassLoader.getSystemResource("Icon//bg.jpg"));
        Image bgImg = bg.getImage().getScaledInstance(850,450,Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImg));
        bgLabel.setBounds(0,0,850,450);
        add(bgLabel);

        // ================= PANEL (FRONT LAYER) =================
        JPanel panel = new JPanel();
        panel.setBounds(0,0,850,450);
        panel.setLayout(null);
        panel.setOpaque(false);
        bgLabel.add(panel); // ⭐ IMPORTANT: panel inside background

        // ================= DOCTOR IMAGE (newp.png) =================
        ImageIcon img2 = new ImageIcon(ClassLoader.getSystemResource("Icon//newp.png"));
        Image i2 = img2.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
        JLabel label1 = new JLabel(new ImageIcon(i2));
        label1.setBounds(500,80,250,250);
        panel.add(label1);

        // ================= TITLE =================
        JLabel textlbl = new JLabel("New Patient Form");
        textlbl.setBounds(300,10,300,40);
        textlbl.setFont(new Font("Tahoma",Font.BOLD,22));
        textlbl.setForeground(Color.BLACK);
        panel.add(textlbl);

        Font f = new Font("Tahoma",Font.BOLD,15);

        // ================= ID =================
        JLabel textlbl2 = new JLabel("ID:");
        textlbl2.setBounds(80,70,150,25);
        textlbl2.setFont(f);
        textlbl2.setForeground(Color.BLACK);
        panel.add(textlbl2);

        comboBox = new JComboBox(new String[] {"Aadhar Card","Voter ID","Driving License"});
        comboBox.setBounds(220,70,180,25);
        panel.add(comboBox);

        // ================= NUMBER =================
        JLabel textlbl3 = new JLabel("Number:");
        textlbl3.setBounds(80,110,150,25);
        textlbl3.setFont(f);
        textlbl3.setForeground(Color.BLACK);
        panel.add(textlbl3);

        textFieldnumber = new JTextField();
        textFieldnumber.setBounds(220,110,180,25);
        panel.add(textFieldnumber);

        // ================= NAME =================
        JLabel textlbl4 = new JLabel("Name:");
        textlbl4.setBounds(80,150,150,25);
        textlbl4.setFont(f);
        textlbl4.setForeground(Color.BLACK);
        panel.add(textlbl4);

        textName = new JTextField();
        textName.setBounds(220,150,180,25);
        panel.add(textName);

        // ================= GENDER =================
        JLabel gender = new JLabel("Gender:");
        gender.setBounds(80,190,150,25);
        gender.setFont(f);
        gender.setForeground(Color.BLACK);
        panel.add(gender);

        r1 = new JRadioButton("Male");
        r1.setBounds(220,190,80,25);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(310,190,100,25);
        panel.add(r2);

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(r1);
        bg1.add(r2);

        // ================= DISEASE =================
        JLabel disease= new JLabel("Disease:");
        disease.setBounds(80,230,150,25);
        disease.setFont(f);
        disease.setForeground(Color.BLACK);
        panel.add(disease);

        textfieldisease = new JTextField();
        textfieldisease.setBounds(220,230,180,25);
        panel.add(textfieldisease);

        // ================= ROOM =================
        JLabel room = new JLabel("Room:");
        room.setBounds(80,270,100,20);
        room.setFont(f);
        room.setForeground(Color.BLACK);
        panel.add(room);

        c1 = new Choice();
        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from rooms");
            while(resultSet.next()){
                c1.add(resultSet.getString("room_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        c1.setBounds(220,270,180,25);
        panel.add(c1);

        // ================= TIME =================
        JLabel jdate = new JLabel("Time:");
        jdate.setBounds(80,310,150,25);
        jdate.setFont(f);
        jdate.setForeground(Color.BLACK);
        panel.add(jdate);

        date = new JLabel(new Date().toString());
        date.setBounds(220,310,300,25);
        panel.add(date);

        // ================= DEPOSIT =================
        JLabel jdeposite = new JLabel("Deposit:");
        jdeposite.setBounds(80,350,150,25);
        jdeposite.setFont(f);
        jdeposite.setForeground(Color.BLACK);
        panel.add(jdeposite);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(220,350,180,25);
        panel.add(textFieldDeposite);

        // ================= BUTTONS =================
        b1 = new JButton("ADD");
        b1.setBounds(120,400,120,35);
        b1.setBackground(new Color(1,18,46));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(260,400,120,35);
        b2.setBackground(new Color(229,62,62));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        panel.add(b2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new new_patient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== b1){

            conn c=new conn();

            String radioBTN = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : "";

            try{
                String q = "insert into Patient_information values ('"
                        + comboBox.getSelectedItem()+"','"
                        + textName.getText()+"','"
                        + textFieldnumber.getText()+"','"
                        + radioBTN+"','"
                        + textfieldisease.getText()+"','"
                        + c1.getSelectedItem()+"','"
                        + date.getText()+"','"
                        + textFieldDeposite.getText()+"')";

                String q1 = "update rooms set Availability = 'Occupied' where room_no = '"+c1.getSelectedItem()+"'";

                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(null,"Added Successfully");
                setVisible(false);

            }catch(Exception E){
                E.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }
}