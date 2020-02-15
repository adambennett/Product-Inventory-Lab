package services;

import models.Figurine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FigurineServiceTest {

    @Test
    public void createTest(){

        // (1)
        String expectedName = "Gandalf";
        double expectedPrice = 80.00;
        int expectedHeight = 1;
        int expectedWeight = 2;
        int expectedWidth = 3;
        String expectedColor = "White";
        int expectedID = 55;

        FigurineService figService = new FigurineService();
        Figurine testFig = figService.create(expectedName, expectedColor, expectedID);

        String actualName = testFig.getName();
        String actualColor = testFig.getColor();
        int actualID = testFig.getId();


        // (4)
        Assertions.assertEquals(Integer.class.getName(), new Integer(actualID).getClass().getName());
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedColor, actualColor);

    }


    @Test
    void findFigure() {
        FigurineService figService = new FigurineService();
        Figurine figure = figService.create("Gandalf", "White", 1);

        Assertions.assertEquals(figure, figService.findFigure(figure.getId()));
    }

    @Test
    void findAll() {
        FigurineService figService = new FigurineService();
        Assertions.assertNotNull(figService.findAll());
    }

    @Test
    void delete() {
        FigurineService figService = new FigurineService();
        Figurine figure = figService.create("Gandalf", "White", 1);

        Assertions.assertTrue(figService.delete(1));
    }
}