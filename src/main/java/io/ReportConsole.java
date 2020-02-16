package io;

import java.util.ArrayList;

public class ReportConsole extends AbstractConsole {
    @Override
    protected void initializeCommands() {
        consoleCommands.put("list", Command.LIST);
        consoleCommands.put("report", Command.REPORT);
        consoleCommands.put("return", Command.RETURN);
        consoleCommands.put("read", Command.READ);
        consoleCommands.put("help", Command.HELP);
    }

    @Override
    public void runOnInvalidCommand(ArrayList<String> originalArgs)    {
        processCommand(Command.RETURN, originalArgs);
    }

    @Override
    public void processCommand(Command cmd, ArrayList<String> args) {
        Console console = new Console();
        switch (cmd) {
            case LIST:
                printPrompt(PromptMessage.LIST, true);
                return;
            case REPORT:
                //TODO
                // Generate more detailed reports of specific products by name or ID
                return;
            case READ:
                console.processCommand(Command.READ, null);
            case RETURN:
                console.printPrompt(PromptMessage.STANDARD, true);
                return;
            case HELP:
                printHelpCommand(this);
                printPrompt(PromptMessage.BLANK, true);
                return;
        }
    }
}
