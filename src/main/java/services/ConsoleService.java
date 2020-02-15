package services;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import io.App;
import models.BoardGame;
import models.Figurine;
import models.Inventory;
import models.Product;
import utils.CSVUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private static int nextId = 0;

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

    public static void saveAllInventoryData() throws IOException {
        String csvFile = "/Users/abennett/Desktop/Inventory.csv";
        FileWriter writer = new FileWriter(csvFile); //(1)

        CSVUtils.writeLine(writer, new ArrayList<String>(Arrays.asList(String.valueOf(nextId))));  // (2)

        for (Product s : Inventory.getProducts()) {
            List<String> list = new ArrayList<>();
            if (s instanceof BoardGame) {
                BoardGame ga = (BoardGame)s;
                list.add("" + ga.getId());
                list.add("" + ga.getAvgPlayingTime());
                list.add(s.getName());
                list.add("" + ga.getAgeMinimum());
                list.add("" + ga.getAgeMax());
            } else if (s instanceof Figurine) {
                Figurine fig = (Figurine)s;
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
}
