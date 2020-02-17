package models;

import java.util.concurrent.ThreadLocalRandom;

public class Product {

    protected String name;
    protected String type;
    protected int id;

    public Product() { }

    public Product(Product p) {
        this.name = p.getName();
        this.type = p.getType();
        this.id = generateID();
        if (p instanceof BoardGame) {
            ((BoardGame)this).setAgeMinimum(((BoardGame) p).getAgeMinimum());
            ((BoardGame)this).setAgeMax(((BoardGame) p).getAgeMax());
            ((BoardGame)this).setManufacturer(((BoardGame) p).getManufacturer());
            ((BoardGame)this).setAvgPlayingTime(((BoardGame) p).getAvgPlayingTime());
        } else if (p instanceof Figurine) {
            ((Figurine)this).setColor(((Figurine) p).getColor());
        }
    }

    public Figurine copyAsFigurine() {
        return copyAsFigurine(false);
    }

    public Figurine copyAsFigurine(boolean copyExact) {
        if (this instanceof Figurine) {
            if (copyExact) {
                Figurine figure = new Figurine(this.getName(), ((Figurine)this).getColor(), generateID());
                return figure;
            } else {
                Figurine figure = new Figurine(this.getName(), Figurine.getRandomColor(), generateID());
                return figure;
            }
        } else {
            return null;
        }
    }

    public BoardGame copyAsBoardGame() {
        if (this instanceof BoardGame) {
            BoardGame game = new BoardGame(this.getName(), ((BoardGame) this).getManufacturer(), ((BoardGame) this).getAgeMinimum(), ((BoardGame) this).getAgeMax(), ((BoardGame) this).getAvgPlayingTime(), generateID());
            return game;
        } else {
            return null;
        }
    }

    protected static int generateID() {
        return ThreadLocalRandom.current().nextInt(0, 1000) + ThreadLocalRandom.current().nextInt(ThreadLocalRandom.current().nextInt(1, 5), ThreadLocalRandom.current().nextInt(10, 3000));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
