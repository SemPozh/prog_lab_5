package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Address;
import models.Coordinates;
import models.Organization;
import models.OrganizationType;

import java.util.Locale;
import java.util.Scanner;

public class Add extends Command {
    public Add(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
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
                    String yText = scanner.nextLine();
                    if (!yText.equals("")) {
                        double y = Double.parseDouble(yText);
                        coordinates.setY(y);
                    }
                    correctInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Y-coordinate must be a number");
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