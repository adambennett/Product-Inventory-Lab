package io;

import services.ConsoleService;

import java.io.IOException;
import java.util.ArrayList;

public class Console extends AbstractConsole {

    @Override
    protected void setupCommands() {
        commandMap.put("create", Command.CREATE);
        commandMap.put("read", Command.READ);
        commandMap.put("update", Command.UPDATE);
        commandMap.put("delete", Command.DELETE);
        commandMap.put("report", Command.REPORT);
        commandMap.put("exit", Command.EXIT);
        commandMap.put("exitnosave", Command.HARDEXIT);
        commandMap.put("help", Command.HELP);
    }

    @Override
    public void runOnBadCommand(ArrayList<String> originalArgs) {
        run(Command.BAD_COMMAND, originalArgs);
    }

    @Override
    public void run(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case CREATE:
                CreateConsole createConsole = new CreateConsole();
                createConsole.printPrompt(PromptMessage.CREATE, true);
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
                reportConsole.printPrompt(PromptMessage.REPORT, true);
                return;
            case EXIT:
                try {
                    ConsoleService.saveAllInventoryData();
                    ConsoleService.saveAllInventoryDataAsJSON();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                return;
            case HARDEXIT:
                System.exit(0);
                return;
            case BAD_COMMAND:
                System.out.println("Bad command! Please enter a valid command, or enter 'Help' to view a list of all commands.");
                printPrompt(PromptMessage.STANDARD, true);
                return;
            case HELP:
                printHelpCommand();
                printPrompt(PromptMessage.STANDARD, true);
                return;
            default:
                run(Command.BAD_COMMAND, null);
                return;
        }
    }

    private void printReadList() {
        for (String s : AbstractConsole.getListOfProducts()) {
            System.out.println(s);
        }
    }


}
