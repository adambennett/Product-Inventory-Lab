package models;

public class Figurine extends Product {
    private int height;
    private int weight;
    private int width;
    private int id;
    private double price;
    private String color;

    public Figurine() { }

    public Figurine(String name) {
        this.name = name;
    }

    public Figurine(String name, int height, int weight, int width, double price, String color, int id) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.width = width;
        this.price = price;
        this.color = color;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

