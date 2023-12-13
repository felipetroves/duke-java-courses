
/**
 * Beschreiben Sie hier die Klasse ImageSaver.
 * 
 * @author Felipe Reis 
 * @version 12.12.2023
 */
import edu.duke.*;
import java.io.File;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String originalName = image.getFileName();
            String newName = "copy-" + originalName;
            image.setFileName(newName);
            image.draw();
            image.save();
        }
    }
}
