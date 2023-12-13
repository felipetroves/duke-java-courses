
/**
 * Beschreiben Sie hier die Klasse BabyBirths.
 * 
 * @author Felipe Reis
 * @version 05.12.2023
 */

import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.File;
import java.lang.*;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0));
            }
        }
    }

    public void testGetTotalBirthsRankedHigher () {
        System.out.println(getTotalBirthsRankedHigher (2012, "Noah", "M"));
        System.out.println(getTotalBirthsRankedHigher (2012, "Ava", "F"));
    }

    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        FileResource fr = new FileResource();
        int totalBoys = 0;
        int totalGirls = 0;
        int rankSoFar = 0;
        //int rank = getRank(year, name, gender);;

        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            //rankSoFar = getRank(year, record.get(0), gender);
            if (record.get(1).equals("M")) {
                totalBoys += numBorn;
                if (record.get(1).equals(gender) && record.get(0).equals(name)) {          
                    totalBoys -= Integer.parseInt(record.get(2));
                    return totalBoys;
                }
            } else {
                totalGirls += numBorn;
                if (record.get(1).equals(gender) && record.get(0).equals(name)) {              
                    totalGirls -= Integer.parseInt(record.get(2));
                    return totalGirls;
                }
            }
        }
        return -1;
    }

    public void testGetAverageRank() {
        System.out.printf("Average rank: %.2f %n", getAverageRank("Felipe", "M"));
        System.out.printf("Average rank: %.2f %n", getAverageRank("Dacian", "M"));
        System.out.printf("Average rank: %.2f %n", getAverageRank("Stella", "F"));
        System.out.printf("Average rank: %.2f %n", getAverageRank("Selma", "F"));
        System.out.printf("Average rank: %n", getAverageRank("Cuszta", "F"));
    }

    public double getAverageRank(String name, String gender) {
        // This class provides a method for choosing one or more files on your computer. You can only create a DirectoryResource with no parameters.
        DirectoryResource dr = new DirectoryResource();
        double rankSoFarM = 0;
        double rankSoFarF = 0;
        double rankM = 1;
        double rankF = 1;
        double counterM = 0;
        double counterF = 0;
        double year = 0;
        String nameFound = "";
        // returns an Iterable that provides access to each of the files selected by the user one at a time
        for (File f : dr.selectedFiles()) { 
            // uses the given String to find a file on your computer or within your BlueJ project
            FileResource fr = new FileResource(f); 
            rankM = 1;
            rankF = 1;
            for (CSVRecord record : fr.getCSVParser(false)) { // iterate through CSV file one row at a time
                if (record.get(1).equals("M")) { // if column 2 in csv file containt "M"
                    if (record.get(0).equals(name) && gender.equals("M")) { // if column 1 in csv file contains the given name and the given gender is "M"
                        nameFound = record.get(0);
                        rankSoFarM += rankM;
                        counterM++;
                        break;
                    }
                    rankM++;
                } else {
                    if (record.get(0).equals(name) && gender.equals("F")) {
                        nameFound = record.get(0);
                        rankSoFarF += rankF;
                        counterF++;
                        break;
                    }
                    rankF++;
                }
            }
        }   
        if (nameFound.equals(name) && gender.equals("M")) {
            return rankSoFarM / counterM;
        } else if (nameFound.equals(name) && gender.equals("F")) {
            return rankSoFarF / counterF;
        } else {
            return -1;
        }
    }

    public void testYearOfHighestRank() {
        System.out.println("Year of highest rank: " + yearOfHighestRank("Bruna", "F"));
        System.out.println("Year of highest rank: " + yearOfHighestRank("Stella", "F"));
        System.out.println("Year of highest rank: " + yearOfHighestRank("Selma", "F"));
        System.out.println("Year of highest rank: " + yearOfHighestRank("Felipe", "M"));
        System.out.println("Year of highest rank: " + yearOfHighestRank("Dacian", "M"));
        System.out.println("Year of highest rank: " + yearOfHighestRank("Whatever", "M"));
    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource(); // This class provides a method for choosing one or more files
        // on your computer. You can only create a DirectoryResource
        // with no parameters
        int rankSoFarM = 10000;
        int rankSoFarF = 10000;
        int rankM = 1;
        int rankF = 1;
        String fileName = "";
        int year = 0;
        // returns an Iterable that provides access to each of the files selected by the user one at a time
        for (File f : dr.selectedFiles()) { 
            // uses the given String to find a file on your computer or within your BlueJ project
            FileResource fr = new FileResource(f); 
            rankM = 1;
            rankF = 1;
            for (CSVRecord record : fr.getCSVParser(false)) { // iterate through CSV file one row at a time

                if (record.get(1).equals("M")) { // if column 2 in csv file containt "M"
                    // if column 1 in csv file contains the given name and the given gender is "M"
                    if (record.get(0).equals(name) && gender.equals("M")) { 
                        break;
                    }
                    rankM++; // if the conditios are not met, then increase male rank by 1
                } else {
                    if (record.get(0).equals(name) && gender.equals("F")) {
                        break;
                    }
                    rankF++;
                }
            }
            if (rankM < rankSoFarM) {
                rankSoFarM = rankM;
                fileName = f.getName();
            }
            if (rankF < rankSoFarF) {
                rankSoFarF = rankF;
                fileName = f.getName();
            }
        }
        year = Integer.parseInt(fileName.substring(3, 7));
        if (getName(year, rankSoFarM, "M").equals(name)) {
            System.out.println("Highest rank: " + rankSoFarM);
            return year;
        } else if (getName(year, rankSoFarF, "F").equals(name)) {
            System.out.println("Highest rank: " + rankSoFarF);
            return year;
        } else {
            return -1;
        }
    }

    public void testWhatIsNameInYear() {
        whatIsNameInYear("Sophia", 2012, 2014, "F");
        whatIsNameInYear("Liam", 2014, 2012, "M");
        whatIsNameInYear("Isabella", 2012, 2014, "F");
        whatIsNameInYear("William", 2012, 2014, "M");
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        String yearString = Integer.toString(year);
        String newYearString = Integer.toString(newYear);

        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear);
    }

    public void testGetName() {
        System.out.println(getName(2012, 3, "F"));
        System.out.println(getName(2014, 2, "M"));
        System.out.println(getName(2014, 4, "F"));
        System.out.println(getName(2012, 6, "M"));
    }

    public String getName(int year, int rank, String gender) {
        String yearString = Integer.toString(year);
        FileResource fr = new FileResource("us_babynames_by_year/yob" + yearString + ".csv"); // open file in
        // directory based on
        // given year
        int rankM = 1;
        int rankF = 1;
        int numBornStorageM = 0;
        int numBornStorageF = 0;
        int numBornMales = 0;
        int numBornFemales = 0;

        for (CSVRecord record : fr.getCSVParser(false)) { // iterate through CSV file one row at a time
            if (numBornStorageM == 0 && record.get(1).equals("M")) {
                numBornStorageM = Integer.parseInt(record.get(2)); // numBornStorage gets the value of the third column
                // (number of newborns)
            }
            if (numBornStorageF == 0 && record.get(1).equals("F")) {
                numBornStorageF = Integer.parseInt(record.get(2)); // numBornStorage gets the value of the third column
                // (number of newborns)
            }
            // Check if current rank matches given rank for respective genders and return
            // the name
            if (record.get(1).equals("M")) {
                numBornMales = Integer.parseInt(record.get(2)); // numBornMales gets the value of the third column
                // (number of newborns) on every iteration
                if (rank == rankM && gender.equals("M")) { // if the given rank matches the current rank and given
                    // gender has value "M"
                    return record.get(0); // return the name of the current row in the 1st column (name)
                } else if (numBornMales <= numBornStorageM) { // if the current number of newborns is lower than the
                    // last iteration saved in storage
                    rankM++; // increase rank by 1
                } else {
                    numBornStorageM = numBornMales;
                }
                // repeat the process here also for the female variant
            } else {
                numBornFemales = Integer.parseInt(record.get(2));
                if (rank == rankF && gender.equals("F")) {
                    return record.get(0);
                } else if (numBornFemales <= numBornStorageF) {
                    rankF++;
                } else {
                    numBornStorageF = numBornFemales;
                }
            }
        }
        return "NO NAME";
    }

    public void testGetRank() {
        System.out.println(getRank(2012, "Ethan", "M"));
        System.out.println(getRank(2013, "Ethan", "M"));
        System.out.println(getRank(2012, "Ethan", "F"));
        System.out.println(getRank(2014, "Ava", "F"));

    }

    public int getRank(int year, String name, String gender) {
        String yearString = Integer.toString(year);
        FileResource fr = new FileResource("us_babynames_test/yob" + yearString + "short.csv"); // open file in
        // directory based on
        // given year
        int rankM = 1;
        int rankF = 1;

        for (CSVRecord record : fr.getCSVParser(false)) { // iterate through CSV file one row at a time
            if (record.get(1).equals("M")) { // if column 2 in csv file containt "M"
                if (record.get(0).equals(name) && gender.equals("M")) { // if column 1 in csv file contains the given
                    // name and the given gender is "M"
                    return rankM; // return the current value in rankM
                }
                rankM++; // if the conditios are not met, then increase male rank by 1
            } else {
                if (record.get(0).equals(name) && gender.equals("F")) {
                    return rankF;
                }
                rankF++;
            }
        }
        return -1;
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);

    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if (record.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
}
