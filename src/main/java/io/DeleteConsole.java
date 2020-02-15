package io;

import models.Inventory;

import java.util.ArrayList;

public class DeleteConsole extends AbstractConsole {

    @Override
    protected void setupCommands() {
        commandMap.put("remove", Command.REMOVE);
        commandMap.put("return", Command.RETURN);
        commandMap.put("help", Command.HELP);
    }

    @Override
    public void runOnBadCommand(ArrayList<String> originalArgs)   {
        run(Command.RETURN, originalArgs);
    }

    @Override
    public void run(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case REMOVE:
                for (String s : args) {
                    try {
                        Integer num = Integer.parseInt(s);
                        Inventory.remove(num);
                    } catch (NumberFormatException e) {}
                }
                printPrompt(PromptMessage.REMOVE, true);
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
