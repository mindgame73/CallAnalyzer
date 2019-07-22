package Controller;

import Model.CSVFileReader;
import Model.PhoneNumber;
import Model.RowService;
import Model.RowServiceTableModel;
import View.UserInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserInterfaceController {
    public static ArrayList<RowService> rowServices = new ArrayList<>();
    public static HashSet<Long> codeSet = new HashSet<>();
    private UserInterface view;
    private JButton readButton;
    private JTable serviceTable;
    private JFrame jFrame;
    private RowServiceTableModel rowServiceTableModel;

    public UserInterfaceController(UserInterface view){
        this.view = view;
        readButton = view.getReadButton();
        serviceTable = view.getServiceTable();
        jFrame = view.getjFrame();
        rowServiceTableModel = view.getRowServiceTableModel();
    }

    public void init(){
        readButton.addActionListener(e->{
            readCSV();
        });

    }

    private void readCSV(){
        try{
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files only *.csv ", "csv"));
            int ret = fileChooser.showDialog(null,"Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION){
                CSVFileReader reader = new CSVFileReader(fileChooser.getSelectedFile().getPath());
                rowServices = reader.read();
                rowServiceTableModel.fireTableDataChanged();
                reader.printRowServices();
                readButton.setEnabled(false);
            }
        }
        catch (IOException ex){
            System.out.println("Не найден файл");
        }
    }
}
