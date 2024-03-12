package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidFileException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Address;
import models.Coordinates;
import models.Organization;
import models.OrganizationType;

import java.util.Locale;
import java.util.Scanner;

public class Add extends Command {
    public Add(String name, int argumentsCount, boolean isInlineArgument, int notInlineArgumentsCount) {
        super(name, argumentsCount, isInlineArgument, notInlineArgumentsCount);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments, String notInlineArguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException, InvalidFileException {
        if (checkArgumentsCount(arguments)){
            String[] notInlineArgumentsArray = notInlineArguments.split("\n");
            Coordinates coordinates = null;
            String name = notInlineArgumentsArray[0];
            Integer annualTurnover = null;
            Integer employeeCount = null;
            String zipCode = null;
            OrganizationType organizationType = null;
            if (notInlineArgumentsArray.length == 7){
                zipCode = notInlineArgumentsArray[6];
            }
            try {
                Integer x = Integer.parseInt(notInlineArgumentsArray[1]);
                coordinates = new Coordinates(x);
            } catch (NumberFormatException e) {
                throw new InvalidFileException("Invalid script data! X-coordinate must be a number!");
            }

            try {
                double y = Double.parseDouble(notInlineArgumentsArray[2]);
                coordinates.setY(y);
            } catch (NumberFormatException e) {
                throw new InvalidFileException("Invalid script data! Y-coordinate must be a double number!");
            }
            if (!notInlineArgumentsArray[3].isEmpty()){
                try{
                    annualTurnover = Integer.parseInt(notInlineArgumentsArray[3]);
                } catch (NumberFormatException e){
                    throw new InvalidFileException("Invalid script data! Annual turnover must be a double number!");
                }
            }

            try{
                employeeCount = Integer.parseInt(notInlineArgumentsArray[4]);
            } catch (NumberFormatException e){
                throw new InvalidFileException("Invalid script data! Employees count must be a double number!");
            }

            try {
                organizationType = OrganizationType.valueOf(notInlineArgumentsArray[5].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid script data! There are no such organization type");
            }

            Organization organization = new Organization(name, coordinates, employeeCount, organizationType);
            if (zipCode!=null){

                organization.setOfficialAddress(new Address(zipCode));
            }
            if (annualTurnover!=null){
                organization.setAnnualTurnover(annualTurnover);
            }
            collectionHandler.addElement(organization);
        } else {
            throw new InvalidCommandArgumentsException("Arguments for this command inputs below command name in next lines");
        }
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\t\tInput organization name: ");
            String name = scanner.nextLine();
            OrganizationType organizationType = null;
            Integer employeeCount = null;
            Coordinates coordinates = null;
            Integer annualTurnover = null;
            Address address = null;
            boolean correctInput = false;
            while (!correctInput) {
                System.out.print("\t\tInput organization X-coordinate: ");
                try {
                    Integer x = Integer.parseInt(scanner.nextLine());
                    coordinates = new Coordinates(x);
                    correctInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("X-coordinate must be defined");
                }
            }
            correctInput = false;
            while (!correctInput) {
                System.out.print("\t\tInput organization Y-coordinate: ");
                try {
                    double y = Double.parseDouble(scanner.nextLine());
                    coordinates.setY(y);
                    correctInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Y-coordinate must be defined");
                }
            }
            correctInput = false;
            while (!correctInput) {
                System.out.print("\t\tInput organization annual turnover: ");
                try {
                    String annualTurnoverText = scanner.nextLine();
                    if (!annualTurnoverText.equals("")) {
                        annualTurnover = Integer.parseInt(annualTurnoverText);
                    }
                    correctInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Annual turnover must be a number");
                }
            }
            correctInput = false;


            while (!correctInput) {
                System.out.print("\t\tInput organization employee count: ");
                try {
                    employeeCount = Integer.valueOf(scanner.nextLine());
                    correctInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Employee count must be a number");
                }
            }
            correctInput = false;
            while (!correctInput) {
                System.out.print("\t\tInput organization type (Commercial, Public, Government, Trust, OJSC): ");
                try {
                    scanner = new Scanner(System.in);
                    String typeText = scanner.nextLine().toUpperCase(Locale.ROOT);
                    organizationType = OrganizationType.valueOf(typeText);
                    correctInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("There are no such organization type");
                }
            }

            correctInput = false;
            while (!correctInput) {
                System.out.print("\t\tInput organization zip code: ");
                String zipCodeText = scanner.nextLine();
                if (!zipCodeText.equals("")) {
                    address = new Address(zipCodeText);
                }
                correctInput = true;
            }

            Organization organization = new Organization(name, coordinates, employeeCount, organizationType, address);
            if (annualTurnover!=null){
                organization.setAnnualTurnover(annualTurnover);
            }
            if (address!=null){
                organization.setOfficialAddress(address);
            }

            collectionHandler.addElement(organization);

        } else {
            throw new InvalidCommandArgumentsException("Arguments for this command inputs below command name in next lines");
        }
    }
}