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
                runSub(args);
                printPrompt(PromptMessage.SUB, true);
                return;
            case SET:
                runSet(args);
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
                printHelpCommand(this);
                printPrompt(PromptMessage.BLANK, true);
                return;
        }
    }

    public void runAdd(ArrayList<String> args) {
        analyzeUserInput(args, Modification.ADD);
    }

    public void runSub(ArrayList<String> args) {
        analyzeUserInput(args, Modification.SUB);
    }

    public void runSet(ArrayList<String> args) {
        analyzeUserInput(args, Modification.SET);
    }

    private void analyzeUserInput(ArrayList<String> args, Modification mod) {
        int amt = 0;
        boolean copyExact = false;
        String name = "";
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String cmdArg : args) {
            try {
                Integer num = Integer.parseInt(cmdArg);
                if (Inventory.isProduct(num)) {
                    ids.add(num);
                } else {
                    amt += num;
                }
            } catch (NumberFormatException e) {
                if (cmdArg.toLowerCase().equals("true")) {
                    copyExact = true;
                }
                else if (Inventory.isProduct(cmdArg)) {
                    names.add(cmdArg);
                } else {
                    name += cmdArg + " ";
                }
            }
        }

        name = name.trim();
        if (Inventory.isProduct(name)) {
            names.add(name);
        }
        processUserInput(mod, amt, copyExact, names, ids);
    }

    private void processSpecificCommandsFromInput(Modification mod, int id, String name, int amt, boolean copyExact) {
        switch (mod) {
            case ADD:
                if (!name.equals("")) {
                    Inventory.increaseAmtOfProduct(name, amt, copyExact);
                } else if (id != -1) {
                    Inventory.increaseAmtOfProduct(id, amt, copyExact);
                }
                return;
            case SUB:
                if (!name.equals("")) {
                    Inventory.decreaseAmtOfProduct(name, amt);
                } else if (id != -1) {
                    Inventory.decreaseAmtOfProduct(id, amt);
                }
                return;
            case SET:
                // TODO
                return;
        }
    }

    private String getImproperArgumentsMessage(Modification mod) {
        switch (mod) {
            case ADD:
                return "Improper arguments. Expecting ADD command to be followed by a product identifier (either name or ID) and an amount to increase that product's quantity by.";
            case SUB:
                return "Improper arguments. Expecting SUB command to be followed by a product identifier (either name or ID) and an amount to decrease that product's quantity by (optional, defaults to remove all).";
            case SET:
                // TODO
                return "TODO";
            default:
                return "";
        }
    }

    private void processUserInput(Modification mod, int amt, boolean copyExact, ArrayList<String> possibleNames, ArrayList<Integer> possibleIDs) {
        String notFoundMsg = getImproperArgumentsMessage(mod);
        boolean found = false;
        if (mod.equals(Modification.SUB) && amt == 0) {
            amt = Inventory.size();
        }

        for (Integer id : possibleIDs) {
            if (Inventory.isProduct(id)) {
                processSpecificCommandsFromInput(mod, id, "", amt, copyExact);
                found = true;
            }
        }

        for (String name : possibleNames) {
            if (Inventory.isProduct(name)) {
                processSpecificCommandsFromInput(mod, -1, name, amt, copyExact);
                found = true;
            }
        }

        if (!found) {
            System.out.println(notFoundMsg);
        }
    }

    private enum Modification {
        ADD,
        SUB,
        SET
    }
}
