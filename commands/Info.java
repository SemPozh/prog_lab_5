package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import models.Organization;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;

public class Info extends Command{
    public Info(String name, int argumentsCount, boolean isInlineArgument, int notInlineArgumentsCount) {
        super(name, argumentsCount, isInlineArgument, notInlineArgumentsCount);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments, String notInlineArguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {

    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException {
        if (checkArgumentsCount(arguments)){

            Collection<Organization> collection = collectionHandler.getCollection();

            System.out.println("Коллекция: " + collectionHandler.getCollectionType());
            System.out.println("Дата инициализаци: " + collectionHandler.getInitializationDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
            System.out.println("Количество элементов: " + collection.size());
            System.out.println("Тип элементов коллекции: Organization");
        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }

}
