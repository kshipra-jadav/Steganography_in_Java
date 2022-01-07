package encrypt;

import java.awt.GraphicsEnvironment;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class Encrypt {

    
    public static void main(String[] args) {
        System.out.println("this is encrypt");
        GUI frame = new GUI();
        
        frame.setTitle("Encrypt");
        frame.drawGUI();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        frame.setVisible(true);
        frame.split.setDividerLocation(0.5);
        frame.validate();
        
        
    }

}
