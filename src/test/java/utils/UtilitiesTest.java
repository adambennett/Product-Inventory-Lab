package utils;

import io.AbstractConsole;
import io.MainConsole;
import models.BoardGame;
import models.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class UtilitiesTest
{
    @Test
    void getListOfProducts() {
        Inventory.clear();
        Inventory.add(new BoardGame("Carcassone"));
        Inventory.add(new BoardGame("Lords of Waterdeep"));
        Inventory.add(new BoardGame("Monopoly"));
        Inventory.add(new BoardGame("Sorry"));
        Integer expected = 4;
        Integer actual = Utilities.getListOfProducts().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void capFirstLetter() {
        String input = "oooo";
        String expected = "Oooo";
        String actual = Utilities.capFirstLetter(input);
        Assertions.assertEquals(expected, actual);
    }
}