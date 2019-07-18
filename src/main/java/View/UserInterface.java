package View;

import javax.swing.*;
import java.awt.*;

public class UserInterface {

    public void init(){
        JFrame jFrame = new JFrame("Анализатор звонков 2.0");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setSize(dimension.width,dimension.height-50);
        jFrame.setVisible(true);
    }
}
