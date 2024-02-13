package logics;

import java.io.*;

public class FileHandler {
    public static String openFile(String fileName){
        return "";
    }

    public static boolean checkFile(String fileName){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            int data = bufferedReader.read();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
