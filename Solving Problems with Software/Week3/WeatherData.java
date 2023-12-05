
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
        System.out.println("Coldest Temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEDT"));
    }

    public void testFileWithColdestTemperature () {
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

    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemp);
    }

    // CSVParser: This class provides you the ability to iterate over each line of data within a CSV formatted file as a record of the individual data values. 
    // Most likely you will not call any methods directly on a CSVParser object, but use it as an Iterable within your loop.
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestSoFar = null; // creates an empty CSVRecord object.
        for (CSVRecord currentRow : parser) { // A"for-each" loop is used exclusively to loop through elements in an array.
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

                if (currentTemp > 0) {
                    if (currentTemp < coldestTemp) { // if temperature in currentTemp in CSV file is colder than the last coldestTemp...
                        coldestSoFar = currentRow; // then the coldestSoFar CSVRecord object gets the currentRow.
                        fileName = f.getName();
                    }
                }   
            }
        }
        return fileName;
    }

    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestSoFar = null;

        for (CSVRecord currentRow : parser) { // A"for-each" loop is used exclusively to loop through elements in an array.
            if (lowestSoFar == null) { 
                lowestSoFar = currentRow;
            }

            else {
                if (currentRow.get("Humidity") != "N/A" && lowestSoFar.get("Humidity") != "N/A") {
                    double currentHumidity = Double.parseDouble(currentRow.get("Humidity")); //save String and convert it to a double
                    double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));

                    if (currentHumidity < lowestHumidity) { 
                        lowestSoFar = currentRow; 
                    }
                }
            }

        }
        return lowestSoFar;
    }

    public double averageTemperatureInFile (CSVParser parser) {
        double averageTemperature = 0.0;
        int counter = 0;

        for (CSVRecord currentRow : parser) { // A"for-each" loop is used exclusively to loop through elements in an array.
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF")); //save String and convert it to a double

            averageTemperature += currentTemp;
            counter++;
        }
        averageTemperature /= counter;
        return averageTemperature;
    }

    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double averageTemperature = 0.0;
        int counter = 0;

        for (CSVRecord currentRow : parser) { // A"for-each" loop is used exclusively to loop through elements in an array.
            if (currentRow.get("Humidity") != "N/A") {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF")); //save String and convert it to a double
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity")); //save String and convert it to a double

                if (currentHumidity >= value) {
                    averageTemperature += currentTemp;
                    counter++;
                } 
            }
        }
        if (averageTemperature > 0) {
            averageTemperature /= counter;
            return averageTemperature;
        } else {
            return 0;
        }  
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (averageTemp == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature when high Humidity is " + averageTemp);
        }
    }

    public CSVRecord fileWithLowestHumidity () {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource(); // This class provides a method for choosing one or more files on your computer. You can only create a DirectoryResource with no parameters

        for (File f : dr.selectedFiles()) { // returns an Iterable that provides access to each of the files selected by the user one at a time
            FileResource fr = new FileResource(f); // uses the given String to find a file on your computer or within your BlueJ project

            // use method to get largest in file
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser()); // This class provides methods for accessing individual data values in a line of data within a CSV formatted file

            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                if (currentRow.get("Humidity") != "N/A" && lowestSoFar.get("Humidity") != "N/A") {
                    double currentHumidity = Double.parseDouble(currentRow.get("Humidity")); //save String and convert it to a double
                    double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));

                    if (currentHumidity < lowestHumidity) { // if temperature in currentTemp in CSV file is colder than the last coldestTemp...
                        lowestSoFar = currentRow; // then the coldestSoFar CSVRecord object gets the currentRow.
                    }
                }

            }
        }
        return lowestSoFar;
    }

    public void testFileWithLowestHumidity () {
        CSVRecord lowestHumidity = fileWithLowestHumidity();
        System.out.println("Day with lowest humidity was " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("TimeEST"));

    }
}
