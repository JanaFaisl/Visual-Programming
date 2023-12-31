 
package com.mycompany.userpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
public class Statics extends JFrame implements ActionListener{
    private JTextField t1;
    private JButton b1,b2,b3;
    public Statics(){
        
        setTitle("statistics");
        t1= new JTextField(30);
        b1=new JButton("Calculate");
        b2= new JButton("Clear");
        b3= new JButton("Cancel");
        JPanel p= (JPanel)this.getContentPane();
        
        JPanel p1= new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(t1);
        p1.add(b1);
         p1.add(b2); p1.add(b3);
         
        p.add(p1,FlowLayout.LEFT);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);  
       
        
    }
    public void actionPerformed(ActionEvent e){
        
        JButton bt= (JButton)e.getSource();
        
        if(bt ==b1){
            String students[], name,line,message;

            int grade,grater=0,less=0;
            try{
                BufferedReader br = new BufferedReader(new FileReader(t1.getText() + ".txt"));
                while((line = br.readLine()) != null){
                    students = line.split(";");
                    grade = Integer.parseInt(students[1]);
                    if(grade<10){
                        less++;
                    }else{
                        grater++;
                    }
                    
                }  
                br.close();
                
                message="Number of lines whith numbers >= 10: "+grater+"\nNumber of lines whith numbers < 10: "+less;
                JOptionPane.showMessageDialog(null, message,"Statistics ",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(FileNotFoundException ee){
                JOptionPane.showMessageDialog(null,"FileNotFoundException error","Error",JOptionPane.ERROR_MESSAGE);            
            }catch(IOException ee){
                JOptionPane.showMessageDialog(null,"IOException error","Error",JOptionPane.ERROR_MESSAGE); 
                 ee.printStackTrace();
            }catch(NumberFormatException ee){
                JOptionPane.showMessageDialog ( null , "Incorrect entered data", "ERROR", JOptionPane.ERROR_MESSAGE);
           }
        }
        else if(bt ==b2){
               t1.setText("");  
        }else if(bt==b3){
             this.setVisible(false); 
        }
        
        
    }
}
    
    
    
    
   
