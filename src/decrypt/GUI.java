package decrypt;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class GUI extends JFrame implements ActionListener{
    JButton open = new JButton("Open");
    JButton decode = new JButton("Decode");
    JButton reset = new JButton("Reset");
    JScrollPane img = new JScrollPane();
    JTextArea msg = new JTextArea(10, 3);
    BufferedImage embedded_img = null;
    Logic l1 = new Logic(this);
    
    public void drawGUI(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(open);
        panel.add(decode);
        panel.add(reset);
        open.addActionListener(this);
        decode.addActionListener(this);
        reset.addActionListener(this);
        this.getContentPane().add(panel, BorderLayout.NORTH);
        
        Border border = null;
        panel.setBorder(BorderFactory.createTitledBorder(border, "<html><b>Developed By - Kshipra Jadav, 3rd Semester B. Tech CSE</b></html>",TitledBorder.CENTER, ICONIFIED));
        
        panel = new JPanel(new GridLayout(1,1));
        panel.add(new JScrollPane(msg));
        msg.setBorder(BorderFactory.createTitledBorder(border, "Decoded Message", TitledBorder.CENTER, ICONIFIED, new Font("Arial", Font.BOLD, 20), Color.yellow));
        msg.setBackground(Color.BLACK);
        msg.setForeground(Color.white);
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        msg.setEditable(false);
        this.getContentPane().add(panel, BorderLayout.SOUTH);
        
        
        img.setBorder(BorderFactory.createTitledBorder("Stenographed Image"));
        this.getContentPane().add(img, BorderLayout.CENTER);
        
        
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if(o == open){
            openImage();
        }
        else if(o == reset){
            reset();
        }
        else if(o == decode){
            l1.decodeMessage();
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    public void openImage(){
        FileDialog fd = new FileDialog(this, "Open a file", FileDialog.LOAD);
        fd.setVisible(true);
        String path = fd.getDirectory() + fd.getFile();
        File temp_image = new File(path);
        try{
            embedded_img = ImageIO.read(temp_image);
            JLabel l = new JLabel(new ImageIcon(embedded_img));
            img.getViewport().add(l);
            this.validate();

        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void reset(){
        msg.setText("");
        img.getViewport().removeAll();
        embedded_img = null;
        this.validate();
    }

}
