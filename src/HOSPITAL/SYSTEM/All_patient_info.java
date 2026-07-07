package HOSPITAL.SYSTEM;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class All_patient_info extends JFrame {

    JTextField txtPatientID;
    JButton bReceipt, bBack;

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
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                txtPatientID.setText(table.getValueAt(row,0).toString());

            }

        });

        JLabel lblID = new JLabel("Patient ID :");
        lblID.setBounds(180,370,90,30);
        lblID.setFont(new Font("Tahoma",Font.BOLD,15));
        panel.add(lblID);

        txtPatientID = new JTextField();
        txtPatientID.setBounds(270,370,120,30);
        panel.add(txtPatientID);

        bReceipt = new JButton("Receipt");
        bReceipt.setBounds(420,370,120,30);
        bReceipt.setBackground(new Color(0,102,204));
        bReceipt.setForeground(Color.WHITE);
        panel.add(bReceipt);
        bReceipt.addActionListener(e -> {

            if(txtPatientID.getText().equals("")){

                JOptionPane.showMessageDialog(null,"Please Select Patient");

            }else{

                int id = Integer.parseInt(txtPatientID.getText());

                new recipt(id);

            }

        });

        JButton b2 = new JButton("BACK");
        b2.setBounds(570, 370, 120, 30);
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