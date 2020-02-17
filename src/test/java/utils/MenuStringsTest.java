package utils;

import io.AbstractConsole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class MenuStringsTest {

    @Test
    void loadStrings() {
        MenuStrings.loadStrings();
        Assertions.assertNotNull(MenuStrings.standard);
    }

    @Test
    void getStringFromPromptType() {
        MenuStrings.loadStrings();
        String expected = MenuStrings.standard;
        String actual = MenuStrings.getStringFromPromptType(AbstractConsole.PromptMessage.STANDARD);
        Assertions.assertEquals(expected, actual);
    }
}