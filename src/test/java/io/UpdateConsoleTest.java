package io;

import models.BoardGame;
import models.Figurine;
import models.Inventory;
import models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.logging.Logger;

class UpdateConsoleTest {

    @Test
    void TestAddA() {
        Logger logger = Logger.getGlobal();
        Inventory.clear();

        BoardGame game = new BoardGame("Lords of Waterdeep", "", 13, 99, 120, 1);
        Figurine fig = new Figurine("Gandalf", "White", 2);

        // Test 1 - test add by product name
        Inventory.add(game);
        Inventory.add(fig);
        ArrayList<String> mockUserInput = new ArrayList<>();
        mockUserInput.add("lords");
        mockUserInput.add("of");
        mockUserInput.add("waterdeep");
        mockUserInput.add("10");

        UpdateConsole updater = new UpdateConsole();
        updater.runAdd(mockUserInput);
        Assertions.assertTrue(Inventory.size() > 2);
    }

    @Test
    void TestAddB() {
        Logger logger = Logger.getGlobal();
        Inventory.clear();
        BoardGame game = new BoardGame("Lords of Waterdeep", "", 13, 99, 120, 1);
        Figurine fig = new Figurine("Gandalf", "White", 2);
        ArrayList<String> mockUserInput = new ArrayList<>();
        UpdateConsole updater;

        // Test 2 - test add by product ID
        Inventory.clear();
        mockUserInput.clear();
        Inventory.add(game);
        Inventory.add(fig);
        mockUserInput.add("1");
        mockUserInput.add("2");
        updater = new UpdateConsole();
        updater.runAdd(mockUserInput);
        Assertions.assertTrue(Inventory.size() > 2);

    }

    @Test
    void TestAddC() {
        Logger logger = Logger.getGlobal();
        Inventory.clear();
        BoardGame game = new BoardGame("Lords of Waterdeep", "", 13, 99, 120, 1);
        Figurine fig = new Figurine("Gandalf", "White", 2);
        ArrayList<String> mockUserInput = new ArrayList<>();
        UpdateConsole updater;

        // Test 3 - test add with bad inputs mixed with good inputs
        // should ideally add 40 new 'Lords of Waterdeep' board games (adding by valid product ID of 1)
        // and also add 40 new Gandalf figurines (adding by a valid product name)
        // 4, 20, 15 are all invalid product IDs so they should be summed to get the 40 value
        Inventory.clear();
        mockUserInput.clear();
        Inventory.add(game);
        Inventory.add(fig);
        mockUserInput.add("monkey bunny");
        mockUserInput.add("4");
        mockUserInput.add("20");
        mockUserInput.add("15");
        mockUserInput.add("lords");
        mockUserInput.add("of");
        mockUserInput.add("carcassone");
        mockUserInput.add("Gandalf");
        mockUserInput.add("1");
        updater = new UpdateConsole();
        updater.runAdd(mockUserInput);
        Assertions.assertTrue(Inventory.size() == 80);
    }

    @Test
    void TestAddD() {
        Logger logger = Logger.getGlobal();
        Inventory.clear();
        BoardGame game = new BoardGame("Carcassone", "", 13, 99, 120, 1);
        Figurine fig = new Figurine("Gandalf", "White", 2);
        ArrayList<String> mockUserInput = new ArrayList<>();
        UpdateConsole updater;

        // Test 4 - test add with bad inputs mixed with good inputs
        // same as test 3 but without any good IDs to add from
        // this is to see if we can at least find a product with a name consisting of one single word, when given a slew of bad input strings
        Inventory.clear();
        mockUserInput.clear();
        Inventory.add(game);
        Inventory.add(fig);
        mockUserInput.add("monkey bunny");
        mockUserInput.add("5");
        mockUserInput.add("lords");
        mockUserInput.add("of");
        mockUserInput.add("carcassone");
        mockUserInput.add("Gandalf");
        updater = new UpdateConsole();
        updater.runAdd(mockUserInput);
        Assertions.assertTrue(Inventory.size() == 12);
    }


    @Test
    void TestAddE() {
        Logger logger = Logger.getGlobal();
        Inventory.clear();

        BoardGame game = new BoardGame("Lords of Waterdeep", "", 13, 99, 120, 1);
        Figurine fig = new Figurine("Gandalf", "White", 2);

        // Test 5 - test add with 'true' argument indicating the user wishes to perform an exact copy of any Figurines (color is copied)
        Inventory.add(fig);
        ArrayList<String> mockUserInput = new ArrayList<>();
        mockUserInput.add("Gandalf");
        mockUserInput.add("tRuE");
        mockUserInput.add("4");
        UpdateConsole updater = new UpdateConsole();
        updater.runAdd(mockUserInput);
        boolean allGandalfsAreWhite = true;
        for (Product p : Inventory.getProducts()) {
            if (p instanceof Figurine && p.getName().equals("Gandalf")) {
                if (!((Figurine) p).getColor().toLowerCase().equals("white")) {
                    allGandalfsAreWhite = false;
                    break;
                }
            }
        }
        Assertions.assertTrue(allGandalfsAreWhite);
    }

    @Test
    void TestAddF() {
        Logger logger = Logger.getGlobal();
        Inventory.clear();

        BoardGame game = new BoardGame("Lords of Waterdeep", "", 13, 99, 120, 1);
        Figurine fig = new Figurine("Gandalf", "White", 2);

        // Test 6 - same as above test but now without the true
        Inventory.add(fig);
        ArrayList<String> mockUserInput = new ArrayList<>();
        mockUserInput.add("Gandalf");
        mockUserInput.add("20");
        UpdateConsole updater = new UpdateConsole();
        updater.runAdd(mockUserInput);
        boolean allGandalfsAreWhite = true;
        for (Product p : Inventory.getProducts()) {
            if (p instanceof Figurine && p.getName().equals("Gandalf")) {
                if (!((Figurine) p).getColor().toLowerCase().equals("white")) {
                    allGandalfsAreWhite = false;
                    break;
                }
            }
        }
        Assertions.assertTrue(!allGandalfsAreWhite);
    }

}