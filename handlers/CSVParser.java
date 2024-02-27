package handlers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CSVParser {
    public static String[] parseCSVString(String csvString){
        String[] arr = csvString.split(",");
        for (int i=0; i<arr.length; i++){
            arr[i] = arr[i].trim();
        }
        return arr;
    }
}
