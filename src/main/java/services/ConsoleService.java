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
import java.util.logging.Logger;

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

    public static void print(String output, Object... args) {
        System.out.printf(output, args);
    }

    public static void println(String output, Object... args) {
        print(output + "\n", args);
    }

    public static void prln(String print) {
        System.out.println(print);
    }

    public static void saveAllInventoryDataAsJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File("/Users/abennett/Desktop/Figurines.json"), Inventory.getFigurines());
            writer.writeValue(new File("/Users/abennett/Desktop/BoardGames.json"), Inventory.getBoardGames());
            Logger.getGlobal().info("Saved Inventory JSON data successfully.");
        }  catch (IOException e) {
            Logger.getGlobal().info("IOException!! Did NOT save Inventory JSON data!");
        }
    }

    public static void saveAllInventoryData() throws IOException {
        String csvFile = "/Users/abennett/Desktop/Inventory.csv";
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
    }

    public static String capFirstLetter(String s) {
        String color = s.toLowerCase();
        color = color.substring(0, 1).toUpperCase() + color.substring(1, color.length());
        return color;
    }
}
