package io;

import models.BoardGame;
import models.Figurine;
import models.Inventory;

import java.util.ArrayList;

public class CreateConsole extends AbstractConsole {

    @Override
    protected void initializeCommands() {
        consoleCommands.put("figure", Command.FIGURINE);
        consoleCommands.put("game", Command.BOARDGAME);
        consoleCommands.put("return", Command.RETURN);
        consoleCommands.put("help", Command.HELP);
    }

    @Override
    public void runOnInvalidCommand(ArrayList<String> originalArgs) {
        processCommand(Command.RETURN, originalArgs);
    }

    public Boolean runFigurine(ArrayList<String> args) {
        Figurine figure;
        for (int i = 0; i < args.size(); i++) {
            try {
                Integer numChecker = Integer.parseInt(args.get(i));
                args.remove(i);
            } catch (NumberFormatException e) {}
        }
        if (args.size() > 1) {
            figure = new Figurine(args.get(0), args.get(1));
            Inventory.add(figure);
            return true;
        } else if (args.size() > 0) {
            figure = new Figurine(args.get(0));
            Inventory.add(figure);
            return true;
        } else {
            return false;
        }
    }

    public Boolean runBoardGame(ArrayList<String> args) {
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
                Inventory.add(game);
                return true;
            }
        }
        return false;
    }

    public void afterBoardGame(Boolean addedBGProperly) {
        if (addedBGProperly) {
            printPrompt(PromptMessage.CREATE_WITH_PROD, true);
        } else {
            System.out.println("Improper arguments. Expecting BOARDGAME command to be followed by some combination of: game name, age minimum, age maximum, and average playing time.");
            printPrompt(PromptMessage.CREATE, true);
        }
    }

    public void afterFigurine(Boolean addedFigProperly) {
        if (addedFigProperly) {
            printPrompt(PromptMessage.CREATE_WITH_PROD, true);
        } else {
            System.out.println("Improper arguments. Expecting FIGURINE command to be followed by some combination of: figurine name and figurine color.");
            printPrompt(PromptMessage.CREATE, true);
        }
    }

    @Override
    public void processCommand(Command cmd, ArrayList<String> args) {
        switch (cmd) {
            case FIGURINE:
                Boolean addedFigProperly = runFigurine(args);
                afterFigurine(addedFigProperly);
                return;
            case BOARDGAME:
                Boolean addedBGProperly = runBoardGame(args);
                afterBoardGame(addedBGProperly);
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
