package Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RowServiceTableModel extends AbstractTableModel {
        private ArrayList<RowService> rowServices;

    public RowServiceTableModel(ArrayList<RowService> rowServices){
            super();
            this.rowServices = rowServices;
        }


    @Override
    public int getRowCount() {
        return rowServices.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c){
            case 0:
                return r+1;
            case 1:
                if (rowServices.get(r).getPhone() != null)
                    return rowServices.get(r).getPhone().getDivision();
                else
                    return "";
            case 2:
                if (rowServices.get(r).getPhone() != null)
                    return rowServices.get(r).getPhone().getEmployeeName();
                else
                    return "";
            case 3:
                if (rowServices.get(r).getPhone() != null)
                    return rowServices.get(r).getPhone().getPhone();
                else
                    return "";
            case 4:
                return rowServices.get(r).getCode();
            case 5:
                return rowServices.get(r).getServiceName();
            case 6:
                return rowServices.get(r).getAmount();
            case 7:
                return rowServices.get(r).getCost();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "№";
                break;
            case 1:
                result = "Подразделение";
                break;
            case 2:
                result = "ФИО";
                break;
            case 3:
                result = "Телефон";
                break;
            case 4:
                result = "Код услуги";
                break;
            case 5:
                result = "Наим. услуги";
                break;
            case 6:
                result = "Количество";
                break;
            case 7:
                result = "Стоимость, руб.";
                break;
        }
        return result;
    }
}
