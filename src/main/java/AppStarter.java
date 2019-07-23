import Controller.UserInterfaceController;
import Model.DivisionSummator;
import View.UserInterface;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.util.Locale;


public class AppStarter {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UserInterface ui = new UserInterface();
        ui.init();

        UserInterfaceController controller = new UserInterfaceController(ui);
        controller.init();
    }
}
