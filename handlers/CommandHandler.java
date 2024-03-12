package handlers;

import commands.*;
import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidObjectFieldException;

import java.util.*;

public class CommandHandler {
    public static HashMap<String, Command> commandsMap;
    private boolean isWorking;

    private CollectionHandler collectionHandler;

    private static final Stack<String> scriptStack = new Stack<>();

    public static void addScriptIntoStack(String scriptName){
        scriptStack.add(scriptName);
    }

    public static void removeFromStack(String scriptName){
        scriptStack.remove(scriptName);
    }

    public static boolean checkIfScriptInStack(String scriptName){
        return scriptStack.search(scriptName)>-1;
    }

    public CommandHandler(CollectionHandler collectionHandler){
        setWorking(true);
        setCollectionHandler(collectionHandler);
        commandsMap = new HashMap<>();
        commandsMap.put("help", new Help("help", 0, true,  0));
        commandsMap.put("info", new Info("info", 0, true, 0));
        commandsMap.put("show", new Show("show", 0, true, 0));
        commandsMap.put("add", new Add("add", 0, false, 7));
        commandsMap.put("update", new Update("update", 1, false, 7));
        commandsMap.put("remove_by_id", new Remove("remove_by_id", 1, true, 0));
        commandsMap.put("clear", new Clear("clear", 0, true, 0));
        commandsMap.put("exit", new Exit("exit", 0, true, 0));
        commandsMap.put("insert_at", new InsertAt("insert_at", 1, false, 7));
        commandsMap.put("add_if_max", new AddIfMax("add_if_max", 0, false, 7));
        commandsMap.put("reorder", new Reorder("reorder", 0, true, 0));
        commandsMap.put("average_of_annual_turnover", new AVGofAnnualTurnover("average_of_annual_turnover", 0, true, 0));
        commandsMap.put("min_by_employees_count", new MinByEmployeesCount("min_by_employees_count", 0, true, 0));
        commandsMap.put("print_field_descending_annual_turnover", new PrintAnnualTurnovers("print_field_descending_annual_turnover", 0, true, 0));
        commandsMap.put("save", new Save("save", 0, true, 0));
        commandsMap.put("execute_script", new ExecuteScript("execute_script", 1, true, 0));
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



    public static String getCommandName(String commandText){
        return commandText.split(" ")[0];
    }

    public static String[] getCommandArguments(String commandText){
        String[] commandArr = commandText.split(" ");
        return Arrays.copyOfRange(commandArr, 1, commandArr.length);
    }

    public static Command getCommandObject(String commandName){
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
                commandObject.setExecutedByScript(false);
            } catch (InvalidCommandArgumentsException | InvalidObjectFieldException | NoSuchElementException e){
                System.out.println(e.getMessage());
            } catch (NullPointerException e){
                System.out.println("There is no such command. The list of commands can be viewed by calling the help command");
            }
        }
        scanner.close();
    }
}
