package commands;

import exceptions.InvalidCommandArgumentsException;
import handlers.CollectionHandler;
import models.Organization;

import java.util.Collection;

public class Show extends Command{
    public Show(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException {
        if (checkArgumentsCount(arguments)){
            Collection<Organization> collection = collectionHandler.getCollection();
            for (Organization el: collection){
                System.out.println(el.toString());
            }

        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }

}
