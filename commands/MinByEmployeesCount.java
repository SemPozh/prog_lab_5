package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Organization;

public class MinByEmployeesCount extends Command{
    public MinByEmployeesCount(String name, int argumentsCount, boolean isInlineArgument, int notInlineArgumentsCount) {
        super(name, argumentsCount, isInlineArgument, notInlineArgumentsCount);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments, String notInlineArguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {

    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)){
            int minEC = Integer.MAX_VALUE;
            Organization obj = null;
            for (Organization org : collectionHandler.getCollection()){
                if (org.getEmployeesCount() < minEC){
                    minEC = org.getEmployeesCount();
                    obj = org;
                }
            }

            if (obj != null){
                System.out.println(obj);
            } else {
                System.out.println("Collection is empty");
            }
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
