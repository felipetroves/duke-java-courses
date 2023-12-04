
/**
 * Beschreiben Sie hier die Klasse Part2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.lang.String;
import edu.duke.*;
import java.io.*;

public class Part2 {
    public int howMany (String stringa, String stringb) {
    int currIndex = stringb.indexOf(stringa);
    int sum = 0;
    int length = stringa.length();
        
        while (currIndex != -1) {     
            sum ++;
            currIndex = stringb.indexOf(stringa, currIndex + length);
        }
        return sum;
    }
    
    public void testHowMany () {
        String stringa = "atc";
        String stringb = "atggatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattgccgacatga";
        
        System.out.println(howMany(stringa, stringb));
    }
}
