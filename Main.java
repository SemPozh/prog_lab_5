import exceptions.InvalidFileException;
import handlers.CollectionHandler;
import handlers.CommandHandler;
import handlers.FileHandler;
import models.Organization;
import validators.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String[] arg = {"DATA.csv"};
        FileHandler fileInputHandler = new FileHandler(arg);
        CollectionHandler collectionHandler = new CollectionHandler(new Stack<Organization>());
        collectionHandler.addElements(fileInputHandler.readFile());


        System.out.println("Welcome to the LABA 5 program. Type command down below. To see all commands type help");
        CommandHandler commandHandler = new CommandHandler(collectionHandler);
        commandHandler.start();
    }
}
