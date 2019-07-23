package Controller;

import Model.*;
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
    public static HashSet<String> divisionSet = new HashSet<>();

    private UserInterface view;
    private JButton readButton;
    private JButton fillButton;
    private JButton refreshButton;
    private JTable serviceTable;
    private JFrame jFrame;
    private RowServiceTableModel rowServiceTableModel;
    private JFileChooser fileChooser;

    public UserInterfaceController(UserInterface view){
        this.view = view;
        readButton = view.getReadButton();
        serviceTable = view.getServiceTable();
        jFrame = view.getjFrame();
        rowServiceTableModel = view.getRowServiceTableModel();
        fillButton = view.getFillButton();
        refreshButton = view.getRefreshButton();
    }

    public void init(){
        readButton.addActionListener(e->{
            readCSV();
        });

        fillButton.addActionListener(e->{
           fillXLS();
           DivisionSummator divisionSummator = new DivisionSummator();
            for (String s : UserInterfaceController.divisionSet) {
                divisionSummator.getDivisionBuffer(s);
                divisionSummator.doCalculate();
            }
        });

        // removing from all
        refreshButton.addActionListener(e ->{
            rowServices.clear();
            codeSet.clear();

            rowServiceTableModel.fireTableDataChanged();
            readButton.setEnabled(true);
            refreshButton.setEnabled(false);
        });

    }

    private void readCSV(){
        try{
            fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files only *.csv ", "csv"));
            int ret = fileChooser.showDialog(null,"Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION){
                CSVFileReader reader = new CSVFileReader(fileChooser.getSelectedFile().getPath());
                rowServices = reader.read();
                rowServiceTableModel.fireTableDataChanged();
                reader.printRowServices();
                readButton.setEnabled(false);
                refreshButton.setEnabled(true);
            }
        }
        catch (IOException ex){
            System.out.println("Не найден файл");
        }
    }

    private void fillXLS(){
        try {
            fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileNameExtensionFilter("XLS Files only *.xls ", "xls"));
            int ret = fileChooser.showDialog(null,"Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION){
                XLSReader xlsReader = new XLSReader(fileChooser.getSelectedFile().getPath());
                xlsReader.fillEmployee();
                rowServices.sort(new RowServiceComparator());
                rowServiceTableModel.fireTableDataChanged();

            }
        }
        catch (IOException ex){
            System.out.println("Не найден файл");
        }
    }
}
