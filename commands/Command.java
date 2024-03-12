package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidFileException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;

public abstract class Command {
    private final int argumentsCount;
    private final boolean isInlineArgument;
    private final String name;

    public int getNotInlineArgumentsCount() {
        return notInlineArgumentsCount;
    }

    private final int notInlineArgumentsCount;

    public boolean isExecutedByScript() {
        return executedByScript;
    }

    public void setExecutedByScript(boolean executedByScript) {
        this.executedByScript = executedByScript;
    }

    private boolean executedByScript;


    public Command(String name, int argumentsCount, boolean isInlineArgument, int notInlineArgumentsCount){
        this.name = name;
        this.argumentsCount = argumentsCount;
        this.isInlineArgument = isInlineArgument;
        this.notInlineArgumentsCount = notInlineArgumentsCount;
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
    public abstract void execute(CollectionHandler collectionHandler, String[] arguments, String notInlineArguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException, InvalidFileException;
}
