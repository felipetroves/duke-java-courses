/**
 * Beschreiben Sie hier die Klasse Part1.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.lang.String;
import edu.duke.*;
import java.io.*;

public class Part1 {
    public String findSimpleGene (String dna) {
        // start codon is "ATG"
        // stop codon is "TAA"
        String result = "";
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "NULL";
        }
        
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) {
            return "NULL";
        }
        
        result = dna.substring(startIndex, stopIndex+3);
        if (result.length() % 3 == 0) {
            return result; 
        }
        
        return "NULL";
    }
    
    public void testSimpleGene () {
        // Has both ATG and TAA but the substring between them is not a multiple of 3
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        // NO ATG
        dna = "ACGTAATATAGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        //No TAA
        dna = "AATGCGTATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        //Substring is a multiple of 3 and has both ATG and TAA
        dna = "AATGCGATAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        //Has neither ATG and TAA
        dna = "AATTAGAACGATAGATATTGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
    }
}