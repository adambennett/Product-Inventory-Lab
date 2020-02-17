package io;

import models.BoardGame;
import models.Figurine;
import models.Inventory;
import models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UpdateConsole extends AbstractConsole {

    private static Map<String, FieldCommands> setCommands;

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
                printPrompt(PromptMessage.SET, true);
                return;
            case ROLLBACK:
                Inventory.rollback();
                printPrompt(PromptMessage.UPDATE, true);
                return;
            case RETURN:
                MainConsole mainConsole = new MainConsole();
                mainConsole.printPrompt(PromptMessage.STANDARD, true);
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
        analyzeUserInputSet(args);
    }

    private void analyzeUserInputSet(ArrayList<String> args) {
        int amt = 0;
        boolean copyExact = false;
        String name = "";
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> fieldIdentifiers = new ArrayList<>();
        ArrayList<String> newFields = new ArrayList<>();

        if (args.size() > 0) {
            try {
                Integer prodID = Integer.parseInt(args.get(0));
                if (Inventory.isProduct(prodID)) {
                    ids.add(prodID);
                }
            } catch (NumberFormatException e) {
                if (Inventory.isProduct(args.get(0))) {
                    names.add(args.get(0));
                }
            }
        }

        for (int i = 1; i < args.size(); i++) {
            boolean found = false;
            try {
                Integer prodID = Integer.parseInt(args.get(i));
                if (Inventory.isProduct(prodID)) {
                    ids.add(prodID);
                    found = true;
                }
            } catch (NumberFormatException e) {}

            if (setCommands.containsKey(args.get(i).toLowerCase()) && !found) {
                fieldIdentifiers.add(args.get(i).toLowerCase());
                found = true;
            }

            if (!found) {
                newFields.add(args.get(i));
            }
        }

        int counter = 0;
        int newCounter = 0;
        boolean found = false;
        try {
            for (String s : names) {
                boolean tempFound = Inventory.modify(Inventory.get(s), setCommands.get(fieldIdentifiers.get(counter)), newFields.get(newCounter));
                if (tempFound) {
                    counter++;
                    newCounter++;
                    found = true;
                    if (fieldIdentifiers.size() <= counter || newFields.size() <= newCounter) {
                        break;
                    }
                }
            }

            for (Integer id : ids) {
                boolean tempFound = Inventory.modify(Inventory.get(id), setCommands.get(fieldIdentifiers.get(counter)), newFields.get(newCounter));
                if (tempFound) {
                    counter++;
                    newCounter++;
                    found = true;
                    if (fieldIdentifiers.size() <= counter || newFields.size() <= newCounter) {
                        break;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Logger.getGlobal().info("Set command caused arrays to go out of bounds - probably you entered the command parameters incorrectly!");
        }

        if (!found) {
            System.out.println(getImproperArgumentsMessage(Modification.SET));
        }
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
                return "Improper arguments. Expecting SET command to be followed by a product identifier (either name or ID), field identifier (full field name or shorthand), and a new value for the given field.";
            default:
                return "";
        }
    }

    private void processUserInput(Modification mod, int amt, boolean copyExact, ArrayList<String> possibleNames, ArrayList<Integer> possibleIDs) {
        String notFoundMsg = getImproperArgumentsMessage(mod);
        boolean found = false;
        if (mod.equals(Modification.SUB) && amt == 0) {
            amt = Inventory.size();
        } else if (amt < 1) {
            amt = 1;
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

    public static Boolean fieldCommandMatchesProductType(FieldCommands fc, Product prod) {
        switch (fc) {
            case COLOR:
                return prod instanceof Figurine;
            case MANUFACTURER:
            case AGE:
            case PLAYTIME:
                return prod instanceof BoardGame;
            case NAME:
            case ID:
                return true;
            default:
                return false;
        }
    }

    static {
        setCommands = new HashMap<>();

        setCommands.put("color", FieldCommands.COLOR);
        setCommands.put("c", FieldCommands.COLOR);

        setCommands.put("manufacturer", FieldCommands.MANUFACTURER);
        setCommands.put("m", FieldCommands.MANUFACTURER);

        setCommands.put("age", FieldCommands.AGE);
        setCommands.put("a", FieldCommands.AGE);

        setCommands.put("playtime", FieldCommands.PLAYTIME);
        setCommands.put("p", FieldCommands.PLAYTIME);

        setCommands.put("name", FieldCommands.NAME);
        setCommands.put("n", FieldCommands.NAME);

        setCommands.put("id", FieldCommands.ID);
        setCommands.put("i", FieldCommands.ID);
    }

    private enum Modification {
        ADD,
        SUB,
        SET
    }

    public enum FieldCommands {
        COLOR,
        MANUFACTURER,
        AGE,
        PLAYTIME,
        NAME,
        ID
    }
}
