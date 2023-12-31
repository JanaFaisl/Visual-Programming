
package com.mycompany.userpage;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class AdminPage extends JFrame implements ActionListener {
    
    private JLabel l1;
    private JTextField t1;
    private JTextArea t2;
    private JButton b1, b2, b3,b4;
    private String x; 	

    
    public AdminPage(String title){
        super(title);
        this.setLocation(200,200);
        l1= new JLabel("Title:");
        
        t1= new JTextField(15);
        t2= new JTextArea(15, 40);
        
        b1= new JButton("Save");
        b2= new JButton("Search");
        b3= new JButton("Clear");
        b4= new JButton("Statistics");
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);        
        b3.addActionListener(this);
        b4.addActionListener(this);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        JPanel p= (JPanel)this.getContentPane();
      
        
        JPanel j1= new JPanel(); 
        JPanel j2= new JPanel(); 
        JPanel j3= new JPanel(); 
        
        j1.add(l1);
        j1.add(t1);
        
        j2.add(t2); 
        
        j3.add(b1); 
        j3.add(b2);
        j3.add(b3);
        j3.add(b4);
        

        
        p.add(j1, BorderLayout.NORTH);
        p.add(j2, BorderLayout.CENTER);
        p.add(j3, BorderLayout.SOUTH);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
      
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bt= (JButton) e.getSource(); 
        if(bt==b1){
            String text = t2.getText();
            try {
                loadFile(text);
                JOptionPane.showMessageDialog(null, "Text saved to file successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving text to file.");
            }
        }           
        else if(bt == b2){
            Search s = new Search("Search");  
        }
        else if(bt == b4){
            Statics ss =new Statics();
        }
        else{
            t1.setText("");  
            t2.setText(""); 
        }
    }

    public void loadFile(String text){         
        try{            
            BufferedWriter br = new BufferedWriter(new FileWriter(t1.getText() + ".txt"));             
            text = text.replaceAll(" ", ";");
            br.write(text);
            br.close();                       
        }
        catch(IOException e){             
            e.printStackTrace();         
        }   
    }               
}