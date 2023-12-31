package com.mycompany.userpage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;

public class LoginPage extends JFrame implements ActionListener{
    private JLabel l1,l2;
    private JTextField t1;
    private JPasswordField t2;
    private JButton b1,b2;
    
    public LoginPage()
    {
        this.setTitle("Login Page");
        this.setLocation(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p=(JPanel)this.getContentPane();
        
       
        l1=new JLabel("Login");
        t1=new JTextField(12);
        
        l2=new JLabel("Password");
        t2=new JPasswordField(12);
        
        b1= new JButton("Enter");
        b2= new JButton("Cancel");
       
        JPanel p1= new JPanel(new GridLayout(2,2));
        
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        
        
        JPanel p2= new JPanel();
        p2.add(b1);
        p2.add(b2);
        
        
        p.add(p1,BorderLayout.CENTER);
        p.add(p2,BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent e) {
        JButton bt= (JButton) e.getSource(); 
        if (bt == b1) {
            // Code for the "Enter" button
            String Name = t1.getText();
            String Password = t2.getText();
            String Type;

            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess:///Users/janafaisl/NetBeansProjects/visual-programming/Project/src/main/java/com/mycompany/userpage/Database.accdb");

                Statement st = c.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM [Database] WHERE Name='" + Name + "' AND Password='" + Password + "'");
                if (rs.next()) {
                    Type = rs.getString("Type");

                    if (Type.equals("Admin")) {
                        AdminPage page = new AdminPage("Text Editor");                       
                    } else if (Type.equals("User")) {
                        UserPage page = new UserPage("Text Editor");                       
                    }
                } else {                  
                    JOptionPane.showMessageDialog(this, "Invalid login or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                System.out.println("Exception : SQL");
                ex.printStackTrace();
            }
        }else{
            t1.setText("");
            t2.setText("");
               
        }
    }

}

    

