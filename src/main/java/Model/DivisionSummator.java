package Model;

import Controller.UserInterfaceController;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DivisionSummator {
    private ArrayList<RowService> tmp = new ArrayList<>();
    private File outputCSVfile;
    private StringBuilder result;

    public ArrayList<RowService> getDivisionBuffer(String division){
        tmp.clear();
        for (RowService rowService : UserInterfaceController.rowServices) {
            if (rowService.getEmployee() != null){
                if (rowService.getEmployee().getDivision() != null){
                    if(rowService.getEmployee().getDivision().equals(division)){
                        tmp.add(rowService);
                    }
                }
            }
        }
        return tmp;
    }

    public strictfp String doCalculate(ArrayList<RowService> tmp){
        result = new StringBuilder();
        this.tmp = tmp;

        UserInterfaceController.codeSet.clear();
        for (RowService rowService : tmp) {
            UserInterfaceController.codeSet.add(rowService.getCode());
        }

        for (Long code : UserInterfaceController.codeSet) {
            float amount = 0;
            float cost = 0;
            String serviceName = "";
            for (RowService rowService : tmp) {
                if (rowService.getCode() == code){
                    serviceName = rowService.getServiceName();
                    amount+=rowService.getAmount();
                    cost+=rowService.getCost();
                }
            }
            result.append("\n;"+ serviceName + ";" + code + ";" + parseToString(amount) + ";" + parseToString(cost));

        }
        return result.toString();
    }

    public String parseToString(float num){
        return String.format(Locale.GERMANY, "%.2f", num);
    }
}
