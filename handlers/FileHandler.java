package handlers;
import exceptions.InvalidFileException;
import exceptions.InvalidObjectFieldException;
import models.Organization;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class FileHandler {
    private String fileName;
    private Collection<Organization> fileContent;

    public FileHandler(String[] args){
        try{
            this.fileName = args[0];
            fileContent = new Stack<>();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("If the file name is not mentioned, the program cannot be downloaded!");
            System.exit(0);
        }
    }

    public Collection<Organization> readFile() {
        File file = new File(this.fileName);
        try {
            Scanner scanner = new Scanner(file);
            ObjectsHandler objectsHandler = new ObjectsHandler();
            while (scanner.hasNextLine()){
                objectsHandler.setDataRow(CSVParser.parseCSVString(scanner.nextLine()));
                Organization organization = objectsHandler.createObjectFromFileRow();
                fileContent.add(organization);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Check your data and try again");
            System.exit(0);
        } catch (InvalidFileException | InvalidObjectFieldException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return fileContent;
    }

    public void writeDataInFile(Collection<Organization> collection){
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            out.close();
            out = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(out);
            String fileStr = "";
            for (Organization element : collection){
                fileStr += element.getId().toString();
                fileStr += ",";
                fileStr += element.getName();
                fileStr += ",";
                fileStr += element.getCoordinates().getX();
                fileStr += ",";
                if (element.getCoordinates().getY() != Double.MAX_VALUE){
                    fileStr += element.getCoordinates().getY();
                }
                fileStr += ",";
                fileStr += element.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                fileStr += ",";
                if (element.getAnnualTurnover() != null){
                    fileStr += element.getAnnualTurnover();
                }
                fileStr += ",";
                fileStr += element.getEmployeesCount();
                fileStr += ",";
                fileStr += element.getType().name();
                fileStr += ",";
                if (element.getOfficialAddress() != null){
                    fileStr += element.getOfficialAddress().getZipCode();
                }
                fileStr += "\n";
            }
            byte[] buffer = fileStr.getBytes();
            bos.write(buffer);
            bos.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
    }
}
