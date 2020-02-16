package io;

import models.Inventory;

import java.util.ArrayList;

public class UpdateConsole extends AbstractConsole {

    @Override
    protected void initializeCommands() {
        consoleCommands.put("list", Command.LIST);
        consoleCommands.put("add", Command.ADD);
        consoleCommands.put("sub", Command.SUB);
        consoleCommands.put("set", Command.SET);
        consoleCommands.put("return", Command.RETURN);
        consoleCommands.put("help", Command.HELP);
        consoleCommands.put("rollback", Command.ROLLBACK);
    }

    @Override
    public void runOnInvalidCommand(ArrayList<String> originalArgs)  {
        processCommand(Command.RETURN, originalArgs);
    }

    @Override
    public void processCommand(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case LIST:
                printPrompt(PromptMessage.LIST, true);
                return;
            case ADD:
                runAdd(args);
                printPrompt(PromptMessage.ADD, true);
                return;
            case SUB:
                //TODO
                // Reduce quantity of products using runAdd() logic
                return;
            case SET:
                //TODO
                // Set properties of products using runAdd() logic + create game/figure logic
                return;
            case ROLLBACK:
                Inventory.rollback();
                printPrompt(PromptMessage.UPDATE, true);
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

    public void runAdd(ArrayList<String> args) {
        int amtToIncreaseBy = 0;
        String name = "";
        int idToCheckFor = -1;
        boolean lookingByID = false;
        String trueFalse = "";
        ArrayList<String> possibleNames = new ArrayList<>();
        for (String s : args) {
            try {
                Integer num = Integer.parseInt(s);
                if (Inventory.isProduct(num)) {
                    lookingByID = true;
                    idToCheckFor = num;
                } else {
                    amtToIncreaseBy += num;
                }
            } catch (NumberFormatException e) {
                name += s + " ";
                possibleNames.add(s);
                if (s.toLowerCase().equals("true")) {
                    trueFalse = s.toLowerCase();
                }
            }
        }
        name = name.trim();
        boolean copyExact = trueFalse.equals("true");
        if (amtToIncreaseBy < 1) { amtToIncreaseBy = 1; }
        if (lookingByID) {
            Inventory.increaseAmtOfProduct(idToCheckFor, amtToIncreaseBy, copyExact);
        } else if (Inventory.isProduct(name)){
            Inventory.increaseAmtOfProduct(name, amtToIncreaseBy, copyExact);
        }
        else {
            boolean found = false;
            for (String s : possibleNames) {
                if (Inventory.isProduct(s)) {
                    Inventory.increaseAmtOfProduct(s, amtToIncreaseBy, copyExact);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Improper arguments. Expecting ADD command to be followed by a product identifier (either name or ID) and an amount to increase that product's quantity by.");
            }
        }
    }
}
