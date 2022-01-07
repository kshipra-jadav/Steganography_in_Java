package decrypt;

import java.awt.image.BufferedImage;


public class Logic {
    GUI gui;

    public Logic(GUI gui) {
        this.gui = gui;
    }

    public void decodeMessage(){
        System.out.println("Decode message called.");
        
        int len = extractInteger(gui.embedded_img, 0, 0);
        
        byte b[] = new byte[len];
        
        for(int i = 0; i < len; i++){
            b[i] = extractByte(gui.embedded_img, i * 8 + 32, 0);
            gui.msg.setText(new String(b));
        }
        
        
    }
    
    public int extractInteger(BufferedImage img, int start, int storageBit){
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start / maxY;
        int startY = start - startX * maxY;
        int count = 0;
        int length = 0;
        
        for(int i = startX; i < maxX && count < 32; i++){
            for(int j = startY; j < maxY && count < 32; j++){
                int rgb = img.getRGB(i, j);
                int bit = getBitValue(rgb, storageBit);
                
                length = setBitValue(length, count, bit);
                
                count++;
            }
        }
        return length;
        
    }
    
    public byte extractByte(BufferedImage img, int start, int storageBit){
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start / maxY;
        int startY = start - startX * maxY;
        int count = 0;
        byte b = 0;
        
        for(int i = startX; i < maxX && count < 8; i++){
            for(int j = startY; j < maxY && count < 8; j++){
                int rgb = img.getRGB(i, j);
                int bit = getBitValue(rgb, storageBit);
                
                b = (byte)setBitValue(b, count, bit);
                
                count++;
            }
        }
        
        return b;
    }
    
    public int getBitValue(int n, int count){
        int v = n & (int) Math.round(Math.pow(2, count));
        
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
