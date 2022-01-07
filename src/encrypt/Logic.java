package encrypt;


import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class Logic {
    GUI gui;
    
    
    Logic(GUI gui){
        this.gui = gui;
        
        
    }
    
    public void embedMessage(){
        System.out.println("embed message called");
        String message = gui.msg.getText();
        gui.output_img = gui.source_img.getSubimage(0, 0, gui.source_img.getWidth(), gui.source_img.getHeight());
        embedMessage(gui.output_img, message);
        JLabel l = new JLabel(new ImageIcon(gui.output_img));
        gui.embedded.getViewport().add(l);
        gui.validate();
                
    }
    
    public void embedMessage(BufferedImage output_img, String message){
        int msg_length = message.length();
        int img_height = output_img.getHeight();
        int img_width = output_img.getWidth();
        int img_size = img_height * img_width;
        
        if(msg_length > img_size * 8 + 32){
            JOptionPane.showMessageDialog(gui, "The entered text is too long for the image selected.", "Cannot Embed Image", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        embedInteger(output_img, msg_length, img_width, img_height);
        
        byte b[] = message.getBytes();
        
        for(int i = 0; i < b.length; i++){
            embedByte(output_img, b[i], i * 8 + 32, 0, img_width, img_height);
        }
        
    }
    
    public void embedInteger(BufferedImage output_img, int msg_length, int img_width, int img_height){
        int maxX = img_width;
        int maxY = img_height;
        int start = 0;
        int count = 0;
        int storageBit = 0;
        int startX = start / maxY;
        int startY = start - startX * maxY;
        
        for(int i = startX; i < maxX && count < 32; i++){
            for(int j = startY; j < maxY && count < 32; j++){
                int rgb = output_img.getRGB(i, j);
                int bit = getBitValue(msg_length, count);
                
                rgb = setBitValue(rgb, storageBit, bit);
                
                output_img.setRGB(i, j, rgb);
                
                count++;
                
            }
        }
    }
    
    public void embedByte(BufferedImage output_img, byte b, int start, int storageBit, int img_width, int img_height){
        int maxX = img_width;
        int maxY = img_height;
        int count = 0;
        int startX = start / maxY;
        int startY = start - startX * maxY;
        
        for(int i = startX; i < maxX && count < 32; i++){
            for(int j = startY; j < maxY && count < 32; j++){
                int rgb = output_img.getRGB(i, j);
                int bit = getBitValue(b, count);
                
                rgb = setBitValue(rgb, storageBit, bit);
                
                output_img.setRGB(i, j, rgb);
                
                count++;
                
            }
        }
    }

    public int getBitValue(int msg_length, int count){
        int v = msg_length & (int) Math.round(Math.pow(2, count));
        
        if(v == 0){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    public int setBitValue(int n, int location, int bit){
        int toggle = (int) Math.pow(2, location);
        int bv = getBitValue(n, location);
        
        if(bv == bit){
            return n;
        }
        if(bv == 0 && bit == 1){
            n |= toggle;
        }
        else if(bv == 1 && bit == 0){
            n ^= toggle;
        }
        
        return n;
    }

}
