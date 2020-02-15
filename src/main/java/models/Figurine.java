package models;

import java.util.concurrent.ThreadLocalRandom;

public class Figurine extends Product {
    private String color;

    public Figurine() {
        this("Unknown Name");
    }

    public Figurine(String name) {
        this(name, getRandomColor());
    }

    public Figurine(String name, String color) {
        this(name, color, generateID());
    }

    public Figurine(String name, String color, int id) {
        this.name = name;
        this.color = color;
        this.id = id;
        this.type = "Figurine";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private static String getRandomColor() {
        String color = "White";
        int roll = ThreadLocalRandom.current().nextInt(1, 11);
        switch (roll) {
            case 1:
                color = "Yellow";
                return color;
            case 2:
                color = "Black";
                return color;
            case 3:
                color = "Green";
                return color;
            case 4:
                color = "Purple";
                return color;
            case 5:
                color = "Red";
                return color;
            case 6:
                color = "Blue";
                return color;
            case 7:
                color = "Silver";
                return color;
            case 8:
                color = "Gold";
                return color;
            case 9:
                color = "Brown";
                return color;
            case 10:
                color = "Orange";
                return color;
            case 11:
                color = "Magenta";
                return color;
            default:
                return color;

        }
    }
}

