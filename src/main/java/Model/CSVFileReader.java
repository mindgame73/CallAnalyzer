package Model;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVFileReader {
    private final CSVReader reader;
    private boolean isServiceBlock;
    private ArrayList<RowService> rowServices;
    public CSVFileReader() throws IOException {
        reader = new CSVReader(new FileReader("src/main/resources/detail_june.csv"),';');
    }

    public ArrayList<RowService> read() throws IOException{
        String[] line;
        rowServices = new ArrayList<>();
        while((line=reader.readNext())!=null){
            String category = line[0].toLowerCase();
            if (category.contains("постоянные услуги")||category.contains("разовые услуги")){
                isServiceBlock = true;
                continue;
            }
            if (isServiceBlock){
                while(!line[2].equals("")){
                    RowService rs = new RowService();
                    if (!line[0].equals("")) rs.setPhone(Long.parseLong(line[0]));
                    if (!line[2].equals("")) rs.setCode(Long.parseLong(line[2]));
                    if (!line[3].equals("")) rs.setServiceName(line[3]);
                    if (!line[4].equals("")) rs.setAmount(parseFloat(line[4]));
                    if (!line[5].equals("")) rs.setAmount(parseFloat(line[5]));

                    rowServices.add(rs);
                    continue;
                }
                isServiceBlock = false;
            }
        }
        return rowServices;
    }

    public void printRowServices(){
        for (RowService rowService : rowServices) {
            System.out.println(rowService);
        }
    }

    private float parseFloat(String number){
        String[] num = number.split(",");
        if (num.length != 1)
            return Float.parseFloat(num[0] + "." + num[1]);
        return Float.parseFloat(num[0]);
    }
}
