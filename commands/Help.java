package commands;

import exceptions.InvalidCommandArgumentsException;

public class Help extends Command{


    public Help(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
    }

    @Override
    public void execute(String[] arguments) throws InvalidCommandArgumentsException {
        if (checkArgumentsCount(arguments)){
            System.out.println("COMMAND HELP TEXT!!!!");
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
