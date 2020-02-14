package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardGameTest {

    private BoardGame testGame;

    @Test
    public void setNameTest() {
        // given (1)
        String expected = "Lords of Waterdeep";

        // when (2)
        testGame = new BoardGame();
        testGame.setName(expected);

        // then (3)
        Assertions.assertEquals(expected, testGame.getName());
    }

    @Test
    public void constructorTest(){
        String expectedName = "Lords of Waterdeep";
        String expectedManufacturer = "Wizards of the Coast";
        int expectedID = 55;
        int expectedAgeMin = 13;
        int expectedAgeMax = 99;
        int expectedPlaytime = 60;

       BoardGame game = new BoardGame(expectedName, expectedManufacturer, expectedAgeMin, expectedAgeMax, expectedPlaytime, expectedID);

        String actualName = game.getName();
        int actualID = game.getId();
        int actualAgeMin = game.getAgeMinimum();
        int actualAgeMax = game.getAgeMax();
        int actualPlaytime = game.getAvgPlayingTime();

        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedAgeMin, actualAgeMin);
        Assertions.assertEquals(expectedAgeMax, actualAgeMax);
        Assertions.assertEquals(expectedPlaytime, actualPlaytime);
        Assertions.assertEquals(expectedID, actualID);
    }

}