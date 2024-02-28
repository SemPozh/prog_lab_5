package handlers;

import commands.Command;
import commands.Help;
import commands.Info;
import commands.Show;
import exceptions.InvalidCommandArgumentsException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CommandHandler {
    HashMap<String, Command> commandsMap;
    private boolean isWorking;

    private CollectionHandler collectionHandler;

    public CommandHandler(CollectionHandler collectionHandler){
        setWorking(true);
        setCollectionHandler(collectionHandler);
        commandsMap = new HashMap<>();
        commandsMap.put("help", new Help("help", 0, true));
        commandsMap.put("info", new Info("info", 0, true));
        commandsMap.put("show", new Show("show", 0, true));
    }

    public CollectionHandler getCollectionHandler() {
        return collectionHandler;
    }

    public void setCollectionHandler(CollectionHandler collectionHandler){
        this.collectionHandler = collectionHandler;
    }

    public void setWorking(boolean isWorking){
        this.isWorking = isWorking;
    }

    public boolean getWorking(){
        return this.isWorking;
    }



    private String getCommandName(String commandText){
        return commandText.split(" ")[0];
    }

    private String[] getCommandArguments(String commandText){
        String[] commandArr = commandText.split(" ");
        return Arrays.copyOfRange(commandArr, 1, commandArr.length);
    }

    private Command getCommandObject(String commandName){
        return commandsMap.get(commandName);
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        while (isWorking){
            System.out.print("\t> ");
            String commandText = scanner.nextLine();
            Command commandObject = getCommandObject(getCommandName(commandText));
            try {
                commandObject.execute(collectionHandler, getCommandArguments(commandText));
            } catch (InvalidCommandArgumentsException e){
                System.out.println(e.getMessage());
            } catch (NullPointerException e){
                System.out.println("There is no such command. The list of commands can be viewed by calling the help command");
            }
        }
        scanner.close();
    }


}
