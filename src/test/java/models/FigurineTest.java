package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FigurineTest {

    private Figurine figure;

    @Test
    public void setNameTest() {
        // given (1)
        String expected = "Gandalf";

        // when (2)
        figure = new Figurine();
        figure.setName(expected);

        // then (3)
        Assertions.assertEquals(expected, figure.getName());
    }

    @Test // (1)
    public void constructorTest(){

        String expectedName = "Gandalf";
        double expectedPrice = 80.00;
        int expectedHeight = 1;
        int expectedWeight = 2;
        int expectedWidth = 3;
        String expectedColor = "White";
        int expectedID = 55;

        // (3)
        Figurine figure = new Figurine(expectedName, expectedHeight, expectedWeight, expectedWidth, expectedPrice, expectedColor, expectedID);

        // (4)
        Assertions.assertEquals(expectedID, figure.getId());
        Assertions.assertEquals(expectedName, figure.getName());
        Assertions.assertEquals(expectedHeight, figure.getHeight());
        Assertions.assertEquals(expectedWeight, figure.getWeight());
        Assertions.assertEquals(expectedWidth, figure.getWidth());
        Assertions.assertEquals(expectedColor, figure.getColor());
        Assertions.assertEquals(expectedPrice, figure.getPrice());
    }

}