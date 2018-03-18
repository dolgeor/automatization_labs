package ex2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XLSXValuteCursCreator {
    private String url;

    public XLSXValuteCursCreator(String url) {
        this.url = url;
    }

    public boolean create() {
        ValuteParser valuteParser = new ValuteParser(url);
        String fileName = String.format("%s.xlsx", valuteParser.generateFileNAme());
        List<Valute> valutes = valuteParser.getValuteList();
        XSSFWorkbook workbook = new XSSFWorkbook();
        fillWorkbook(workbook, valutes);
        return writeToFile(workbook, fileName);
    }


    private boolean writeToFile(XSSFWorkbook workbook, String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void fillWorkbook(XSSFWorkbook workbook, List<Valute> valutes) {
        for (Valute valute : valutes) {
            XSSFSheet sheet = workbook.createSheet(valute.getID().toString());
            Object[][] valuteProperties = {
                    {"NumCode", valute.getNumCode()},
                    {"CharCode", valute.getCharCode()},
                    {"Nominal", valute.getNominal()},
                    {"Name", valute.getName()},
                    {"Value", valute.getValue()}
            };
            int rowNum = 0;
            for (Object[] property : valuteProperties) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : property) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    } else if (field instanceof Double) {
                        cell.setCellValue((Double) field);
                    }
                }
            }
        }
    }
}
