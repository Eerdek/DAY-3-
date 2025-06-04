package kass_java.helper;

import java.io.*;
import java.util.Base64;

public class ImageHelper {

    public static byte[] loadImageBytes(String imagePath) throws IOException {
        File file = new File(imagePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    public static byte[] loadImageBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    public static boolean fileExists(String imagePath) {
        File file = new File(imagePath);
        return file.exists() && file.isFile();
    }

    public static String encodeToBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
