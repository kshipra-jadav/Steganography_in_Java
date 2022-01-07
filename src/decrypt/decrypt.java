package decrypt;

import java.awt.GraphicsEnvironment;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class decrypt {

    
    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.setTitle("Decrypt");
        frame.drawGUI();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        frame.setVisible(true);
        
        frame.validate();
    
        
    }

}
