/**
 * Beschreiben Sie hier die Klasse Part1.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.lang.String;
import edu.duke.*;
import java.io.*;

public class Part2 {
    public String findSimpleGene (String dna, int startIndex, int stopIndex) {
        // start codon is "ATG"
        // stop codon is "TAA"
        if (startIndex == -1) {
            return "NULL";
        }

        if (stopIndex == -1) {
            return "NULL";
        }

        String result = dna.substring(startIndex, stopIndex+3);
        if (result.length() % 3 == 0) {
            for (int i=0; i < dna.length(); i++){
                //if any character is not in lower case, return false
                if(Character.isLowerCase(dna.charAt(i))) {
                    return dna.toLowerCase();
                }
                if(Character.isUpperCase(dna.charAt(i))) {
                    return dna.toUpperCase();
                }
            }
        }

        return "NULL";
    }

    public void testSimpleGene () {
        // Uppercase
        String dna = "AATGCGTTAATATGGT";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna, startIndex, stopIndex);
        System.out.println("Gene is " + gene);

        // Lowercase
        dna = "agctatgctgacataa";
        startIndex = dna.indexOf("atg");
        stopIndex = dna.indexOf("taa", startIndex+3);
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, startIndex, stopIndex);
        System.out.println("Gene is " + gene);

        // No Gene
        dna = "agcattgaatgactgacatgttaa";
        startIndex = dna.indexOf("atg");
        stopIndex = dna.indexOf("taa", startIndex+3);
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, startIndex, stopIndex);
        System.out.println("Gene is " + gene);

    }
}