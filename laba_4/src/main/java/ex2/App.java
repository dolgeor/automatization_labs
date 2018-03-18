package ex2;

public class App {
    public static void main(String[] args) {
        final String url = "https://bnm.md/en/official_exchange_rates?get_xml=1&date=15.02.2018";
        XLSXValuteCursCreator xlsxValuteCursCreator = new XLSXValuteCursCreator(url);
        System.out.println("XLSX file is created: " + xlsxValuteCursCreator.create());
    }
}
