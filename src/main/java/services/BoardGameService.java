package services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.BoardGame;
import models.Inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoardGameService {
    private ArrayList<BoardGame> inventory = new ArrayList<>();

    public BoardGame create(String gameName, String manufacturer, int ageMinimum, int ageMax, int avgPlayingTime, int id) {
        BoardGame createdGame = new BoardGame(gameName, manufacturer, ageMinimum, ageMax, avgPlayingTime, id);
        inventory.add(createdGame);
        return createdGame;
    }

    public static void loadJSONData(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        String fullFile = "/Users/Inventory/BoardGames/" + fileName + ".json";
        try {
            Inventory.loadProductsBG(objectMapper.readValue(new File(fullFile), new TypeReference<ArrayList<BoardGame>>(){}));
        } catch (JsonParseException e) {} catch (JsonMappingException e) {} catch (IOException e) {}
    }

    public static void loadJSONData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Inventory.loadProductsBG(objectMapper.readValue(new File("/Users/Inventory/BoardGames/BoardGames.json"), new TypeReference<ArrayList<BoardGame>>(){}));
            Inventory.loadRollBackBG(objectMapper.readValue(new File("/Users/Inventory/BoardGames/BoardGames.json"), new TypeReference<ArrayList<BoardGame>>(){}));
        } catch (JsonParseException e) {} catch (JsonMappingException e) {} catch (IOException e) {}
    }

    public static void loadData(){
        Inventory.clear();
        String csvFile = "/Users/Inventory/InventoryCSV/Inventory.csv";
        String line = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] game = line.split(csvSplitBy);
                int id = -1;
                int playtime = 60;
                String name = "";
                int ageMin = 13;
                int ageMax = 99;
                String type = "";
                try {
                    type = game[0];
                    id = Integer.parseInt(game[1]);
                    playtime = Integer.parseInt(game[2]);
                    name = game[3];
                    ageMin = Integer.parseInt(game[4]);
                    ageMax = Integer.parseInt(game[5]);
                } catch (NumberFormatException|IndexOutOfBoundsException e) {}

                // (5)
                if (!name.equals("") && type.equals("BOARD")) {
                    Inventory.add(new BoardGame(name, "", ageMin, ageMax, playtime, id));
                }
            }
        } catch (IOException|NumberFormatException e) {}
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
