package io;

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
    }

    @Override
    public void runOnBadCommand(ArrayList<String> originalArgs)  {
        run(Command.RETURN, originalArgs);
    }

    @Override
    public void run(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case LIST:
                printPrompt(PromptMessage.LIST, true);
                return;
            case ADD:
                //TODO
                return;
            case SUB:
                //TODO
                return;
            case SET:
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
