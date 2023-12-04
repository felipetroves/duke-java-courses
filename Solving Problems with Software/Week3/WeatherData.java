
/**
 * Beschreiben Sie hier die Klasse WeatherData.
 * 
 * @author Felipe Reis 
 * @version 01.12.2023
 */

import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.File;
import java.lang.*;

public class WeatherData {
    public void tester () {
        FileResource fr = new FileResource(); // opens a dialog box prompting you to select a file on your computer
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser()); // This class provides methods for accessing individual data values in a line of data within a CSV formatted file
        System.out.println("Coldest Temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
    }

    public void testFileWithColdestTemperature() {
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestFileName);
        
        String year = coldestFileName.substring(8, 12);
       
        for (int i = 2; i <= 4; i++) {
            String counter = Integer.toString(i);
            if (year.equals("201" + counter)) { // opens a dialog box prompting you to select a file on your computer
                year = "201" + counter;
                break;
            } 
        }

        FileResource fr = new FileResource("nc_weather/" + year + "/" + coldestFileName); // opens a dialog box prompting you to select a file on your computer

        CSVRecord coldestFile = coldestHourInFile(fr.getCSVParser()); // This class provides methods for accessing individual data values in a line of data within a CSV formatted file
        System.out.println("Coldest temperature on that day was " + coldestFile.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");

        CSVParser parser = fr.getCSVParser();
        for (CSVRecord currentTemp : parser) {
            System.out.println(currentTemp.get("DateUTC") + ": " + currentTemp.get("TemperatureF"));
        }
        
    }

    // CSVParser: This class provides you the ability to iterate over each line of data within a CSV formatted file as a record of the individual data values. 
    // Most likely you will not call any methods directly on a CSVParser object, but use it as an Iterable within your loop.
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestSoFar = null; // creates an empty CSVRecord object.
        for (CSVRecord currentRow : parser) { // A"for-each" loop is used exclusively to loop through elements in an array.
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    public String fileWithColdestTemperature () {
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource(); // This class provides a method for choosing one or more files on your computer. You can only create a DirectoryResource with no parameters
        String fileName = "";

        for (File f : dr.selectedFiles()) { // returns an Iterable that provides access to each of the files selected by the user one at a time
            FileResource fr = new FileResource(f); // uses the given String to find a file on your computer or within your BlueJ project

            // use method to get largest in file
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser()); // This class provides methods for accessing individual data values in a line of data within a CSV formatted file

            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF")); //save temperature String and convert it to a double
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));

                if (currentTemp < coldestTemp) { // if temperature in currentTemp in CSV file is colder than the last coldestTemp...
                    coldestSoFar = currentRow; // then the coldestSoFar CSVRecord object gets the currentRow.
                    fileName = f.getName();
                }
            }
        }
        return fileName;
    }

    public CSVRecord getColdestOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
        if (coldestSoFar == null) { 
            coldestSoFar = currentRow;
        }

        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF")); //save temperature String and convert it to a double
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));

            if (currentTemp < coldestTemp) { // if temperature in currentTemp in CSV file is colder than the last coldestTemp...
                coldestSoFar = currentRow; // then the coldestSoFar CSVRecord object gets the currentRow.
            }
        }
        return coldestSoFar;
    }

}
