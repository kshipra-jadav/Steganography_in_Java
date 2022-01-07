package encrypt;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class GUI extends JFrame implements ActionListener {
    JButton open = new JButton("Open");
    JButton save = new JButton("Save");
    JButton reset = new JButton("Reset");
    JButton embed = new JButton("Embed");
    public JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    
    JScrollPane original = new JScrollPane();
    JScrollPane embedded = new JScrollPane();
    JTextArea msg = new JTextArea(10, 3);
    Logic l1 = new Logic(this);
    BufferedImage source_img = null;
    BufferedImage output_img = null;

    public void drawGUI(){
        JPanel panel = new JPanel(new FlowLayout());
        this.add(panel);
        panel.add(open);
        panel.add(save);
        panel.add(reset);
        panel.add(embed);
        open.addActionListener(this);
        save.addActionListener(this);
        reset.addActionListener(this);
        embed.addActionListener(this);
        
        this.getContentPane().add(panel, BorderLayout.NORTH);
        Border border = null;
        panel.setBorder(BorderFactory.createTitledBorder(border, "<html><b>Developed By - Kshipra Jadav, 3rd Semester B. Tech CSE</b></html>",TitledBorder.CENTER, ICONIFIED));
        
        
        panel = new JPanel(new GridLayout(1, 1));
        panel.add(new JScrollPane(msg));
        msg.setBorder(BorderFactory.createTitledBorder(border, "Enter Message", TitledBorder.CENTER, ICONIFIED, new Font("Arial", Font.BOLD, 20), Color.yellow));
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        msg.setBackground(Color.BLACK);
        msg.setForeground(Color.white);
        this.getContentPane().add(panel, BorderLayout.SOUTH);
        
        split.setLeftComponent(original);
        split.setRightComponent(embedded);
        original.setBorder(BorderFactory.createTitledBorder("Original Image"));
        embedded.setBorder(BorderFactory.createTitledBorder("Stegnographed Image"));
        this.getContentPane().add(split, BorderLayout.CENTER);
        
                
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == open){
            openImage();
        }
        else if(o == save){
            saveImage();
        }
        else if(o == reset){
            reset();
        }
        else if(o == embed){
            l1.embedMessage();
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
            source_img = ImageIO.read(temp_image);
            JLabel l = new JLabel(new ImageIcon(source_img));
            original.getViewport().add(l);
            this.validate();

        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void saveImage(){
        FileDialog fd = new FileDialog(this, "Save The Image", FileDialog.SAVE);
        fd.setVisible(true);
        String path = fd.getDirectory() + fd.getFile() + ".png";
        File output = new File(path);
        try{
            ImageIO.write(output_img, "png", output);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void reset(){
        msg.setText("");
        original.getViewport().removeAll();
        embedded.getViewport().removeAll();
        source_img = null;
        output_img = null;
        split.setDividerLocation(0.5);
        this.validate();
    }

}
