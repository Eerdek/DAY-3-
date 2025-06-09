package kass_java.helper;
import java.io.*;

public class ImageHelper {
    
     public static byte[] loadImageBytes(String imagePath) throws IOException {
        File file = new File(imagePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }
}
