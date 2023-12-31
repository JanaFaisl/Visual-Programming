
package com.mycompany.userpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class Search extends JFrame implements ActionListener{
    private JLabel l1,l2;
    private JTextField t1, t2;
    private JTextArea t3;
    private JButton b1, b2, b3;

    
    public Search(String title){
        super(title);
        
        l1= new JLabel("Title:");
        l2= new JLabel("Keywords:");
        
        t1= new JTextField(15);
        t2= new JTextField(15);
        
        t3= new JTextArea(15, 50);

        b1= new JButton("Search");
        b2= new JButton("Clear");
        b3= new JButton("Cancel");
        
        b1.addActionListener(this);        
        b2.addActionListener(this);        
        b3.addActionListener(this);        

        JPanel p= (JPanel)this.getContentPane();
       
        JPanel j1= new JPanel(); 
        JPanel j2= new JPanel(); 
        JPanel j3= new JPanel(); 

        
        
        j1.add(l1);
        j1.add(t1);

        j2.add(l2);        
        j2.add(t2); 
        
        j2.add(b1); 
        j2.add(b2);
        j2.add(b3);
        
        j3.add(t3);
        
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
        if (bt == b1){
            File file = new File(t1.getText() + ".txt");
            if (file.exists()){
                String keyWord = t2.getText();
                print(keyWord, file);
            }else{
                JOptionPane.showMessageDialog(this, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (bt == b2){
            t1.setText("");  
            t2.setText(""); 
            t3.setText("");
        }else 
            this.setVisible(false);

    }
    
    
    
    public void print(String keyWord, File file){
    String line, students[], name, match;
    int grade;
    String content = "";
    boolean keywordFound = false;

    try{
        BufferedReader br = new BufferedReader(new FileReader(file));

        while((line = br.readLine()) != null){
            students = line.split(";");
            name = students[0];
            grade = Integer.parseInt(students[1]);

            if(name.equalsIgnoreCase(keyWord)){
                keywordFound = true;
                match = name + " " + grade;
                content += match + "\n";
            }
        }

        if (keywordFound) {
            t3.setText(content);
        } else {
            JOptionPane.showMessageDialog(this, "Keyword not found", "Error", JOptionPane.ERROR_MESSAGE);
        }

        br.close();

    } catch(FileNotFoundException e){
        System.out.println("FileNotFoundException error");            
    } catch(IOException e){
        System.out.println("IOException error" + e.getMessage());
    }
}

}
    