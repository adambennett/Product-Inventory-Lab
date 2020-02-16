package utils;

import models.BoardGame;
import models.Figurine;
import models.Inventory;
import models.Product;

import java.util.ArrayList;

public class Utilities {

    private static String lastCommand = "";

    public static String getStringOfProducts(int lengthToCheck) {
        ArrayList<String> uniques = new ArrayList<>();
        String toRet = "";
        for (Product p : Inventory.getProducts()) {
            String prefix = "***";
            String suffix = "***\n";
            String space = " ";
            String productInfo =  "[" + p.getId() + "]: " + capFirstLetter(p.getName());

            if (p instanceof Figurine) {
                String color = capFirstLetter(((Figurine) p).getColor());
                productInfo += " (" +  color + ")";
            }

            int reductionCounter = productInfo.length();
            while (productInfo.length() + suffix.length() + prefix.length() > lengthToCheck) {
                productInfo = productInfo.substring(0, reductionCounter);
                reductionCounter--;
            }
            boolean flipSpaceToAdd = false;
            while (productInfo.length() + suffix.length() + prefix.length() < lengthToCheck + 1) {
                if (flipSpaceToAdd) { prefix += space; }
                else { suffix = space + suffix; }
                flipSpaceToAdd = !flipSpaceToAdd;
            }
            productInfo = prefix + productInfo + suffix;
            if (!uniques.contains(productInfo)) {
                uniques.add(productInfo);
            }
        }
        for (String s : uniques) {
            toRet += s;
        }

        return toRet;
    }


    public static ArrayList<String> getListOfProducts() {
        ArrayList<String> uniques = new ArrayList<>();
        for (Product p : Inventory.getProducts()) {
            String s = "[" + Inventory.amtOfProductByName(p) + "]: " + capFirstLetter(p.getName());
            if (p instanceof BoardGame) {
                BoardGame bg = (BoardGame)p;
                s += " (Ages " + bg.getAgeMinimum() + " to " + bg.getAgeMax() + ", Playtime: " + bg.getAvgPlayingTime() + " minutes)";
            }
            else if (p instanceof Figurine) {
                Figurine fig = (Figurine)p;
                String color = capFirstLetter(fig.getColor());
                s += " (" + color + ")";
            }
            if (!uniques.contains(s)) {
                uniques.add(s);
            }
        }
        return uniques;
    }

    public static String getLastCommand() {
        return lastCommand;
    }

    public static void setLastCommand(String newLastCommand) {
        lastCommand = newLastCommand;
    }

    public static String capFirstLetter(String s) {
        String color = s.toLowerCase();
        color = color.substring(0, 1).toUpperCase() + color.substring(1, color.length());
        return color;
    }
}
