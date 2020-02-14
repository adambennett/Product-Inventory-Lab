package io;

import models.Product;
import services.ConsoleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Console {

    private static Map<String, Command> commandMap;
    private static Logger logger;

    public enum Command {
        CREATE,
        READ,
        UPDATE,
        DELETE,
        REPORT,
        EXIT,
        BAD_COMMAND,
        HELP
    }

    public static boolean commandExists(String cmd) {
        if (commandMap != null && commandMap.containsKey(cmd.toLowerCase())) {
            return true;
        }
        return false;
    }

    public static void runCommand(ArrayList<String> args) {
        if (commandMap != null && commandMap.containsKey(args.get(0).toLowerCase())) {
            run(commandMap.get(args.get(0).toLowerCase()), args);
        }
        else if (args.size() > 1 && args.get(0).toLowerCase().equals("best") && args.get(1).toLowerCase().equals("programmer")) {
            System.out.println("Nobles");
            printWelcomeWithPrompt();
        }
        else {
            run(Command.BAD_COMMAND, args);
        }
    }

    public static void printWelcome(){
        System.out.println("" +
                "**************************************************" +
                "***           Welcome and Bienvenue            ***" +
                "***                    to                      ***" +
                "***          ZipCo Inventory Manager           ***" +
                "**************************************************");

        ConsoleService.getStringInput("");
    }

    public static ArrayList<String> generateUserInput() {
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

    public static void printWelcomeWithPrompt(){
        System.out.println("" +
                "**************************************************\n" +
                "***           Welcome and Bienvenue            ***\n" +
                "***                    to                      ***\n" +
                "***          ZipCo Inventory Manager           ***\n" +
                "***                                            ***\n" +
                "***               Enter a Command              ***\n" +
                "**************************************************\n");


    }

    public static void reprompt() {
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

    public static void printPrompt(){
        System.out.println("" +
                "**************************************************\n" +
                "***           ZipCo Inventory Manager          ***\n" +
                "***                                            ***\n" +
                "***               Enter a Command              ***\n" +
                "**************************************************\n");


    }

    public static void run(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case CREATE:
                System.out.println("Create!");
                printPrompt();
                return;
            case READ:
                System.out.println("Read!");
                int counter = 0;
                for (Product p : App.inventory.getProducts()) {
                    System.out.println("[" + counter + "]: " + p.getName());
                }
                printPrompt();
                return;
            case UPDATE:
                System.out.println("Update!");
                printPrompt();
                return;
            case DELETE:
                System.out.println("Delete!");
                printPrompt();
                return;

            case REPORT:
                System.out.println("Report!");
                printPrompt();
                return;
            case EXIT:
                System.exit(0);
                return;
            case BAD_COMMAND:
                System.out.println("Bad command! Please enter a valid command, or enter 'Help' to view a list of all commands.");
                return;
            case HELP:
                printHelpCommand(commandMap);
                printPrompt();
                return;
            default:
                run(Command.BAD_COMMAND, null);
                return;
        }
    }


    public static void printHelpCommand(Map<String, Command> commands) {
        System.out.println("Printing a list of all available commands in this menu: ");
        ArrayList<String> uniques = new ArrayList<>();
        for (Map.Entry<String, Command> i : commands.entrySet()) {
            uniques.add(i.getKey().toUpperCase());
        }
        Collections.sort(uniques);
        for (String s : uniques) {
            System.out.println(s);
        }
    }


    static {
        commandMap = new HashMap<>();
        commandMap.put("create", Command.CREATE);
        commandMap.put("read", Command.READ);
        commandMap.put("update", Command.UPDATE);
        commandMap.put("delete", Command.DELETE);
        commandMap.put("report", Command.REPORT);
        commandMap.put("exit", Command.EXIT);
        commandMap.put("help", Command.HELP);
    }
}
