package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;

public class Clear extends Command{
    public Clear(String name, int argumentsCount, boolean isInlineArgument, int notInlineArgumentsCount) {
        super(name, argumentsCount, isInlineArgument, notInlineArgumentsCount);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments, String notInlineArguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {

    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if(checkArgumentsCount(arguments)){
            collectionHandler.removeAll();
            System.out.println("All organizations successfully removed");
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
