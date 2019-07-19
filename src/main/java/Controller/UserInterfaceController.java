package Controller;

import Model.CSVFileReader;
import View.UserInterface;

import javax.swing.*;
import java.io.IOException;

public class UserInterfaceController {
    private UserInterface view;
    private JButton readButton;

    public UserInterfaceController(UserInterface view){
        this.view = view;
        readButton = view.getReadButton();
    }

    public void init(){
        readButton.addActionListener(e->{
            readCSV();
        });

    }

    private void readCSV(){
        try{
            CSVFileReader reader = new CSVFileReader();
            reader.read();
            reader.printRowServices();
        }
        catch (IOException ex){
            System.out.println("Не найден файл");
        }
    }
}
