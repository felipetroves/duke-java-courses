
/**
 * Problem Solving with Strings
 * 
 * @author Felipe Reis
 * @version 1.0
 */
import java.lang.String;
import edu.duke.*;
import java.io.*;

public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb) {
        int firstOccurence = stringa.indexOf(stringb);
        int lengthOfString = stringb.length();
        int secondOccurence = stringa.indexOf(stringb, firstOccurence + lengthOfString);

        if (firstOccurence == -1 || secondOccurence == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String lastPart (String stringa, String stringb) {
        int firstOccurence = stringa.indexOf(stringb);
        int lengthOfString = stringb.length();
        
        if (firstOccurence == -1) {
            return stringa;
        } else {
            return stringa.substring(firstOccurence + lengthOfString);
        }
    }

    public void testing () {
        String a;
        String b;

        System.out.println("Testing twoOccurences");
        System.out.println("=====================");

        a = "A story by Abby Long";
        b = "by";
        System.out.println("\""+ b + "\"" + " appears at least twice in " + "\"" + a + "\"" + " = " + twoOccurrences(a, b));

        a = "Bleak Sanctuary";
        b = "eak";
        System.out.println("\""+ b + "\"" + " appears at least twice in " + "\"" + a + "\"" + " = " + twoOccurrences(a, b));

        a = "Banana";
        b = "an";
        System.out.println("\""+ b + "\"" + " appears at least twice in " + "\"" + a + "\"" + " = " + twoOccurrences(a, b));

        a = "Saturn and Venus";
        b = "ur";
        System.out.println("\""+ b + "\"" + " appears at least twice in " + "\"" + a + "\"" + " = " + twoOccurrences(a, b) + "\"");
        
        System.out.println("\nTesting lastPart");
        System.out.println("=====================");
        
        a = "A story by Abby Long";
        b = "by";
        System.out.println("The part of the string after " + "\"" + b + "\" in " + "\"" + a + "\" is" + "\"" + lastPart(a, b) + "\"");

        a = "Bleak Sanctuary";
        b = "forest";
        System.out.println("The part of the string after " + "\"" + b + "\" in " + "\"" + a + "\" is " + "\"" + lastPart(a, b) + "\"");

        a = "Banana";
        b = "Apple";
        System.out.println("The part of the string after " + "\"" + b + "\" in " + "\"" + a + "\" is " + "\"" + lastPart(a, b) + "\"");

        a = "Saturn and Venus";
        b = "ur";
        System.out.println("The part of the string after " + "\"" + b + "\" in " + "\"" + a + "\" is " + "\"" + lastPart(a, b) + "\"");
    }
}
