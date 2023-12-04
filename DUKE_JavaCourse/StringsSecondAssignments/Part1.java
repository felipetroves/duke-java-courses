
/**
 * Beschreiben Sie hier die Klasse Part1.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) { 
        // currIndex returns the index of the last letter of "stopCodon"
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
            return dna.length();
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        
        if (minIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindStopCodon () { 
        String dna = "atggatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattgccgacatga";
        findStopCodon(dna, 0, "TAA");
    }
    
    public void printAllGenes (String dna) {
        
    }
}
