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
        Figurine testFig = figService.create(expectedName, expectedHeight, expectedWeight, expectedWidth, expectedPrice, expectedColor, expectedID);

        String actualName = testFig.getName();
        double actualPrice = testFig.getPrice();
        int actualHeight = testFig.getHeight();
        int actualWeight = testFig.getWeight();
        int actualWidth = testFig.getWidth();
        String actualColor = testFig.getColor();
        int actualID = testFig.getId();


        // (4)
        Assertions.assertEquals(Integer.class.getName(), new Integer(actualID).getClass().getName());
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedHeight, actualHeight);
        Assertions.assertEquals(expectedWeight, actualWeight);
        Assertions.assertEquals(expectedWidth, actualWidth);
        Assertions.assertEquals(expectedColor, actualColor);
        Assertions.assertEquals(expectedPrice, actualPrice);

    }


    @Test
    void findFigure() {
        FigurineService figService = new FigurineService();
        Figurine figure = figService.create("Gandalf", 0, 0, 0, 0, "White", 1);

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
        Figurine figure = figService.create("Gandalf", 0, 0, 0, 0, "White", 1);

        Assertions.assertTrue(figService.delete(1));
    }
}