package Model;

import Controller.UserInterfaceController;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

public class XLSReader {

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;

    public XLSReader(String path) throws IOException {
        FileInputStream fis = new FileInputStream(new File("src/main/resources/NEC.xls"));
        workbook = new HSSFWorkbook(fis);
        sheet = workbook.getSheet("Лист1");

    }
    public int findRowByValue(Long value) {
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) continue;
            Cell cell = row.getCell(2);
            if (cell != null) {
                CellType cellType = cell.getCellTypeEnum();
                if (cellType == CellType.NUMERIC){
                    if (value == new BigDecimal(cell.getNumericCellValue()).longValue())
                        return row.getRowNum();
                }
            }
        }
        return -1;
    }

    public void fillEmployee(){
        for (RowService rowService : UserInterfaceController.rowServices) {
            if (rowService.getEmployee() != null && rowService.getEmployee().getPhone() != 0){
                CellType cellType;
                int rowNum = findRowByValue(rowService.getEmployee().getPhone());
                if (rowNum != -1){
                    Row row = sheet.getRow(rowNum);
                    Cell division = row.getCell(6);
                    Cell employeeName = row.getCell(12);
                    if (division != null) {
                        cellType = division.getCellTypeEnum();
                        switch (cellType){
                            case BLANK:
                                break;
                            case STRING:
                                rowService.getEmployee().setDivision(division.getStringCellValue().toUpperCase().trim());
                                UserInterfaceController.divisionSet.add(division.getStringCellValue().toUpperCase().trim());
                                break;
                        }
                    }
                    if (employeeName != null) {
                        cellType = employeeName.getCellTypeEnum();
                        switch (cellType){
                            case BLANK:
                                break;
                            case STRING:
                                rowService.getEmployee().setEmployeeName(employeeName.getStringCellValue());
                                break;
                        }

                    }
                }
            }
        }
    }
}
