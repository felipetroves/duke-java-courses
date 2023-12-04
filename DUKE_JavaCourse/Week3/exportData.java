
/**
 * Beschreiben Sie hier die Klasse exportData.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import org.apache.commons.csv.*;
import edu.duke.*;

public class exportData {
    public void tester () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
        parser = fr.getCSVParser();
        listExportersTwoProducts (parser, "gold", "diamonds");
        parser = fr.getCSVParser();
        numberOfExporters (parser, "sugar");
        parser = fr.getCSVParser();
        bigExporters (parser, "$999,999,999,999");
    }

    public String countryInfo (CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryOfInterest = record.get("Country");
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");

            if (country.contains(countryOfInterest)) {
                System.out.println(country + ": " + exports + ": " + value);
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts (CSVParser parser, String exportIterm1, String exportIterm2) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String exports = record.get("Exports");

            if (exports.contains(exportIterm1) && exports.contains(exportIterm2)) {
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters (CSVParser parser, String exportItem) {
        int sum = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");

            if (exports.contains(exportItem)) {
                sum++;
            }
        }
        System.out.println(sum);
        return sum;
    }

    public void bigExporters (CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");

            int lengthValue = value.length();
            int lengthAmount = amount.length();

            if (lengthValue > lengthAmount) {
                System.out.println(country + " " + value);
            }
        }
    }
}
