package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Organization;

public class AVGofAnnualTurnover extends Command{
    public AVGofAnnualTurnover(String name, int argumentsCount, boolean isInlineArgument) {
        super(name, argumentsCount, isInlineArgument);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)){
            int sumAT = 0;

            for (Organization org : collectionHandler.getCollection()){
                sumAT += org.getAnnualTurnover();
            }

            if (collectionHandler.getCollectionSize() != 0){
                System.out.println(sumAT/collectionHandler.getCollectionSize());
            } else {
                System.out.println("Collection is empty");
            }
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }
}
