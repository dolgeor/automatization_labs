package ex1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class App {
    private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/SalesJan2009.csv";

    public static void main(String[] args) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim());
        ) {
            showAllPurchaseOrders(csvParser.getRecords());
        }
    }

    public static void showAllPurchaseOrders(Iterable<CSVRecord> csvRecords) {
        for (CSVRecord csvRecord : csvRecords) {
            System.out.format("%33s%n", "Purchase Order No - " + csvRecord.getRecordNumber());
            System.out.println(getPurchaseOrderFromCSVRecord(csvRecord));
        }
    }

    public static PurchaseOrder getPurchaseOrderFromCSVRecord(CSVRecord csvRecord) {
        return new PurchaseOrder(
                new Date(csvRecord.get("Transaction_date")),
                csvRecord.get("Product"),
                new BigDecimal(csvRecord.get("Price")),
                csvRecord.get("Payment_Type"),
                csvRecord.get("Name"),
                csvRecord.get("City"),
                csvRecord.get("State"),
                csvRecord.get("Country"),
                new Date(csvRecord.get("Account_Created")),
                new Date(csvRecord.get("Last_Login")),
                new BigDecimal(csvRecord.get("Latitude")),
                new BigDecimal(csvRecord.get("Longitude"))
        );
    }

}
