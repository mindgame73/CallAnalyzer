package Model;

import Controller.UserInterfaceController;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;

public class DivisionSummator {
    private ArrayList<RowService> tmp;

    public ArrayList<RowService> getDivisionBuffer(String division){
        tmp = new ArrayList<>();
        for (RowService rowService : UserInterfaceController.rowServices) {
            if (rowService.getEmployee() != null){
                if (rowService.getEmployee().getDivision() != null){
                    while(rowService.getEmployee().getDivision().equals(division)){
                        tmp.add(rowService);
                    }
                }
            }
        }
        return tmp;
    }

    public void doCalculate(){
         float amount = 0;
         float cost = 0;

        UserInterfaceController.codeSet.clear();

        for (RowService rowService : tmp) {
            UserInterfaceController.codeSet.add(rowService.getCode());
        }

        for (Long code : UserInterfaceController.codeSet) {
            for (RowService rowService : tmp) {
                if (rowService.getCost() == code){
                    amount+=rowService.getAmount();
                    cost+=rowService.getCost();
                }
            }
            System.out.println(tmp.get(0).getEmployee().getDivision());
            System.out.println(code);
            System.out.println(amount);
            System.out.println(cost);
        }
    }
}
