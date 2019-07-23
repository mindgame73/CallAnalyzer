package Controller;

import Model.*;
import View.UserInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class UserInterfaceController {
    public static ArrayList<RowService> rowServices = new ArrayList<>();
    public static HashSet<Long> codeSet = new HashSet<>();
    public static TreeSet<String> divisionSet = new TreeSet<>();

    private UserInterface view;
    private JButton readButton;
    private JButton fillButton;
    private JButton refreshButton;
    private JButton reportButton;
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
        reportButton = view.getReportButton();
    }

    public void init(){
        readButton.addActionListener(e->{
            readCSV();
        });

        fillButton.addActionListener(e->{
           fillXLS();
           reportButton.setEnabled(true);
        });

        // removing from all
        refreshButton.addActionListener(e ->{
            rowServices.clear();
            codeSet.clear();

            rowServiceTableModel.fireTableDataChanged();
            readButton.setEnabled(true);
            fillButton.setEnabled(false);
            reportButton.setEnabled(false);
            refreshButton.setEnabled(false);
        });


        reportButton.addActionListener(e->{
            try {
                File outputCSVFile = new File("output.csv");
                FileWriter fw = new FileWriter(outputCSVFile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Файл сгенерирован \n" + new Date());
                bw.write("\n\nПодразделение;Код;Наименование;Общ.кол-во;Общ.стоимость без НДС");
                DivisionSummator divisionSummator = new DivisionSummator();
                for (String s : UserInterfaceController.divisionSet) {
                    bw.write("\n" + s);
                    bw.write(divisionSummator.doCalculate(divisionSummator.getDivisionBuffer(s)));
                }
                bw.close();
            }
            catch (IOException ex){
                JOptionPane.showMessageDialog(null,"Ошибка записи в файл " +
                        fileChooser.getSelectedFile().getPath(),"Ошибка",JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    private void readCSV(){
        try{
            fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files only *.csv ", "csv"));
            int ret = fileChooser.showDialog(null,"Открыть файл");
            //if (ret == JFileChooser.APPROVE_OPTION){
                //CSVFileReader reader = new CSVFileReader(fileChooser.getSelectedFile().getPath());
            CSVFileReader reader = new CSVFileReader("");
                rowServices = reader.read();
                rowServiceTableModel.fireTableDataChanged();
                readButton.setEnabled(false);
                refreshButton.setEnabled(true);
                fillButton.setEnabled(true);
            //}
        }
        catch (IOException ex){
            JOptionPane.showMessageDialog(null,"Ошибка чтения файла " +
                            fileChooser.getSelectedFile().getPath(),"Ошибка",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillXLS(){
        try {
            fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new FileNameExtensionFilter("XLS Files only *.xls ", "xls"));
            int ret = fileChooser.showDialog(null,"Открыть файл");
            //if (ret == JFileChooser.APPROVE_OPTION){
                //XLSReader xlsReader = new XLSReader(fileChooser.getSelectedFile().getPath());
                XLSReader xlsReader = new XLSReader("");
                xlsReader.fillEmployee();
                rowServices.sort(new RowServiceComparator());
                rowServiceTableModel.fireTableDataChanged();

            //}
        }
        catch (IOException ex){
            JOptionPane.showMessageDialog(null,"Ошибка чтения файла " +
                    fileChooser.getSelectedFile().getPath(),"Ошибка",JOptionPane.ERROR_MESSAGE);
        }
    }
}
