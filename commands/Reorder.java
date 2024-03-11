package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;

public class Reorder extends Command{
    public Reorder(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)){
            collectionHandler.reverseCollection();
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
