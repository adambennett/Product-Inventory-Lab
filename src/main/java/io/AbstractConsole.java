package io;

import models.BoardGame;
import models.Figurine;
import models.Inventory;
import models.Product;
import services.ConsoleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class AbstractConsole
{
    protected Map<String, Command> commandMap;
    protected Logger logger;

    public enum Command {
        BAD_COMMAND,
        CREATE,
        DELETE,
        EXIT,
        HELP,
        READ,
        REPORT,
        UPDATE,
        FIGURINE,
        BOARDGAME,
        RETURN,
        LIST,
        ADD,
        SUB,
        SET,
        REMOVE,
        HARDEXIT
    }

    public enum PromptMessage {
        CREATE,
        DELETE,
        READ,
        REPORT,
        STANDARD,
        UPDATE,
        WELCOME,
        REMOVE,
        MODIFY,
        LIST,
        ADD,
        SUB,
        SET
    }

    public AbstractConsole() {
        commandMap = new HashMap<>();
        setupCommands();
    }

    public ArrayList<String> generateUserInput() {
        String input = ConsoleService.getStringInput("");

        // Split the input into an array of seperate strings (this splits by spaces)
        String[] splited = input.split("\\s+");

        // Save the strings to an arraylist so we can pass it around more easily
        ArrayList<String> argus = new ArrayList<>();
        for (String s : splited) {
            argus.add(s);
        }

        return argus;
    }

    public boolean commandExists(String cmd) {
        if (this.commandMap != null && this.commandMap.containsKey(cmd.toLowerCase())) {
            return true;
        }
        return false;
    }

    protected void reprompt() {
        String input = ConsoleService.getStringInput("");

        // Split the input into an array of seperate strings (this splits by spaces)
        String[] splited = input.split("\\s+");

        // Save the strings to an arraylist so we can pass it around more easily
        ArrayList<String> argus = new ArrayList<>();
        for (String s : splited) {
            argus.add(s);
        }

        // Loop to check for input
        // If input is not entered, this waits for more
        while (argus.size() < 1) {
            argus = generateUserInput();
        }

        // Once we have at least 1 string of input, run a command
        runCommand(argus);
    }

    protected void printHelpCommand() {
        System.out.println("Printing a list of all available commands in this menu: ");
        ArrayList<String> uniques = new ArrayList<>();
        for (Map.Entry<String, Command> i : this.commandMap.entrySet()) {
            uniques.add(i.getKey().toUpperCase());
        }
        Collections.sort(uniques);
        for (String s : uniques) {
            System.out.println(s);
        }
    }

    public void printPrompt(PromptMessage message, boolean promptForInput) {
        switch (message) {
            case WELCOME:
                System.out.println("" +
                        "**************************************************\n" +
                        "***           Welcome and Bienvenue            ***\n" +
                        "***                    to                      ***\n" +
                        "***          ZipCo Inventory Manager           ***\n" +
                        "***                                            ***\n" +
                        "***               Enter a Command              ***\n" +
                        "***     Enter 'Help' for a List of Commands    ***\n" +
                        "**************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case STANDARD:
                System.out.println("" +
                        "**************************************************\n" +
                        "***     ZipCo Inventory Manager - Main Menu    ***\n" +
                        "**************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case CREATE:
                System.out.println("" +
                        "**************************************************\n" +
                        "***    ZipCo Inventory Manager - Create Menu   ***\n" +
                        "***                                            ***\n" +
                        "***                                            ***\n" +
                        "***         Board Game - Enter 'Game'          ***\n" +
                        "***         Figurine - Enter 'Figure'          ***\n" +
                        "***                                            ***\n" +
                        "***      Enter 'Help' for a List of Commands   ***\n" +
                        "**************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case UPDATE:
                String forLengthChecksD = "***                                                         ***";
                String allProductsD = getStringOfProducts(forLengthChecksD.length());
                System.out.println("" +
                        "***************************************************************\n" +
                        "***         ZipCo Inventory Manager - Update Menu         ***\n" +
                        "***                                                         ***\n" +
                        "***                                                         ***\n" +
                        allProductsD +
                        "***                                                         ***\n" +
                        "***           Enter 'Help' for a List of Commands           ***\n" +
                        "***************************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case DELETE:
                System.out.println("" +
                        "**************************************************\n" +
                        "*** ZipCo Inventory Manager - Enter Command(D) ***\n" +
                        "**************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case REPORT:
                System.out.println("" +
                        "**************************************************\n" +
                        "*** ZipCo Inventory Manager - Enter Command(R) ***\n" +
                        "**************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case REMOVE:
                String forLengthChecks = "***                                                         ***";
                String allProducts = getStringOfProducts(forLengthChecks.length());
                System.out.println("" +
                        "***************************************************************\n" +
                        "***          ZipCo Inventory Manager - Remove Menu          ***\n" +
                        "***                                                         ***\n" +
                        "***                                                         ***\n" +
                        allProducts +
                        "***                                                         ***\n" +
                        "***           Enter 'Help' for a List of Commands           ***\n" +
                        "***     Enter 'Remove' and Product ID to Remove Product     ***\n" +
                        "***************************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case ADD:
                String forLengthChecksC = "***                                                         ***";
                String allProductsC = getStringOfProducts(forLengthChecksC.length());
                System.out.println("" +
                        "***************************************************************\n" +
                        "***           ZipCo Inventory Manager - Add Menu            ***\n" +
                        "***                                                         ***\n" +
                        "***                                                         ***\n" +
                        allProductsC +
                        "***                                                         ***\n" +
                        "***           Enter 'Help' for a List of Commands           ***\n" +
                        "***  Enter 'Add' and Product ID to Increase Quantity by 1   ***\n" +
                        "***************************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case LIST:
                String forLengthChecksB = "***                                                         ***";
                String allProductsB = getStringOfProducts(forLengthChecksB.length());
                System.out.println("" +
                        "***************************************************************\n" +
                        "***         ZipCo Inventory Manager - Products Menu         ***\n" +
                        "***                                                         ***\n" +
                        "***                                                         ***\n" +
                        allProductsB +
                        "***                                                         ***\n" +
                        "***           Enter 'Help' for a List of Commands           ***\n" +
                        "***************************************************************\n");
                if (promptForInput) {
                    reprompt();
                }
                return;
            case MODIFY:

                return;
            default:
                printPrompt(PromptMessage.STANDARD, promptForInput);
                return;
        }
    }

    public void runCommand(ArrayList<String> args) {
        String command = args.remove(0).toLowerCase();
        String scCmd = "";
        boolean allowOtherConsoles = false;
        AbstractConsole checkConsole = null;
        if (commandExistsElsewhere(command)) {
            checkConsole = commandExistsInOtherConsole(command);
            allowOtherConsoles = true;
        }
        if (args.size() > 0) {
           scCmd = args.get(0).toLowerCase();
        }
        Command cmd = this.commandMap.get(command);

        if (commandExists(command)) {
            run(cmd, args);
        }
        else if (allowOtherConsoles) {
            cmd = checkConsole.commandMap.get(command);
            checkConsole.run(cmd, args);
        }
        else if (command.equals("best") && scCmd.equals("programmer")) {
            System.out.println("Nobles");
            printPrompt(PromptMessage.STANDARD, true);
        }
        else {
            runOnBadCommand(args);
        }
    }

    private static boolean commandExistsElsewhere(String cmd) {
        Console console = new Console();
        CreateConsole creator = new CreateConsole();
        DeleteConsole deletor = new DeleteConsole();
        UpdateConsole updater = new UpdateConsole();
        if (console.commandExists(cmd)) { return true; }
        else if (creator.commandExists(cmd)) { return true; }
        else if (deletor.commandExists(cmd)) { return true; }
        else if (updater.commandExists(cmd)) { return true; }
        return false;
    }

    private static AbstractConsole commandExistsInOtherConsole(String cmd) {
        Console console = new Console();
        CreateConsole creator = new CreateConsole();
        DeleteConsole deletor = new DeleteConsole();
        UpdateConsole updater = new UpdateConsole();
        if (console.commandExists(cmd)) { return console; }
        else if (creator.commandExists(cmd)) { return creator; }
        else if (deletor.commandExists(cmd)) { return deletor; }
        else if (updater.commandExists(cmd)) { return updater; }
        return null;
    }

    public static String getStringOfProducts(int lengthToCheck) {
        ArrayList<String> uniques = new ArrayList<>();
        String toRet = "";
        for (Product p : Inventory.getProducts()) {
            String prefix = "***";
            String suffix = "***\n";
            String space = " ";
            String productInfo =  "[" + p.getId() + "]: " + ConsoleService.capFirstLetter(p.getName());

            if (p instanceof Figurine) {
                String color = ConsoleService.capFirstLetter(((Figurine) p).getColor());
                productInfo += " (" +  color + ")";
            }

            int reductionCounter = productInfo.length();
            while (productInfo.length() + suffix.length() + prefix.length() > lengthToCheck) {
                productInfo = productInfo.substring(0, reductionCounter);
                reductionCounter--;
            }
            boolean flipSpaceToAdd = false;
            while (productInfo.length() + suffix.length() + prefix.length() < lengthToCheck + 1) {
                if (flipSpaceToAdd) { prefix += space; }
                else { suffix = space + suffix; }
                flipSpaceToAdd = !flipSpaceToAdd;
            }
            productInfo = prefix + productInfo + suffix;
            if (!uniques.contains(productInfo)) {
                uniques.add(productInfo);
            }
        }
        for (String s : uniques) {
            toRet += s;
        }

        return toRet;
    }

    public static ArrayList<String> getListOfProducts() {
        ArrayList<String> uniques = new ArrayList<>();
        for (Product p : Inventory.getProducts()) {
            String s = "[" + Inventory.amtOfProductByName(p) + "]: " + ConsoleService.capFirstLetter(p.getName());
            if (p instanceof BoardGame) {
                BoardGame bg = (BoardGame)p;
                s += " (Ages " + bg.getAgeMinimum() + " to " + bg.getAgeMax() + ", Playtime: " + bg.getAvgPlayingTime() + " minutes)";
            }
            else if (p instanceof Figurine) {
                Figurine fig = (Figurine)p;
                String color = ConsoleService.capFirstLetter(fig.getColor());
                s += " (" + color + ")";
            }
            if (!uniques.contains(s)) {
                uniques.add(s);
            }
        }
        return uniques;
    }

    protected abstract void setupCommands();

    public abstract void runOnBadCommand(ArrayList<String> originalArgs);

    public abstract void run(Command cmd, ArrayList<String> args);

}
