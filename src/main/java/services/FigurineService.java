package services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Figurine;
import models.Inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FigurineService {
    private ArrayList<Figurine> inventory = new ArrayList<>();

    public Figurine create(String name, String color, int id) {
        Figurine createdGame = new Figurine(name, color, id);
        inventory.add(createdGame);
        return createdGame;
    }

    public static void loadJSONData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Inventory.loadProductsFig((objectMapper.readValue(new File("/Users/abennett/Desktop/Figurines.json"), new TypeReference<ArrayList<Figurine>>(){})));
            Inventory.loadRollBackFig((objectMapper.readValue(new File("/Users/abennett/Desktop/Figurines.json"), new TypeReference<ArrayList<Figurine>>(){})));
        } catch (JsonParseException e) {} catch (JsonMappingException e) {} catch (IOException e) {}
    }

    public static void loadData(){
        // (1)
        String csvFile = "/Users/abennett/Desktop/Inventory.csv";
        String line = "";
        String csvSplitBy = ",";

        // (2)
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] game = line.split(csvSplitBy);
                String type = "";
                int id = -1;
                String name = "";
                String color = "";
                try {
                    type = game[0];
                    id = Integer.parseInt(game[1]);
                    color = game[2];
                    name = game[3];
                } catch (NumberFormatException|IndexOutOfBoundsException e) {}

                // (5)
                if (!name.equals("") && type.equals("FIG")) {
                    Inventory.add(new Figurine(name, color, id));
                }
            }
        } catch (IOException|NumberFormatException e) {}
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
