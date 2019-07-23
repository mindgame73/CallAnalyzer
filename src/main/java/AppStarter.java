import Controller.UserInterfaceController;
import View.UserInterface;


public class AppStarter {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.init();

        UserInterfaceController controller = new UserInterfaceController(ui);
        controller.init();

    }
}
