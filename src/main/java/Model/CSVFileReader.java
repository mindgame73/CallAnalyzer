package Model;

import Controller.UserInterfaceController;
import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class CSVFileReader {
    private final CSVReader reader;
    private int blocksRead = 0;
    private boolean isServiceBlock;
    private ArrayList<RowService> rowServices = UserInterfaceController.rowServices;
    private HashSet<Long> codeSet = UserInterfaceController.codeSet;
    public CSVFileReader(String path) throws IOException {
        reader = new CSVReader(new FileReader(path),';');
    }

    public ArrayList<RowService> read() throws IOException{

        String[] line;

        while((line=reader.readNext())!=null){
            String category = line[0].toLowerCase();
            if (category.contains("постоянные услуги")||category.contains("разовые услуги")){
                isServiceBlock = true;
                blocksRead++;
                continue;

            }
            if (blocksRead > 2) break;
            if (isServiceBlock){
                if(!line[2].equals("")){
                    RowService rs = new RowService();
                    if (!line[0].equals("")) {
                        Employee employee = new Employee();
                        employee.setPhone(Long.parseLong(line[0]));
                        rs.setEmployee(employee);
                    }
                    if (!line[2].equals("")) {
                        rs.setCode(Long.parseLong(line[2]));
                    }
                    if (!line[3].equals("")) rs.setServiceName(line[3]);
                    if (!line[4].equals("")) rs.setAmount(parseFloat(line[4]));
                    if (!line[5].equals("")) rs.setCost(parseFloat(line[5]));

                    rowServices.add(rs);

                }
                else {
                    isServiceBlock = false;
                }
            }
        }
        return rowServices;
    }

    public void printRowServices(){
        for (RowService rowService : rowServices) {
            System.out.println(rowService);
        }
        for (Long aLong : codeSet) {
            System.out.println(aLong);
        }
    }

    private float parseFloat(String number){
        String[] num = number.split(",");
        if (num.length != 1)
            return Float.parseFloat(num[0] + "." + num[1]);
        return Float.parseFloat(num[0]);
    }
}
