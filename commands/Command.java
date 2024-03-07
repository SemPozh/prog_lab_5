package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;

public abstract class Command {
    private final int argumentsCount;
    private final boolean isInlineArgument;
    private final String name;


    public Command(String name, int argumentsCount, boolean isInlineArgument){
        this.name = name;
        this.argumentsCount = argumentsCount;
        this.isInlineArgument = isInlineArgument;
    }

    public String getName() {
        return name;
    }

    public int getArgumentsCount() {
        return argumentsCount;
    }

    public boolean isInlineArgument() {
        return isInlineArgument;
    }

    protected boolean checkArgumentsCount(String[] arguments){
        return getArgumentsCount() == arguments.length;
    }


    public abstract void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException;
}
