package handlers;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.lang.Object.*;
public class CSVParser {
    public static String[] parseCSVString(String csvString){
        String[] arr = new String[9];
        String[] data = csvString.split(",");

        for (int i=0; i<8; i++){
            arr[i] = data[i].trim();
        }
        if (data.length == 9){
            arr[8] = data[8].trim();
        } else{
            arr[8] = "";
        }
        return arr;
    }
}
