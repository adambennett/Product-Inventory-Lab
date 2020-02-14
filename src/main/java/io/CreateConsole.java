package io;

import models.BoardGame;

import java.util.ArrayList;

public class CreateConsole extends AbstractConsole {

    @Override
    protected void setupCommands() {
        commandMap.put("figure", Command.FIGURINE);
        commandMap.put("game", Command.BOARDGAME);
        commandMap.put("exit", Command.EXIT);
        commandMap.put("help", Command.HELP);
        commandMap.put("return", Command.RETURN);
    }

    @Override
    public void runOnBadCommand(ArrayList<String> originalArgs) {
        run(Command.RETURN, originalArgs);
    }

    @Override
    public void run(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case FIGURINE:
                return;
            case BOARDGAME:
                if (args.size() > 0) {
                    ArrayList<Integer> nums = new ArrayList<>();
                    ArrayList<String> strings = new ArrayList<>();
                    for (String s : args) {
                        try {
                            Integer i = Integer.parseInt(s);
                            nums.add(i);
                        } catch (NumberFormatException e) {
                            strings.add(s);
                        }
                    }

                    String name = "";
                    if (strings.size() > 1) {
                        name = "";
                        for (int i = 0; i < strings.size(); i++) {
                            name += strings.get(i) + " ";
                        }
                    }
                    else { name = strings.get(0); }

                    if (!name.equals(""))
                    {
                        BoardGame game;
                        if (nums.size() > 2) {
                            game = new BoardGame(name, "Unknown", nums.get(0), nums.get(1), nums.get(2));
                        } else if (nums.size() > 1) {
                            game = new BoardGame(name, "Unknown", nums.get(0), nums.get(1));
                        } else if (nums.size() > 0) {
                            game = new BoardGame(name, "Unknown", nums.get(0));
                        } else {
                            game = new BoardGame(name);
                        }
                        App.inventory.add(game);
                        printPrompt(PromptMessage.STANDARD, true);
                        return;
                    }
                }
                System.out.println("Improper arguments. Expecting BOARDGAME command to be followed by some combination of: game name, age minimum, age maximum, and average playing time.");
                printPrompt(PromptMessage.STANDARD, true);
                return;
            case EXIT:
                System.exit(0);
                return;
            case HELP:
                printHelpCommand();
                printPrompt(PromptMessage.STANDARD, true);
                return;
            case RETURN:
                Console console = new Console();
                console.printPrompt(PromptMessage.STANDARD, true);
                return;
        }
    }
}
