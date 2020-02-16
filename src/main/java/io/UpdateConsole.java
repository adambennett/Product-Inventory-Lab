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
        commandMap.put("rollback", Command.ROLLBACK);
    }

    @Override
    public void runOnBadCommand(ArrayList<String> originalArgs)  {
        run(Command.RETURN, originalArgs);
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

    @Override
    public void run(Command cmd, ArrayList<String> args) {
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
                //TODO
                // Should reset the inventory to the initial state upon load (load from CSV/JSON or from copy made on program init?)
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
