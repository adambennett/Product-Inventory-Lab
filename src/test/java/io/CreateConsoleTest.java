package io;

import models.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CreateConsoleTest {

    @Test
    void runFigurine() {
        Inventory.clear();
        CreateConsole console = new CreateConsole();
        ArrayList<String> args = new ArrayList<>();
        args.add("Gandalf");
        args.add("White");
        console.runFigurine(args);
        Integer expected = 1;
        Integer actual = Inventory.getFigurines().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void runBoardGame() {
        Inventory.clear();
        CreateConsole console = new CreateConsole();
        ArrayList<String> args = new ArrayList<>();
        args.add("Carcassone");
        console.runBoardGame(args);
        Integer expected = 1;
        Integer actual = Inventory.getBoardGames().size();
        Assertions.assertEquals(expected, actual);
    }
}