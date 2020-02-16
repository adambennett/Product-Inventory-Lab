package io;

import services.ConsoleService;
import utils.Utilities;

import java.util.ArrayList;

public class Console extends AbstractConsole {

    @Override
    protected void initializeCommands() {
        consoleCommands.put("create", Command.CREATE);
        consoleCommands.put("read", Command.READ);
        consoleCommands.put("update", Command.UPDATE);
        consoleCommands.put("delete", Command.DELETE);
        consoleCommands.put("report", Command.REPORT);
        consoleCommands.put("exit", Command.EXIT);
        consoleCommands.put("exitnosave", Command.HARDEXIT);
        consoleCommands.put("help", Command.HELP);
        consoleCommands.put("commands", Command.COMMANDS);
    }

    @Override
    public void runOnInvalidCommand(ArrayList<String> originalArgs) {
        processCommand(Command.BAD_COMMAND, originalArgs);
    }

    @Override
    public void processCommand(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case CREATE:
                CreateConsole createConsole = new CreateConsole();
                createConsole.printPrompt(PromptMessage.CREATE, true);
                return;
            case READ:
                printReadList();
                printPrompt(PromptMessage.READ, true);
                return;
            case UPDATE:
                UpdateConsole updateConsole = new UpdateConsole();
                updateConsole.printPrompt(PromptMessage.UPDATE, true);
                return;
            case DELETE:
                DeleteConsole deleteConsole = new DeleteConsole();
                deleteConsole.printPrompt(PromptMessage.DELETE, true);
                return;
            case REPORT:
                ReportConsole reportConsole = new ReportConsole();
                reportConsole.printPrompt(PromptMessage.REPORT, true);
                return;
            case EXIT:
                ConsoleService.saveAllInventoryDataAsJSON();
                printPrompt(PromptMessage.GOODBYE, false);
                return;
            case HARDEXIT:
                printPrompt(PromptMessage.GOODBYE, false);
                return;
            case BAD_COMMAND:
                System.out.println("Bad command! Please enter a valid command, or enter 'Commands' to view a formatted list of all commands.");
                printPrompt(PromptMessage.STANDARD, true);
                return;
            case HELP:
                printHelpCommand();
                printPrompt(PromptMessage.STANDARD, true);
                return;
            case COMMANDS:
                printPrompt(PromptMessage.COMMANDS, true);
                return;
            default:
                processCommand(Command.BAD_COMMAND, null);
                return;
        }
    }

    private void printReadList() {
        for (String s : Utilities.getListOfProducts()) {
            System.out.println(s);
        }
    }


}
