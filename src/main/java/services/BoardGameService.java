package services;

import models.BoardGame;

import java.util.ArrayList;

public class BoardGameService {
    private static int nextId = 1;
    private ArrayList<BoardGame> inventory = new ArrayList<>();

    public BoardGame create(String gameName, String manufacturer, int ageMinimum, int ageMax, int avgPlayingTime, int id) {
        BoardGame createdGame = new BoardGame(gameName, manufacturer, ageMinimum, ageMax, avgPlayingTime, id);
        inventory.add(createdGame);
        return createdGame;
    }

    //read
    public BoardGame findGame(int id) {
        for (BoardGame s : inventory) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

    //read all
    public BoardGame[] findAll() {
        Object[] sneaks = this.inventory.toArray();
        int newSize = this.inventory.size();
        if (newSize < 1) { newSize = 1; }
        BoardGame[] toRet = new BoardGame[newSize];
        int index = 0;
        for (Object o : sneaks) {
            if (o instanceof BoardGame) {
                toRet[index] = (BoardGame) o;
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
