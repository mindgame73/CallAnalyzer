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
import java.text.SimpleDateFormat;
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
            int choise = JOptionPane.showConfirmDialog(null,"Считанная информация будет удалена, " +
                            " для отмены нажмите Cancel",
                    "Очистка таблицы",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if (choise == 0){
                rowServices.clear();
                codeSet.clear();

                rowServiceTableModel.fireTableDataChanged();
                readButton.setEnabled(true);
                fillButton.setEnabled(false);
                reportButton.setEnabled(false);
                refreshButton.setEnabled(false);
            }

        });


        reportButton.addActionListener(e->{
            try {
                fileChooser  = new JFileChooser(".");
                fileChooser.setDialogTitle("Сохранение файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files only *.csv", "csv"));
                fileChooser.setSelectedFile(new File(getSimpleDate()));

                int result = fileChooser.showSaveDialog(null);
                // Если файл выбран, то представим его в сообщении
                if (result == JFileChooser.APPROVE_OPTION ){
                    File outputCSVFile = new File(fileChooser.getSelectedFile().getPath() + ".csv");
                    FileWriter fw = new FileWriter(outputCSVFile, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Файл сгенерирован \n" + new Date());
                    bw.write("\n\nПодразделение;Наименование;Код;Общ.кол-во;Общ.стоимость без НДС");
                    DivisionSummator divisionSummator = new DivisionSummator();
                    for (String s : divisionSet) {
                        bw.write("\n" + s);
                        bw.write(divisionSummator.doCalculate(divisionSummator.getDivisionBuffer(s)));
                    }

                    bw.write("\n\n\nНИИАР(остальная часть)");
                    for (RowService rowService: rowServices){
                        bw.write(divisionSummator.printRestPart(rowService));
                    }
                    bw.close();

                    JOptionPane.showMessageDialog(null,
                            "Файл ' (" + fileChooser.getSelectedFile().getPath() +
                                    ".csv) сохранен");
                }
            }
            catch (IOException ex){
                System.out.println(ex);
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
            if (ret == JFileChooser.APPROVE_OPTION){
                CSVFileReader reader = new CSVFileReader(fileChooser.getSelectedFile().getPath());
                rowServices = reader.read();
                rowServiceTableModel.fireTableDataChanged();
                readButton.setEnabled(false);
                refreshButton.setEnabled(true);
                fillButton.setEnabled(true);
            }
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
            if (ret == JFileChooser.APPROVE_OPTION){
                XLSReader xlsReader = new XLSReader(fileChooser.getSelectedFile().getPath());
                xlsReader.fillEmployee();
                rowServices.sort(new RowServiceComparator());
                rowServiceTableModel.fireTableDataChanged();

            }
        }
        catch (IOException ex){
            JOptionPane.showMessageDialog(null,"Ошибка чтения файла " +
                    fileChooser.getSelectedFile().getPath(),"Ошибка",JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getSimpleDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY_HHmmss");
        return simpleDateFormat.format(new Date());
    }
}
