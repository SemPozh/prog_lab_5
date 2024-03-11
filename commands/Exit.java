package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;

public class Exit extends Command{
    public Exit(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)){
            System.out.println("Good Bye!");
            System.exit(0);
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
