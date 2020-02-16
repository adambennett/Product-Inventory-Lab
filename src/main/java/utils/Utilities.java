package utils;

import models.BoardGame;
import models.Figurine;
import models.Inventory;
import models.Product;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

    private static ArrayList<Product> generateRandomProduct(int amt) {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Product> toRet = new ArrayList<>();
        products.add(new BoardGame("Carcassone", "Hans im Glück", 7, 99, 45));
        products.add(new BoardGame("Lords of Waterdeep", "Wizards of the Coast", 12, 99, 120));
        products.add(new BoardGame("Monopoly", "Unknown", 8, 99, 180));
        products.add(new BoardGame("Sorry", "Basic Fun, Inc.", 6, 99, 30));
        products.add(new BoardGame("Betrayal at House on the Hill", "Avalon Hill Games, Inc.", 12, 99, 60));
        products.add(new BoardGame("Small World", "Days of Wonder", 8, 99, 80));
        products.add(new BoardGame("Stone Age", "Hans im Glück", 10, 99, 90));
        products.add(new BoardGame("Risk", "Unknown", 10, 99, 120));
        products.add(new BoardGame("Axis & Allies", "Milton Bradley", 12, 99, 180));
        products.add(new BoardGame("Spheres of Influence", "Warp Spawn Games", 0, 99, 60));
        products.add(new BoardGame("Gloomhaven", "Cephalofair Games", 12, 99, 120));
        products.add(new BoardGame("Pandemic", "Z-Man Games", 8, 99, 45));
        products.add(new BoardGame("Mage Knight", "WizKids", 14, 99, 240));
        products.add(new BoardGame("Power Grid", "2F-Spiele", 12, 99, 120));
        products.add(new BoardGame("Dominion", "Rio Grande Games", 13, 99, 30));
        products.add(new BoardGame("Raiders of the North Sea", "Garphill Games", 12, 99, 80));
        products.add(new BoardGame("Ticket to Ride", "Days of Wonder", 8, 99, 60));
        products.add(new BoardGame("Settlers of Catan", "KOSMOS", 10, 99, 120));
        products.add(new Figurine("Gandalf", "White"));
        products.add(new Figurine("Gandalf", "Grey"));
        products.add(new Figurine("MasterChief", "Green"));
        products.add(new Figurine("Saruman", "White"));
        products.add(new Figurine("Sauron", "Red & Black"));
        products.add(new Figurine("Jon Snow", "Natural Colors"));
        products.add(new Figurine("The Lich King", "White & Black"));
        products.add(new Figurine("Blue Eyes White Dragon", "Blue & White"));
        products.add(new Figurine("Dark Magician", "Red & Black"));
        products.add(new Figurine("Walter White", "Natural Colors"));

        while (toRet.size() < amt) {
            Product rand = products.get(ThreadLocalRandom.current().nextInt(products.size()));
            if (rand instanceof Figurine) {
                toRet.add(rand.copyAsFigurine(false));
            } else if (rand instanceof BoardGame) {
                toRet.add(rand.copyAsBoardGame());
            }
        }
        return toRet;
    }

    public static void addRandomProductToInventory(int amt) {
        Inventory.addAll(generateRandomProduct(amt));
    }
}
