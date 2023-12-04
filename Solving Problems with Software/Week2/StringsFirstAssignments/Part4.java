
/**
 * Beschreiben Sie hier die Klasse Part4.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.lang.String;
import edu.duke.*;
import java.io.*;

public class Part4 {
    public void findLinks (String url) {
        URLResource urlSource = new URLResource(url);

        for (String item : urlSource.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");

            if (pos != -1) {
                int startIndex = item.lastIndexOf("\"", pos);
                int lastIndex = item.indexOf("\"", pos);
                System.out.println("Youtube link: " + item.substring(startIndex + 1, lastIndex));
            }
        }
    }

    public void test() {
        String url = "https://www.dukelearntoprogram.com/course2/doc/javadoc/edu/duke/URLResource.html";
        findLinks(url);
    }
}
