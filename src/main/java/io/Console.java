package io;

import models.Inventory;
import services.ConsoleService;
import utils.Utilities;

import java.util.ArrayList;

public class Console extends AbstractConsole {

    @Override
    protected void initializeCommands() {
        consoleCommands.put("create", Command.CREATE);
        consoleCommands.put("read", Command.READ);
        consoleCommands.put("update", Command.UPDATE);
        consoleCommands.put("delete", Command.DELETE);
        consoleCommands.put("report", Command.REPORT);
        consoleCommands.put("exit", Command.EXIT);
        consoleCommands.put("exitnosave", Command.HARDEXIT);
        consoleCommands.put("help", Command.HELP);
        consoleCommands.put("commands", Command.COMMANDS);
        consoleCommands.put("save", Command.SAVEJSON);
        consoleCommands.put("savecsv", Command.SAVECSV);
        consoleCommands.put("loadcsv", Command.LOADCSV);
        consoleCommands.put("load", Command.LOADJSON);

    }

    @Override
    public void runOnInvalidCommand(ArrayList<String> originalArgs) {
        processCommand(Command.BAD_COMMAND, originalArgs);
    }

    @Override
    public void processCommand(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case CREATE:
                CreateConsole createConsole = new CreateConsole();
                if (isCreatingRandom(args)) {
                    handleRandomCreate(args, createConsole);
                } else {
                    createConsole.printPrompt(PromptMessage.CREATE, true);
                }
                return;
            case READ:
                printReadList();
                printPrompt(PromptMessage.READ, true);
                return;
            case UPDATE:
                UpdateConsole updateConsole = new UpdateConsole();
                updateConsole.printPrompt(PromptMessage.UPDATE, true);
                return;
            case DELETE:
                DeleteConsole deleteConsole = new DeleteConsole();
                deleteConsole.printPrompt(PromptMessage.DELETE, true);
                return;
            case REPORT:
                ReportConsole reportConsole = new ReportConsole();
                reportConsole.processCommand(Command.REPORT, args);
                return;
            case EXIT:
                ConsoleService.saveAllInventoryDataAsJSON();
                printPrompt(PromptMessage.GOODBYE, false);
                return;
            case HARDEXIT:
                printPrompt(PromptMessage.GOODBYE, false);
                return;
            case BAD_COMMAND:
                System.out.println("Bad command! Please enter a valid command, or enter 'Commands' to view a formatted list of all commands.");
                printPrompt(PromptMessage.STANDARD, true);
                return;
            case HELP:
                printHelpCommand(this);
                printPrompt(PromptMessage.BLANK, true);
                return;
            case COMMANDS:
                printPrompt(PromptMessage.COMMANDS, true);
                return;
            case SAVECSV:
                ConsoleService.saveAllInventoryData();
                return;
            case SAVEJSON:
                if (args.size() > 1) {
                    ConsoleService.saveAllInventoryDataAsJSON(args.get(1), args.get(0));
                } else {
                    ConsoleService.saveAllInventoryDataAsJSON();
                }
                printPrompt(PromptMessage.STANDARD, true);
                return;
            case LOADCSV:
                Inventory.loadData();
                return;
            case LOADJSON:
                if (args.size() > 1) {
                    Inventory.loadData(args.get(1), args.get(0));
                } else {
                    Inventory.loadData("BoardGames", "Figurines");
                }
                return;
            default:
                processCommand(Command.BAD_COMMAND, null);
                return;
        }
    }

    private void printReadList() {
        for (String s : Utilities.getListOfProducts()) {
            System.out.println(s);
        }
    }

    private void handleRandomCreate(ArrayList<String> args, CreateConsole createConsole) {
        createRandomProducts(args);
        if (Inventory.size() < 101) {
            createConsole.printPrompt(PromptMessage.CREATE_WITH_PROD, true);
        } else {
            createConsole.printPrompt(PromptMessage.CREATE, true);
        }
    }

    private Boolean isCreatingRandom(ArrayList<String> args) {
        if (args.size() > 0 && args.get(0).toLowerCase().equals("random")) {
            return true;
        }
        return false;
    }

    private void createRandomProducts(ArrayList<String> args) {
        int amt = 0;
        if (args.size() > 1) {
            for (int i = 1; i < args.size(); i++) {
                try {
                    Integer more = Integer.parseInt(args.get(1));
                    amt += more;
                } catch (NumberFormatException e) {
                }
            }
        }
        if (amt < 1) { amt = 1; }
        Utilities.addRandomProductToInventory(amt);
    }


}
