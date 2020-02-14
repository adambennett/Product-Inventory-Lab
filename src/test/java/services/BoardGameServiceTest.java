package services;

import models.BoardGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardGameServiceTest {

    @Test
    public void createTest(){
        String expectedName = "Lords of Waterdeep";
        String expectedManufacturer = "Wizards of the Coast";
        int expectedID = 55;
        int expectedAgeMin = 13;
        int expectedAgeMax = 99;
        int expectedPlaytime = 60;

        BoardGameService bgService = new BoardGameService();
        BoardGame game = bgService.create(expectedName, expectedManufacturer, expectedAgeMin, expectedAgeMax, expectedPlaytime, expectedID);


        String actualName = game.getName();
        int actualID = game.getId();
        int actualAgeMin = game.getAgeMinimum();
        int actualAgeMax = game.getAgeMax();
        int actualPlaytime = game.getAvgPlayingTime();


        Assertions.assertEquals(Integer.class.getName(), new Integer(actualID).getClass().getName());
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedAgeMin, actualAgeMin);
        Assertions.assertEquals(expectedAgeMax, actualAgeMax);
        Assertions.assertEquals(expectedPlaytime, actualPlaytime);
        Assertions.assertEquals(expectedID, actualID);

    }

    @Test
    void findGame() {
        BoardGameService bgService = new BoardGameService();
        BoardGame game = bgService.create("Lords of Waterdeep", "Wizards of the Coast", 13, 99, 60, 1);

        Assertions.assertEquals(game, bgService.findGame(game.getId()));
    }

    @Test
    void findAll() {
        BoardGameService bgService = new BoardGameService();
        Assertions.assertNotNull(bgService.findAll());
    }

    @Test
    void delete() {
        BoardGameService bgService = new BoardGameService();
        BoardGame game = bgService.create("Lords of Waterdeep", "Wizards of the Coast", 13, 99, 60, 1);

        Assertions.assertTrue(bgService.delete(1));
    }
}