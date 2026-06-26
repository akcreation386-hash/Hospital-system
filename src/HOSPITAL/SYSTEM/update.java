package HOSPITAL.SYSTEM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update extends JFrame {

    update() {


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

        Font labelFont = new Font("Tahoma", Font.BOLD, 16);

        JLabel heading = new JLabel("Update Patient Detail");
        heading.setBounds(260, 25, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setForeground(Color.BLACK); // ✅ BLACK
        panel.add(heading);

        Choice choice = new Choice();
        choice.setBounds(248, 90, 180, 25);
        panel.add(choice);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from patient_information");
            while (rs.next()) {
                choice.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔹 Labels (ALL BLACK)
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 90, 200, 25);
        lblName.setFont(labelFont);
        lblName.setForeground(Color.BLACK);
        panel.add(lblName);

        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(50, 130, 200, 25);
        lblRoom.setFont(labelFont);
        lblRoom.setForeground(Color.BLACK);
        panel.add(lblRoom);

        JTextField textFieldr = new JTextField();
        textFieldr.setBounds(248, 130, 180, 25);
        panel.add(textFieldr);

        JLabel lblTime = new JLabel("In-Time:");
        lblTime.setBounds(50, 170, 200, 25);
        lblTime.setFont(labelFont);
        lblTime.setForeground(Color.BLACK);
        panel.add(lblTime);

        JTextField textFieldtime = new JTextField();
        textFieldtime.setBounds(248, 170, 180, 25);
        panel.add(textFieldtime);

        JLabel lblAmount = new JLabel("Amount Paid:");
        lblAmount.setBounds(50, 210, 200, 25);
        lblAmount.setFont(labelFont);
        lblAmount.setForeground(Color.BLACK);
        panel.add(lblAmount);

        JTextField textFieldamount = new JTextField();
        textFieldamount.setBounds(248, 210, 180, 25);
        panel.add(textFieldamount);

        JLabel lblPending = new JLabel("Pending Amount:");
        lblPending.setBounds(50, 250, 200, 25);
        lblPending.setFont(labelFont);
        lblPending.setForeground(Color.BLACK);
        panel.add(lblPending);

        JTextField textFieldpending = new JTextField();
        textFieldpending.setBounds(248, 250, 180, 25);
        panel.add(textFieldpending);

        // 🔘 Buttons
        JButton check = new JButton("CHECK");
        check.setBounds(150, 350, 120, 35);
        check.setBackground(new Color(1,18,46));
        check.setForeground(Color.white);
        panel.add(check);

        JButton updateBtn = new JButton("UPDATE");
        updateBtn.setBounds(300, 350, 120, 35);
        updateBtn.setBackground(new Color(1,18,46));
        updateBtn.setForeground(Color.white);
        panel.add(updateBtn);

        JButton back = new JButton("BACK");
        back.setBounds(450, 350, 120, 35);
        back.setBackground(new Color(229,62,62));
        back.setForeground(Color.white);
        panel.add(back);

        // 🔹 CHECK LOGIC
        check.addActionListener(e -> {
            String name = choice.getSelectedItem();
            try {
                conn c = new conn();

                ResultSet rs1 = c.statement.executeQuery(
                        "select * from patient_information where Name='" + name + "'");

                if (rs1.next()) {
                    textFieldr.setText(rs1.getString("Room"));
                    textFieldtime.setText(rs1.getString("Time"));
                    textFieldamount.setText(rs1.getString("Deposite"));
                }

                ResultSet rs2 = c.statement.executeQuery(
                        "select * from rooms where room_no='" + textFieldr.getText() + "'");

                if (rs2.next()) {
                    String price = rs2.getString("Price");
                    int pending = Integer.parseInt(price)
                            - Integer.parseInt(textFieldamount.getText());
                    textFieldpending.setText(String.valueOf(pending));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 🔹 UPDATE LOGIC
        updateBtn.addActionListener(e -> {
            try {
                conn c = new conn();
                String q = choice.getSelectedItem();

                c.statement.executeUpdate(
                        "update patient_information set Room='" + textFieldr.getText() +
                                "', Time='" + textFieldtime.getText() +
                                "', Deposite='" + textFieldamount.getText() +
                                "' where Name='" + q + "'");

                JOptionPane.showMessageDialog(null, "Updated successfully");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        back.addActionListener(e -> setVisible(false));

        // 🖼️ IMAGE FIX
        try {
            ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Icon/updat.png"));
            Image i = img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(i));
            imageLabel.setBounds(550, 120, 200, 200);
            panel.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Image not found. Check path: Icon/updat.png");
        }

        setUndecorated(true);
        setSize(850, 450);
        setLocation(250, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new update();
    }
}