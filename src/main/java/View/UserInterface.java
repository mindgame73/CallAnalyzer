package View;

import javax.swing.*;
import java.awt.*;

public class UserInterface {

    private JButton readButton;
    public void init(){
        JFrame jFrame = new JFrame("���������� ������� 2.0");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        readButton = new JButton("�������");
        jFrame.add(readButton);
        jFrame.setSize(dimension.width,dimension.height-50);
        jFrame.setVisible(true);
    }

    public JButton getReadButton() {
        return readButton;
    }
}
