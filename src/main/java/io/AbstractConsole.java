package io;

import services.ConsoleService;
import utils.MenuStrings;
import utils.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConsole
{
    protected Map<String, Command> consoleCommands;

    public AbstractConsole() {
        consoleCommands = new HashMap<>();
        initializeCommands();
    }

    private ArrayList<String> generateUserInput() {
        String input = ConsoleService.getStringInput("");
        String[] splited = input.split("\\s+");
        ArrayList<String> argus = new ArrayList<>();
        for (String s : splited) {
            argus.add(s);
        }
        return argus;
    }

    public boolean commandExists(String cmd) {
        if (this.consoleCommands != null && this.consoleCommands.containsKey(cmd.toLowerCase())) {
            return true;
        }
        return false;
    }

    protected void getUserInput() {
        String input = ConsoleService.getStringInput("");
        String[] splited = input.split("\\s+");
        ArrayList<String> argus = new ArrayList<>();
        for (String s : splited) {
            argus.add(s);
        }
        while (argus.size() < 1) {
            argus = generateUserInput();
        }
        findAndProcessCommand(argus);
    }

    protected void printHelpCommand() {
        System.out.println("Printing a list of all available commands in this menu: ");
        ArrayList<String> uniques = new ArrayList<>();
        for (Map.Entry<String, Command> i : this.consoleCommands.entrySet()) {
            uniques.add(i.getKey().toUpperCase());
        }
        Collections.sort(uniques);
        for (String s : uniques) {
            System.out.println(s);
        }
    }

    public void printPrompt(PromptMessage message, boolean promptForInput) {
        MenuStrings.loadStrings();
        String promptString = MenuStrings.getStringFromPromptType(message);
        System.out.println(promptString);
        if (promptForInput) {
            getUserInput();
        }
    }

    public void findAndProcessCommand(ArrayList<String> args) {
        String fullCommand = "";
        for (String s : args) {
            fullCommand += s + " ";
        }
        String command = args.remove(0).toLowerCase();
        String hiddenCmd = "";
        boolean allowOtherConsoles = false;
        AbstractConsole consoleToExecuteFrom = null;

        if (isValidCommandInAnyConsole(command)) {
            consoleToExecuteFrom = whichConsoleHasThisCommand(command);
            allowOtherConsoles = true;
        }
        if (commandExists(command)) {
            this.processCommand(this.consoleCommands.get(command), args);
            Utilities.setLastCommand(fullCommand);
        }
        else if (allowOtherConsoles) {
            consoleToExecuteFrom.processCommand(consoleToExecuteFrom.consoleCommands.get(command), args);
            Utilities.setLastCommand(fullCommand);
        }
        else if (command.equals("best") && hiddenCmd.toLowerCase().equals("programmer")) {
            System.out.println("Nobles");
            printPrompt(PromptMessage.STANDARD, true);
            Utilities.setLastCommand(command + " " + hiddenCmd);
        }
        else {
            runOnInvalidCommand(args);
        }
    }

    private static boolean isValidCommandInAnyConsole(String cmd) {
        Console console = new Console();
        CreateConsole creator = new CreateConsole();
        DeleteConsole deletor = new DeleteConsole();
        UpdateConsole updater = new UpdateConsole();
        if (console.commandExists(cmd)) { return true; }
        else if (creator.commandExists(cmd)) { return true; }
        else if (deletor.commandExists(cmd)) { return true; }
        else if (updater.commandExists(cmd)) { return true; }
        return false;
    }

    private static AbstractConsole whichConsoleHasThisCommand(String cmd) {
        Console console = new Console();
        CreateConsole creator = new CreateConsole();
        DeleteConsole deletor = new DeleteConsole();
        UpdateConsole updater = new UpdateConsole();
        if (console.commandExists(cmd)) { return console; }
        else if (creator.commandExists(cmd)) { return creator; }
        else if (deletor.commandExists(cmd)) { return deletor; }
        else if (updater.commandExists(cmd)) { return updater; }
        return null;
    }

    protected abstract void initializeCommands();

    public abstract void runOnInvalidCommand(ArrayList<String> originalArgs);

    public abstract void processCommand(Command cmd, ArrayList<String> args);

    public enum Command {
        BAD_COMMAND,
        CREATE,
        DELETE,
        EXIT,
        HELP,
        READ,
        REPORT,
        UPDATE,
        FIGURINE,
        BOARDGAME,
        RETURN,
        LIST,
        ADD,
        SUB,
        SET,
        REMOVE,
        HARDEXIT,
        ROLLBACK,
        COMMANDS
    }

    public enum PromptMessage {
        CREATE,
        DELETE,
        READ,
        REPORT,
        STANDARD,
        UPDATE,
        WELCOME,
        MODIFY,
        LIST,
        ADD,
        SUB,
        SET,
        GOODBYE,
        CREATE_WITH_PROD,
        COMMANDS
    }

}
