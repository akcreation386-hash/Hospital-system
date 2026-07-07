package HOSPITAL.SYSTEM;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;

public class recipt extends JFrame {

    JPanel panel;

    JLabel heading;
    JLabel lblPatientID, lblName, lblMobile, lblIdNumber;
    JLabel lblGender, lblDisease, lblRoom, lblCheckIn;
    JLabel lblDeposit, lblDate;

    JLabel patientID, name, mobile, idNumber;
    JLabel gender, disease, room, checkIn;
    JLabel deposit, date;

    recipt(int patientId) {

        setTitle("Patient Receipt");
        setSize(700, 650);
        setLocationRelativeTo(null);
     //  setUndecorated(true);


        ImageIcon watermark = new ImageIcon(
                ClassLoader.getSystemResource("Icon/1.png"));

        Image logo = watermark.getImage();

        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                // Watermark transparency
                g2.setComposite(
                        AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER,0.10f));

                // Center watermark
                int w = 350;
                int h = 350;

                int x = (getWidth()-w)/2;
                int y = (getHeight()-h)/2;

                g2.drawImage(logo,x,y,w,h,this);

                g2.setComposite(
                        AlphaComposite.getInstance(
                                AlphaComposite.SRC_OVER,1f));

            }

        };
            panel.setBackground(Color.WHITE);


        panel.setLayout(null);
        add(panel);


        // Left Logo
        ImageIcon leftIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/mdr2.png"));
        Image leftImg = leftIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
        JLabel leftLogo = new JLabel(new ImageIcon(leftImg));
        leftLogo.setBounds(50,0,100,100);
        panel.add(leftLogo);

        // Right Logo
        ImageIcon rightIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/fdr.png"));
        Image rightImg = rightIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
        JLabel rightLogo = new JLabel(new ImageIcon(rightImg));
        rightLogo.setBounds(560,1,100,100);
        panel.add(rightLogo);

        //==================== HEADER ====================

        heading = new JLabel("Akash & Aashish Health Centre");
        heading.setBounds(220,20,300,35);
        heading.setFont(new Font("Tahoma",Font.BOLD,15));
        heading.setForeground(Color.blue);
        panel.add(heading);

        JLabel subHeading = new JLabel("PATIENT RECEIPT");
        subHeading.setBounds(235,60,250,30);
        subHeading.setFont(new Font("Tahoma",Font.BOLD,20));
        subHeading.setForeground(Color.BLACK);
        panel.add(subHeading);

        JSeparator s1 = new JSeparator();
        s1.setBounds(20,100,650,2);
        panel.add(s1);

        //==================== LABELS ====================

        int x1 = 40;
        int x2 = 200;
        int y = 130;
        int gap = 40;

        lblPatientID = new JLabel("Patient ID :");
        lblPatientID.setBounds(x1,y,150,25);
        lblPatientID.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblPatientID);

        patientID = new JLabel();
        patientID.setBounds(x2,y,250,25);
        patientID.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(patientID);

        y += gap;

        lblName = new JLabel("Patient Name :");
        lblName.setBounds(x1,y,150,25);
        lblName.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblName);

        name = new JLabel();
        name.setBounds(x2,y,250,25);
        name.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(name);

        y += gap;

        lblMobile = new JLabel("Mobile :");
        lblMobile.setBounds(x1,y,150,25);
        lblMobile.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblMobile);

        mobile = new JLabel();
        mobile.setBounds(x2,y,250,25);
        mobile.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(mobile);

        y += gap;

        lblIdNumber = new JLabel("ID Number :");
        lblIdNumber.setBounds(x1,y,150,25);
        lblIdNumber.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblIdNumber);

        idNumber = new JLabel();
        idNumber.setBounds(x2,y,250,25);
        idNumber.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(idNumber);

        y += gap;

        lblGender = new JLabel("Gender :");
        lblGender.setBounds(x1,y,150,25);
        lblGender.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblGender);

        gender = new JLabel();
        gender.setBounds(x2,y,250,25);
        gender.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(gender);

        y += gap;

        lblDisease = new JLabel("Disease :");
        lblDisease.setBounds(x1,y,150,25);
        lblDisease.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblDisease);

        disease = new JLabel();
        disease.setBounds(x2,y,250,25);
        disease.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(disease);

        y += gap;

        lblRoom = new JLabel("Room No :");
        lblRoom.setBounds(x1,y,150,25);
        lblRoom.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblRoom);

        room = new JLabel();
        room.setBounds(x2,y,250,25);
        room.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(room);

        y += gap;

        lblCheckIn = new JLabel("Check In :");
        lblCheckIn.setBounds(x1,y,150,25);
        lblCheckIn.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblCheckIn);

        checkIn = new JLabel();
        checkIn.setBounds(x2,y,250,25);
        checkIn.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(checkIn);

        y += gap;

        lblDeposit = new JLabel("Deposit :");
        lblDeposit.setBounds(x1,y,150,25);
        lblDeposit.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblDeposit);

        deposit = new JLabel();
        deposit.setBounds(x2,y,250,25);
        deposit.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(deposit);

        y += gap;

        lblDate = new JLabel("Receipt Date :");
        lblDate.setBounds(x1,y,150,25);
        lblDate.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(lblDate);

        date = new JLabel();
        date.setBounds(x2,y,250,25);
        date.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(date);

        JSeparator s2 = new JSeparator();
        s2.setBounds(20,560,650,2);
        panel.add(s2);

        JLabel thanks = new JLabel("Thank You! Get Well Soon.");
        thanks.setBounds(210,570,280,25);
        thanks.setFont(new Font("Tahoma",Font.BOLD,18));
        panel.add(thanks);
        try {

            conn c = new conn();

            String q = "select * from patient_information where PatientID=" + patientId;

            ResultSet rs = c.statement.executeQuery(q);

            if(rs.next()){

                patientID.setText(rs.getString("PatientID"));
                name.setText(rs.getString("name"));
                mobile.setText(rs.getString("Mobile"));
                idNumber.setText(rs.getString("IdNumber"));
                gender.setText(rs.getString("Gender"));
                disease.setText(rs.getString("Disease"));
                room.setText(rs.getString("Room"));
                checkIn.setText(rs.getString("Time"));
                deposit.setText(rs.getString("Deposite"));

                date.setText(new java.util.Date().toString());

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new recipt(202601);
    }

}