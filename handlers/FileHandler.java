package handlers;
import exceptions.InvalidFileException;
import models.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class FileHandler {
    private String fileName;
    private Stack<Organization> fileContent;

    public FileHandler(String[] args){
        try{
            this.fileName = args[0];
            fileContent = new Stack<>();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("If the file name is not mentioned, the program cannot be downloaded!");
            System.exit(0);
        }
    }

    public Stack<Organization> readFile() {
        File file = new File(this.fileName);
        try {
            Scanner scanner = new Scanner(file);
            ObjectsHandler objectsHandler = new ObjectsHandler();
            while (scanner.hasNextLine()){
                objectsHandler.setDataRow(CSVParser.parseCSVString(scanner.nextLine()));
                Organization organization = objectsHandler.createObjectFromFileRow();
                fileContent.add(organization);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Check your data and try again");
            System.exit(0);
        } catch (InvalidFileException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return fileContent;
    }


}
