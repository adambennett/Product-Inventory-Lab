package io;

import java.util.ArrayList;

public class ReportConsole extends AbstractConsole {
    @Override
    protected void setupCommands() {
        commandMap.put("list", Command.LIST);
        commandMap.put("report", Command.REPORT);
        commandMap.put("return", Command.RETURN);
        commandMap.put("help", Command.HELP);
    }

    @Override
    public void runOnBadCommand(ArrayList<String> originalArgs)    {
        run(Command.RETURN, originalArgs);
    }

    @Override
    public void run(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case LIST:
                //TODO
                return;
            case REPORT:
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
