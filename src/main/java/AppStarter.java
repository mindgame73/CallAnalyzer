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

        try {
            XLSReader xlsReader = new XLSReader();
            xlsReader.findRowByValue(8423579676L);
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }
}
