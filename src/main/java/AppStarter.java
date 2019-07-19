import Controller.UserInterfaceController;
import Model.CSVFileReader;
import Model.XLSReader;
import View.UserInterface;

import java.io.IOException;

public class AppStarter {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.init();

        UserInterfaceController controller = new UserInterfaceController(ui);
        controller.init();
    }
}
