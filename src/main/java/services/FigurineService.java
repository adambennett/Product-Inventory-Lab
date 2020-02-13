package services;

import models.BoardGame;
import models.Figurine;

import java.util.ArrayList;

public class FigurineService {
    private static int nextId = 1;
    private ArrayList<Figurine> inventory = new ArrayList<>();

    public Figurine create(String name, int height, int weight, int width, double price, String color, int id) {
        Figurine createdGame = new Figurine(name, height, weight, width, price, color, id);
        inventory.add(createdGame);
        return createdGame;
    }

    //read
    public Figurine findFigure(int id) {
        for (Figurine s : inventory) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

    //read all
    public Figurine[] findAll() {
        Object[] sneaks = this.inventory.toArray();
        int newSize = this.inventory.size();
        if (newSize < 1) { newSize = 1; }
        Figurine[] toRet = new Figurine[newSize];
        int index = 0;
        for (Object o : sneaks) {
            if (o instanceof Figurine) {
                toRet[index] = (Figurine) o;
                index++;
            }
        }
        return toRet;
    }

    //delete
    public boolean delete(int id) {
        for (int i = 0; i < this.inventory.size(); i++) {
            if (this.inventory.get(i).getId()==id) {
                this.inventory.remove(i);
                return true;
            }
        }
        return false;
    }
}