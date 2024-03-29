package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Organization;

public class Remove extends Command{
    public Remove(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)){
            try {
                Organization element = collectionHandler.getElementById(Integer.valueOf(arguments[0]));
                collectionHandler.removeElement(element);
                System.out.println("Organization " + element.getName() + " was successfully removed");
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentsException("ID must be a number");
            }
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
