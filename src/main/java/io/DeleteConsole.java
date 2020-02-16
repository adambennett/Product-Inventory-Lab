package io;

import java.util.ArrayList;

public class DeleteConsole extends AbstractConsole {

    @Override
    protected void initializeCommands() {
        consoleCommands.put("sub", Command.SUB);
        consoleCommands.put("return", Command.RETURN);
        consoleCommands.put("help", Command.HELP);
    }

    @Override
    public void runOnInvalidCommand(ArrayList<String> originalArgs)   {
        processCommand(Command.RETURN, originalArgs);
    }

    @Override
    public void processCommand(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case SUB:
                UpdateConsole updater = new UpdateConsole();
                updater.runSub(args);
                printPrompt(PromptMessage.SUB, true);
                return;
            case RETURN:
                Console console = new Console();
                console.printPrompt(PromptMessage.STANDARD, true);
                return;
            case HELP:
                printHelpCommand(this);
                printPrompt(PromptMessage.BLANK, true);
                return;
        }
    }
}
