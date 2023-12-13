
/**
 * Beschreiben Sie hier die Klasse Grayscale.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import edu.duke.*;
import java.io.File;

public class Grayscale {
    public ImageResource makeGray (ImageResource inImage) {
        // Create blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            // look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            // compute inPixel's red + inPixel's blue + inPixels's green
            // divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            // set pixel's red to average
            pixel.setRed(average);
            // set pixel's green to average
            pixel.setGreen(average);
            // set pixel's blue to average
            pixel.setBlue(average);
        }    
        // outImage is your answer
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }

    public void doSave() {  
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGray(image);
            String originalName = image.getFileName();
            String newName = "gray-" + originalName;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}
