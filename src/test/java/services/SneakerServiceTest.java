package services;

import models.Sneaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SneakerServiceTest {

    @Test
    public void createTest(){

        // (1)
        String expectedName = "Stan Smith";
        String expectedBrand = "Adidas";
        String expectedSport = "Tennis";
        int expectedSize = 1;
        int expectedQty = 10;
        float expectedPrice = 80.00f;

        // (2)
        SneakerService sneakerService = new SneakerService();
        Sneaker testSneaker = sneakerService.create(expectedName, expectedBrand,
                expectedSport, expectedSize, expectedQty, expectedPrice);

        // (3)
        int actualId = testSneaker.getId();
        String actualName = testSneaker.getName();
        String actualBrand = testSneaker.getBrand();
        String actualSport = testSneaker.getSport();
        double actualSize = testSneaker.getSize();
        int actualQty = testSneaker.getQty();
        float actualPrice = testSneaker.getPrice();

        // (4)
        Assertions.assertEquals(Integer.class.getName(), new Integer(actualId).getClass().getName());
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedBrand, actualBrand);
        Assertions.assertEquals(expectedSport, actualSport);
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertEquals(expectedQty, actualQty);
        Assertions.assertEquals(expectedPrice, actualPrice);

    }


    @Test
    void findSneaker() {
        SneakerService sneakerService = new SneakerService();
        Sneaker testSneaker = sneakerService.create("Sneaker", "Nike", "Underwater Basketweaving", 1, 1, 1.0f);

        Assertions.assertEquals(testSneaker, sneakerService.findSneaker(testSneaker.getId()));
    }

    @Test
    void findAll() {
        SneakerService sneakerService = new SneakerService();
        Assertions.assertNotNull(sneakerService.findAll());
    }

    @Test
    void delete() {
        SneakerService sneakerService = new SneakerService();
        Sneaker testSneaker = sneakerService.create("Sneaker", "Nike", "Underwater Basketweaving", 1, 1, 1.0f);

        Assertions.assertTrue(sneakerService.delete(1));
    }
}