package io;

import models.Inventory;

import java.util.ArrayList;

public class UpdateConsole extends AbstractConsole {

    @Override
    protected void setupCommands() {
        commandMap.put("list", Command.LIST);
        commandMap.put("add", Command.ADD);
        commandMap.put("sub", Command.SUB);
        commandMap.put("set", Command.SET);
        commandMap.put("return", Command.RETURN);
        commandMap.put("help", Command.HELP);
    }

    @Override
    public void runOnBadCommand(ArrayList<String> originalArgs)  {
        run(Command.RETURN, originalArgs);
    }

    @Override
    public void run(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case LIST:
                printPrompt(PromptMessage.LIST, true);
                return;
            case ADD:
                for (String s : args) {
                    try {
                        Integer num = Integer.parseInt(s);
                        Inventory.increaseAmtOfProduct(num, 1);
                    } catch (NumberFormatException e) {
                        Inventory.increaseAmtOfProduct(s, 1);
                    }
                }
                printPrompt(PromptMessage.ADD, true);
                return;
            case SUB:
                //TODO
                return;
            case SET:
                //TODO
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
