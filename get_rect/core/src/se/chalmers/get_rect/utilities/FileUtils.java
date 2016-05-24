package se.chalmers.get_rect.utilities;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void createFolder(String filePath) throws IOException {
        File theFile = new File(filePath);
        boolean tmp = theFile.mkdirs();

        if (!tmp){
            throw new IOException("Failed to create save path: " + filePath);
        }
    }

    public static boolean folderExists(String path) {
        return new File(path).isDirectory();
    }

    public static boolean fileExists(String file) {
        return new File(file).isFile();
    }
}
