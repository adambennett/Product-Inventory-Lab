package services;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import models.BoardGame;
import models.Figurine;
import models.Inventory;
import models.Product;
import utils.CSVUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {
    public static Float getFloatInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Util.println(prompt);
        String userInput = scanner.nextLine();
        try {
            return Float.parseFloat(userInput);
        } catch (NumberFormatException e) { e.printStackTrace(); }
        return 0.0f;
    }


    public static Double getDoubleInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Util.println(prompt);
        String userInput = scanner.nextLine();
        try {
            return Double.parseDouble(userInput);
        } catch (NumberFormatException e) { e.printStackTrace(); }
        return 0.0;
    }

    public static Integer getIntegerInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Util.println(prompt);
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) { e.printStackTrace(); }
        return 0;
    }

    public static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Util.println(prompt);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static void saveAllInventoryDataAsJSON(String boardGameFileName, String figurineFileName) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        String fullFigurineFile = "/Users/Inventory/Figures/" + figurineFileName + ".json";
        String fullBoardGameFile = "/Users/Inventory/BoardGames/" + boardGameFileName + ".json";
        if (!figurineFileName.equals("")) {
            try {
                writer.writeValue(new File(fullFigurineFile), Inventory.getFigurines());
            } catch (IOException e) {}
        }
        if (!boardGameFileName.equals("")) {
            try {
                writer.writeValue(new File(fullBoardGameFile), Inventory.getBoardGames());
            } catch (IOException e) {}
        }
    }

    public static void saveAllInventoryDataAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File("/Users/Inventory/Figures/Figurines.json"), Inventory.getFigurines());
        } catch (IOException e) {}
        try { writer.writeValue(new File("/Users/Inventory/BoardGames/BoardGames.json"), Inventory.getBoardGames());
        }  catch (IOException e) {}
    }

    public static void saveAllInventoryData() {
        try {
            String csvFile = "/Users/Inventory/InventoryCSV/Inventory.csv";
            FileWriter writer = new FileWriter(csvFile); //(1)
            for (Product s : Inventory.getProducts()) {
                List<String> list = new ArrayList<>();
                if (s instanceof BoardGame) {
                    BoardGame ga = (BoardGame)s;
                    list.add("BOARD");
                    list.add("" + ga.getId());
                    list.add("" + ga.getAvgPlayingTime());
                    list.add(s.getName());
                    list.add("" + ga.getAgeMinimum());
                    list.add("" + ga.getAgeMax());
                } else if (s instanceof Figurine) {
                    Figurine fig = (Figurine)s;
                    list.add("FIG");
                    list.add("" + fig.getId());
                    list.add(fig.getColor());
                    list.add(s.getName());
                }
                CSVUtils.writeLine(writer, list);  // (4)
            }

            // (5)
            writer.flush();
            writer.close();
        } catch (IOException e) {}
    }

}
