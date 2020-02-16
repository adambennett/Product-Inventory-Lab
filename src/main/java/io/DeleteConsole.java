package io;

import models.Inventory;

import java.util.ArrayList;

public class DeleteConsole extends AbstractConsole {

    @Override
    protected void initializeCommands() {
        consoleCommands.put("remove", Command.REMOVE);
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
            //TODO
            // Improve this by using runAdd() logic from UpdateConsole
            // Right now you can only remove by ID
            // Should be able to remove by name(s) too
            // Should be able to remove a given amount of an item by ID or name, and if no amt is given remove all by default
            case REMOVE:
                for (String s : args) {
                    try {
                        Integer num = Integer.parseInt(s);
                        Inventory.remove(num);
                    } catch (NumberFormatException e) {}
                }
                printPrompt(PromptMessage.DELETE, true);
                return;
            case RETURN:
                Console console = new Console();
                console.printPrompt(PromptMessage.STANDARD, true);
                return;
            case HELP:
                printHelpCommand();
                printPrompt(PromptMessage.STANDARD, true);
                return;
        }
    }
}
