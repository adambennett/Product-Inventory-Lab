package io;

import models.Inventory;
import models.Product;
import utils.Utilities;

import java.util.ArrayList;

public class ReportConsole extends AbstractConsole {
    @Override
    protected void initializeCommands() {
        consoleCommands.put("list", Command.LIST);
        consoleCommands.put("report", Command.REPORT);
        consoleCommands.put("return", Command.RETURN);
        consoleCommands.put("read", Command.READ);
        consoleCommands.put("help", Command.HELP);
    }

    @Override
    public void runOnInvalidCommand(ArrayList<String> originalArgs)    {
        processCommand(Command.RETURN, originalArgs);
    }

    @Override
    public void processCommand(Command cmd, ArrayList<String> args) {
        Console console = new Console();
        switch (cmd) {
            case LIST:
                printPrompt(PromptMessage.LIST, true);
                return;
            case REPORT:
                analyzeReportInput(args);
                printPrompt(PromptMessage.REPORT, true);
                return;
            case READ:
                console.processCommand(Command.READ, null);
            case RETURN:
                console.printPrompt(PromptMessage.STANDARD, true);
                return;
            case HELP:
                printHelpCommand(this);
                printPrompt(PromptMessage.BLANK, true);
                return;
        }
    }

    private void analyzeReportInput(ArrayList<String> args) {
        String name = "";
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String cmdArg : args) {
            try {
                Integer num = Integer.parseInt(cmdArg);
                if (Inventory.isProduct(num)) {
                    ids.add(num);
                }
            } catch (NumberFormatException e) {
                if (Inventory.isProduct(cmdArg)) {
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
        runReport(names, ids);
    }

    private void runReport(ArrayList<String> names, ArrayList<Integer> ids) {
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (String name : names) {
            matchingProducts.addAll(Inventory.getAll(name));
        }
        for (Integer id : ids) {
            matchingProducts.addAll(Inventory.getAll(id));
        }
        printReport(matchingProducts);
    }

    private void printReport(ArrayList<Product> productsToReportOn) {
        if (productsToReportOn.size() > 0) {
            String forLengthChecks = "**********************************************************************************";
            String allProducts = Utilities.getReportString(forLengthChecks, productsToReportOn);
            String toPrint = "" +
                    "**********************************************************************************\n" +
                    "**                            Generated Reports                                 **\n" +
                    allProducts +
                    "**********************************************************************************\n";
            System.out.println(toPrint);
        }
    }
}
