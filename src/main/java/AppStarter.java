import Model.XLSReader;
import View.UserInterface;

import java.io.IOException;

public class AppStarter {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        //ui.init();
        try{
            XLSReader xlsReader = new XLSReader();
            xlsReader.readXLS();
        }
        catch (IOException e){
            System.out.println("Не найден файл");
        }

    }
}
