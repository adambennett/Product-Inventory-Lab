package models;

import java.util.concurrent.ThreadLocalRandom;

public class BoardGame extends Product {
    private String manufacturer;
    private int ageMinimum;
    private int ageMax;
    private int avgPlayingTime;
    private int id;

    public BoardGame() {
        this("Unknown", "Unknown", 0, 99, 60);
    }

    public BoardGame(String gameName) {
        this(gameName, "Unknown", 0, 99,60);
    }

    public BoardGame(String gameName, String manufacturer) {
        this(gameName, manufacturer, 0, 99, 60);
    }

    public BoardGame(String gameName, String manufacturer, int avgPlayingTime) {
        this(gameName, manufacturer, 0, 99, avgPlayingTime);
    }

    public BoardGame(String gameName, String manufacturer, int avgPlayingTime, int ageMax) {
        this(gameName, manufacturer, 0, ageMax, avgPlayingTime);
    }

    public BoardGame(String gameName, String manufacturer, int ageMinimum, int ageMax, int avgPlayingTime) {
        this(gameName, manufacturer, ageMinimum, ageMax, avgPlayingTime, generateID());
    }

    public BoardGame(String gameName, String manufacturer, int ageMinimum, int ageMax, int avgPlayingTime, int id) {
        this.name = gameName;
        this.manufacturer = manufacturer;
        this.ageMinimum = ageMinimum;
        this.ageMax = ageMax;
        this.avgPlayingTime = avgPlayingTime;
        this.id = id;
        this.type = "Board Game";
    }

    private static int generateID() {
        return ThreadLocalRandom.current().nextInt(0, 1000) + ThreadLocalRandom.current().nextInt(ThreadLocalRandom.current().nextInt(1, 5), ThreadLocalRandom.current().nextInt(10, 3000));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getAgeMinimum() {
        return ageMinimum;
    }

    public void setAgeMinimum(int ageMinimum) {
        this.ageMinimum = ageMinimum;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getAvgPlayingTime() {
        return avgPlayingTime;
    }

    public void setAvgPlayingTime(int avgPlayingTime) {
        this.avgPlayingTime = avgPlayingTime;
    }
}
