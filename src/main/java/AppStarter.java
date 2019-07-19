import Model.CSVFileReader;
import Model.XLSReader;
import View.UserInterface;

import java.io.IOException;

public class AppStarter {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        //ui.init();
        try{
            CSVFileReader reader = new CSVFileReader();
            reader.read();
        }
        catch (IOException e){
            System.out.println("Не найден файл");
        }

    }
}
