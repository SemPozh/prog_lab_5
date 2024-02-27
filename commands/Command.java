package commands;

import exceptions.InvalidCommandArgumentsException;

public abstract class Command {
    private int argumentsCount;
    private boolean isInlineArgument;
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
        return arguments.length == getArgumentsCount();
    }


    public abstract void execute(String[] arguments) throws InvalidCommandArgumentsException;
}
