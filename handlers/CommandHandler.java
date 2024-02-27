package handlers;

import commands.Command;
import commands.Help;
import exceptions.InvalidCommandArgumentsException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CommandHandler {
    HashMap<String, Command> commandsMap;
    private boolean isWorking;

    public CommandHandler(){
        setWorking(true);
        commandsMap = new HashMap<>();
        commandsMap.put("help", new Help("help", 0, true));
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
        String[] result = Arrays.copyOfRange(commandArr, 1, commandArr.length);
        System.out.println(result.length);
        for (String el:result){
            System.out.println(el);
        }
        return result;
    }

    private Command getCommandObject(String commandName){
        return commandsMap.get(commandName);
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        while (isWorking){
            System.out.print("\t> ");
            String commandText = scanner.next();
            Command commandObject = getCommandObject(getCommandName(commandText));
            try {
                commandObject.execute(getCommandArguments(commandText));
            } catch (InvalidCommandArgumentsException e){
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        scanner.close();
    }


}
