package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void copyAsFigurine() {
        //TODO
    }

    @Test
    void copyAsBoardGame() {
        //TODO
    }

    @Test
    void generateID() {
        Integer newID = Product.generateID();
        Assertions.assertTrue(newID > -1);
    }
}