package services;

import io.App;
import models.BoardGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoardGameService {
    private static int nextId = 1;
    private ArrayList<BoardGame> inventory = new ArrayList<>();

    public BoardGame create(String gameName, String manufacturer, int ageMinimum, int ageMax, int avgPlayingTime, int id) {
        BoardGame createdGame = new BoardGame(gameName, manufacturer, ageMinimum, ageMax, avgPlayingTime, id);
        inventory.add(createdGame);
        return createdGame;
    }

    private void loadData(){
        // (1)
        String csvFile = "/Users/Adam/Desktop/Inventory.csv";
        String line = "";
        String csvSplitBy = ",";

        // (2)
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            nextId = Integer.parseInt(br.readLine());  // (3)

            while ((line = br.readLine()) != null) {
                // split line with comma
                String[] game = line.split(csvSplitBy);

                // (4)
                int playtime = Integer.parseInt(game[0]);
                String name = game[1];
                int ageMin = Integer.parseInt(game[2]);
                int ageMax = Integer.parseInt(game[3]);

                // (5)
                App.inventory.add(new BoardGame(name, "", ageMin, ageMax, playtime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
