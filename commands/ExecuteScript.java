package commands;

import exceptions.InvalidCommandArgumentsException;
import exceptions.InvalidFileException;
import exceptions.InvalidObjectFieldException;
import handlers.CollectionHandler;
import handlers.CommandHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class ExecuteScript extends Command {
    public ExecuteScript(String name, int argumentsCount, boolean isInlineArgument, int notInlineArgumentsCount) {
        super(name, argumentsCount, isInlineArgument, notInlineArgumentsCount);
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {
        if (checkArgumentsCount(arguments)) {
            if (!CommandHandler.checkIfScriptInStack(arguments[0])){
                try {
                    CommandHandler.addScriptIntoStack(arguments[0]);
                    File file = new File(arguments[0]);
                    Scanner scanner = new Scanner(file);
                    Command currentCommand = null;
                    String scriptLine;
                    int readArguments = 0;
                    String currentCommandArguments = "";
                    String[] commandInlineArguments = null;
                    boolean commandInProcess = false;
                    while (scanner.hasNext()) {
                        scriptLine = scanner.nextLine();
                        if (!commandInProcess) {
                            currentCommand = CommandHandler.getCommandObject(CommandHandler.getCommandName(scriptLine));
                            currentCommand.setExecutedByScript(true);
                            if (currentCommand.isInlineArgument()) {
                                commandInlineArguments = CommandHandler.getCommandArguments(scriptLine);
                                currentCommand.execute(collectionHandler, commandInlineArguments);
                            } else {
                                commandInProcess = true;
                                readArguments = currentCommand.getNotInlineArgumentsCount();
                                commandInlineArguments = CommandHandler.getCommandArguments(scriptLine);
                            }
                        } else {
                            if (readArguments > 1){
                                currentCommandArguments += scriptLine + "\n";
                                readArguments--;
                            } else {
                                currentCommandArguments += scriptLine + "\n";
                                readArguments--;
                                try {
                                    currentCommand.execute(collectionHandler, commandInlineArguments, currentCommandArguments);
                                    currentCommandArguments = "";
                                } catch (InvalidFileException e){
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                commandInProcess = false;
                            }
                        }
                    }
                    CommandHandler.removeFromStack(arguments[0]);
                } catch (FileNotFoundException e) {
                    throw new InvalidCommandArgumentsException("File not found. Script can't be executed");
                }
            } else {
                System.out.println("Script can't be executed in cause of recursion!");
            }

        } else {
            throw new InvalidCommandArgumentsException("Invalid number of arguments. Expected: " + getArgumentsCount() + ", given: " + arguments.length);
        }
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] arguments, String notInlineArguments) throws InvalidCommandArgumentsException, InvalidObjectFieldException {

    }
}