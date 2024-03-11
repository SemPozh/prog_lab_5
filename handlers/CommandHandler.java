package handlers;

import commands.*;
import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
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
        commandsMap.put("add", new Add("add", 0, false));
        commandsMap.put("update", new Update("update", 1, false));
        commandsMap.put("remove_by_id", new Remove("remove_by_id", 1, true));
        commandsMap.put("clear", new Clear("clear", 0, true));
        commandsMap.put("exit", new Exit("exit", 0, true));
        commandsMap.put("insert_at", new InsertAt("insert_at", 1, false));
        commandsMap.put("add_if_max", new AddIfMax("add_if_max", 0, false));
        commandsMap.put("reorder", new Reorder("reorder", 0, true));
        commandsMap.put("average_of_annual_turnover", new AVGofAnnualTurnover("average_of_annual_turnover", 0, true));
        commandsMap.put("min_by_employees_count", new MinByEmployeesCount("min_by_employees_count", 0, true));
        commandsMap.put("print_field_descending_annual_turnover", new PrintAnnualTurnovers("print_field_descending_annual_turnover", 0, true));
        commandsMap.put("save", new Save("save", 0, true));
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
            } catch (InvalidCommandArgumentsException | InvalidObjectFieldException | NoSuchElementException e){
                System.out.println(e.getMessage());
            } catch (NullPointerException e){
                System.out.println("There is no such command. The list of commands can be viewed by calling the help command");
            }
        }
        scanner.close();
    }
}
