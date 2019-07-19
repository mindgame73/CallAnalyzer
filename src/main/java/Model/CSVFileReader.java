package Model;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVFileReader {
    private final CSVReader reader;
    public CSVFileReader() throws IOException {
        reader = new CSVReader(new FileReader("src/main/resources/detail_june.csv"),';');
    }

    public void read() throws IOException{
        String[] line;
        while((line=reader.readNext())!=null){
            if (line != null) System.out.println(Arrays.toString(line));
        }
    }
}
