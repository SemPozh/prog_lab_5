package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Address;
import models.Coordinates;
import models.Organization;
import models.OrganizationType;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Update extends Command{
    public Update(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException, NoSuchElementException {
        if (checkArgumentsCount(arguments)){
            try {
                Organization element = collectionHandler.getElementById(Integer.valueOf(arguments[0]));
                Scanner scanner = new Scanner(System.in);

                System.out.print("\t\tInput organization name: ");
                String name = scanner.nextLine();
                element.setName(name);

                boolean correctInput = false;
                while (!correctInput) {
                    System.out.print("\t\tInput organization X-coordinate: ");
                    try {
                        Integer x = Integer.parseInt(scanner.nextLine());
                        element.setCoordinates(new Coordinates(x));
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
                            element.getCoordinates().setY(y);
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
                            element.setAnnualTurnover(Integer.parseInt(annualTurnoverText));
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
                        element.setEmployeesCount(Integer.valueOf(scanner.nextLine()));
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
                        element.setType(OrganizationType.valueOf(typeText));
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
                        element.setOfficialAddress(new Address(zipCodeText));
                    }
                    correctInput = true;
                }

            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentsException("ID must be a number");
            }
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
