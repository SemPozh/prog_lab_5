package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class PrintAnnualTurnovers extends Command{
    public PrintAnnualTurnovers(String name, int argumentsCount, boolean isInlineArgument, int notInlineArgumentsCount) {
        super(name, argumentsCount, isInlineArgument, notInlineArgumentsCount);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments, String notInlineArguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {

    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)){
            ArrayList<Integer> sortedAT = new ArrayList<>();
            for (Organization org: collectionHandler.getCollection()){
                sortedAT.add(org.getAnnualTurnover());
            }

            sortedAT.sort(Comparator.comparingInt(x -> x));
            for (Integer el: sortedAT){
                System.out.println(el);
            }
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }


    }
}
