package models;

public class BoardGame extends Product {
    private String manufacturer;
    private int ageMinimum;
    private int ageMax;
    private int avgPlayingTime;
    private int id;

    public BoardGame() { }

    public BoardGame(String gameName, String manufacturer, int avgPlayingTime, int id) {
        this.name = gameName;
        this.manufacturer = manufacturer;
        this.avgPlayingTime = avgPlayingTime;
        this.ageMinimum = 0;
        this.ageMax = 99;
        this.id = id;
    }

    public BoardGame(String gameName, String manufacturer, int ageMinimum, int ageMax, int avgPlayingTime, int id) {
        this.name = gameName;
        this.manufacturer = manufacturer;
        this.ageMinimum = ageMinimum;
        this.ageMax = ageMax;
        this.avgPlayingTime = avgPlayingTime;
        this.id = id;
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
