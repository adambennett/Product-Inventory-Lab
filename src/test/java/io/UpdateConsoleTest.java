package io;

import models.BoardGame;
import models.Figurine;
import models.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.logging.Logger;

class UpdateConsoleTest {
    @Test
    void runAdd() {
        Logger logger = Logger.getGlobal();
        Inventory.clear();
        BoardGame game = new BoardGame("Lords of Waterdeep", "", 13, 99, 120);
        Figurine fig = new Figurine("Gandalf", "White");
        Inventory.add(game);
        Inventory.add(fig);
        ArrayList<String> mockUserInput = new ArrayList<>();
        mockUserInput.add("lords");
        mockUserInput.add("of");
        mockUserInput.add("waterdeep");
        mockUserInput.add("10");

        UpdateConsole updater = new UpdateConsole();
        updater.runAdd(mockUserInput);
        logger.info("Inventory size: " + Inventory.getProducts().size());
        Assertions.assertTrue(Inventory.getProducts().size() > 2);

    }

}