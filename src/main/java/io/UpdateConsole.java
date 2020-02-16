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
                int amtToIncreaseBy = 0;
                String name = "";
                int idToCheckFor = -1;
                boolean lookingByID = false;
                boolean stringNeedsSpace = false;
                for (String s : args) {
                    stringNeedsSpace = false;
                    String[] test;
                    try {
                            Integer num = Integer.parseInt(s);
                            if (Inventory.isProductID(num)) {
                                lookingByID = true;
                                idToCheckFor = num;
                            } else {
                                amtToIncreaseBy += num;
                            }
                        } catch (NumberFormatException e) {
                            if (stringNeedsSpace) {
                                name += " " + s;
                            }
                            else {
                                stringNeedsSpace = true;
                                name += s;
                            }

                        }
                }
                if (amtToIncreaseBy < 1) { amtToIncreaseBy = 1; }
                if (lookingByID) {
                    Inventory.increaseAmtOfProduct(idToCheckFor, amtToIncreaseBy);
                } else if (!name.equals("")){
                    Inventory.increaseAmtOfProduct(name, amtToIncreaseBy);
                } else {
                    System.out.println("Improper arguments. Expecting ADD command to be followed by a product identifier (either name or ID) and an amount to increase that product's quantity by.");
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
