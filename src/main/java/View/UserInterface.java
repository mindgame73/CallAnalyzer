package View;

import Controller.UserInterfaceController;
import Model.RowService;
import Model.RowServiceTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class UserInterface {

    private JButton readButton;
    private JButton fillButton;
    private JButton refreshButton;
    private JButton reportButton;
    private JTable serviceTable;
    private JFrame jFrame;
    private RowServiceTableModel rowServiceTableModel;
    private String openCSVPath = "src/main/resources/images/open.png";
    private String openXLSPath = "src/main/resources/images/open2.png";
    private String refreshPath = "src/main/resources/images/refresh.png";
    private String reportPath = "src/main/resources/images/report-excel.png";
    private String faviconPath = "src/main/resources/images/icon.jpg";

    public JTable getServiceTable() {
        return serviceTable;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public JButton getFillButton() {
        return fillButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public RowServiceTableModel getRowServiceTableModel() {
        return rowServiceTableModel;
    }

    public JButton getReportButton() {
        return reportButton;
    }

    public void init(){
        jFrame = new JFrame("Анализатор звонков 2.0");
        JPanel buttonsPanel = new JPanel();
        jFrame.setIconImage(new ImageIcon(faviconPath).getImage());
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        readButton = new JButton("Считать файл детализации", new ImageIcon(openCSVPath));
        fillButton = new JButton("Считать файл справочника НИИАР", new ImageIcon(openXLSPath));
        refreshButton = new JButton("Обновить", new ImageIcon(refreshPath));
        reportButton = new JButton("Вычислить и сохранить отчет", new ImageIcon(reportPath));
        refreshButton.setEnabled(false);
        fillButton.setEnabled(false);
        reportButton.setEnabled(false);
        rowServiceTableModel = new RowServiceTableModel(UserInterfaceController.rowServices);
        serviceTable = new JTable(rowServiceTableModel);
        for (int i = 0; i < serviceTable.getColumnModel().getColumnCount(); i++) {
            TableColumn tableColumn = serviceTable.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    tableColumn.setMaxWidth(40);
                    break;
                case 3:
                    tableColumn.setMaxWidth(120);
                    tableColumn.setPreferredWidth(100);
                    break;
                case 4:
                    tableColumn.setMaxWidth(120);
                    tableColumn.setPreferredWidth(100);
                    break;
                case 5:
                    tableColumn.setPreferredWidth(350);
                    break;
            }
        }
        JScrollPane jscrlp = new JScrollPane(serviceTable);
        jFrame.add(jscrlp,BorderLayout.CENTER);
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(readButton);
        buttonsPanel.add(fillButton);
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(reportButton);
        jFrame.add(buttonsPanel,BorderLayout.SOUTH);
        jFrame.setSize(dimension.width,dimension.height-50);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getReadButton() {
        return readButton;
    }
}
