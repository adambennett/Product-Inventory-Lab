package models;

import io.UpdateConsole;
import services.BoardGameService;
import services.FigurineService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private static ArrayList<Product> currentInventory;
    private static ArrayList<Product> rollbackList;
    private static Map<String, Integer> occurrences;

    public static void rollback() {
        clear();
        for (Product p : rollbackList) {
            add(p);
        }
    }

    public static ArrayList<Product> getProducts() {
        return currentInventory;
    }

    public static ArrayList<Figurine> getFigurines() {
        ArrayList<Figurine> toRet = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p instanceof Figurine) {
                toRet.add((Figurine) p);
            }
        }
        return toRet;
    }

    public static ArrayList<BoardGame> getBoardGames() {
        ArrayList<BoardGame> toRet = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p instanceof BoardGame) {
                toRet.add((BoardGame) p);
            }
        }
        return toRet;
    }

    public static void loadProductsFig(ArrayList<Figurine> figures) {
        for (Figurine f : figures) {
            add(f);
        }
    }

    public static void loadProductsBG(ArrayList<BoardGame> games) {
        for (BoardGame g : games) {
            add(g);
        }
    }

    public static void loadRollBackBG(ArrayList<BoardGame> games) {
        for (BoardGame g : games) {
            addRollback(g);
        }
    }

    public static void loadRollBackFig(ArrayList<Figurine> figures) {
        for (Figurine f : figures) {
            addRollback(f);
        }
    }

    public static void clear() {
        currentInventory.clear();
        occurrences.clear();
    }

    public static void add(Product p) {
        add(p, false);
    }

    public static void add(Product p, boolean addToRollback) {
        int loopMax = 100;
        while (isProduct(p.getId()) && loopMax > 0) {
            p.setId(Product.generateID());
            loopMax--;
        }
        if (addToRollback) {
            rollbackList.add(p);
        } else {
            currentInventory.add(p);
            processOccurrences(p);
        }
    }

    public static void addAll(ArrayList<Product> prods) {
       for (Product p : prods) {
           add(p);
       }
    }

    public static void addRollback(Product p) {
        add(p, true);
    }

    public static void removeOneOf(String name) {
        ArrayList<Product> toRemove = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                toRemove.add(p);
                break;
            }
        }

        for (Product p : toRemove) {
            currentInventory.remove(p);
            String productName = p.getName().toLowerCase();
            if (p instanceof Figurine) {
                productName += ((Figurine) p).getColor();
            }
            if (occurrences.containsKey(productName)) {
                occurrences.put(productName, occurrences.get(productName) - 1);
                if (occurrences.get(productName) < 1) {
                    occurrences.remove(productName);
                }
            }
        }
    }

    public static Boolean isProduct(int id) {
        for (Product p : currentInventory) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isProduct(String name) {
        if (occurrences.containsKey(name)) {
            return true;
        } else {
            for (Product p : currentInventory) {
                if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Integer size() {
        return currentInventory.size();
    }

    public static Product get(Product p) {
        if (currentInventory.contains(p)) {
            for (Product prod : currentInventory) {
                if (prod.equals(p)) {
                    return prod;
                }
            }
        }

        return null;
    }

    public static Product get(String name) {
        for (Product prod : currentInventory) {
            if (prod.getName().toLowerCase().equals(name.toLowerCase())) {
                return prod;
            }
        }
        return null;
    }

    public static ArrayList<Product> getAll(String name) {
        ArrayList<Product> toRet = new ArrayList<>();
        for (Product prod : currentInventory) {
            if (prod.getName().toLowerCase().equals(name.toLowerCase())) {
                toRet.add(prod);
            }
        }
        return toRet;
    }

    public static Product get(int id) {
        for (Product prod : currentInventory) {
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public static ArrayList<Product> getAll(int id) {
        ArrayList<Product> toRet = new ArrayList<>();
        for (Product prod : currentInventory) {
            if (prod.getId() == id) {
                toRet.add(prod);
            }
        }
        return toRet;
    }

    public static Integer amtOfProduct(Product p) {
        if (p instanceof Figurine) {
            return occurrences.getOrDefault(p.getName().toLowerCase() + ((Figurine) p).getColor(), 0);
        } else {
            return occurrences.getOrDefault(p.getName().toLowerCase(), 0);
        }
    }

    public static void increaseAmtOfProduct(int id, int amt, boolean copyExact) {
        ArrayList<Product> toAdd = getAllProductsByID(id);
        addToInventory(amt, toAdd, copyExact);
    }

    public static void increaseAmtOfProduct(String prodName, int amt, boolean copyExact) {
        ArrayList<Product> toAdd = getAllProductsByName(prodName);
        addToInventory(amt, toAdd, copyExact);
    }

    public static void decreaseAmtOfProduct(int id, int amt) {
        ArrayList<Product> toRemove = getAllProductsByID(id);
        removeFromInventory(amt, toRemove);
    }

    public static void decreaseAmtOfProduct(String prodName, int amt) {
        ArrayList<Product> toRemove = getAllProductsByName(prodName);
        removeFromInventory(amt, toRemove);
    }

    public static Boolean modify(Product p, UpdateConsole.FieldCommands field, String newValue) {
        if (p instanceof BoardGame) {
            BoardGame g = (BoardGame)p;
            return modifyBG(g, field, newValue);
        } else if (p instanceof Figurine) {
            Figurine f = (Figurine)p;
            return modifyFig(f, field, newValue);
        } else if (field.equals(UpdateConsole.FieldCommands.ID) || field.equals(UpdateConsole.FieldCommands.NAME)) {
            return modifyGeneralProd(p, field, newValue);
        } else {
            return false;
        }
    }

    public static Boolean modifyGeneralProd(Product g, UpdateConsole.FieldCommands field, String val) {
        switch (field) {
            case NAME:
                g.setName(val);
                return true;
            case ID:
                try {
                    g.setId(Integer.parseInt(val));
                    return true;
                } catch (NumberFormatException e) { return false; }
            default:
                return false;
        }
    }

    public static Boolean modifyBG(BoardGame g, UpdateConsole.FieldCommands field, String val) {
        switch (field) {
            case MANUFACTURER:
                g.setManufacturer(val);
                return true;
            case AGE:
                try {
                    g.setAgeMinimum(Integer.parseInt(val));
                    return true;
                } catch (NumberFormatException e) { return false; }
            case PLAYTIME:
                try {
                    g.setAvgPlayingTime(Integer.parseInt(val));
                    return true;
                } catch (NumberFormatException e) { return false; }
            case NAME:
                g.setName(val);
                return true;
            case ID:
                try {
                    g.setId(Integer.parseInt(val));
                    return true;
                } catch (NumberFormatException e) { return false; }
            default:
                return false;
        }
    }

    public static Boolean modifyFig(Figurine g, UpdateConsole.FieldCommands field, String val) {
        switch (field) {
            case COLOR:
                g.setColor(val);
                return true;
            case NAME:
                g.setName(val);
                return true;
            case ID:
                try {
                    g.setId(Integer.parseInt(val));
                    return true;
                } catch (NumberFormatException e) { return false; }
            default:
                return false;
        }
    }



    private static ArrayList<Product> getAllProductsByName(String prodName) {
        ArrayList<Product> toRemove = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p.getName().toLowerCase().equals(prodName.toLowerCase())) {
                if (p instanceof Figurine) {
                    toRemove.add(p);
                    break;
                } else if (p instanceof BoardGame) {
                    toRemove.add(p);
                    break;
                }
            }
        }
        return toRemove;
    }

    private static ArrayList<Product> getAllProductsByID(int id) {
        ArrayList<Product> toRemove = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p.getId() == id) {
                if (p instanceof Figurine) {
                    toRemove.add(p);
                    break;
                } else if (p instanceof BoardGame) {
                    toRemove.add(p);
                    break;
                }
            }
        }
        return toRemove;
    }

    private static void addToInventory(int amt, ArrayList<Product> toAdd, boolean copyExact) {
        for (Product p : toAdd) {
            for (int i = 0; i < amt; i++) {
                if (p instanceof BoardGame) {
                    add(p.copyAsBoardGame());
                } else if (p instanceof Figurine) {
                    add(p.copyAsFigurine(copyExact));
                }
            }
        }
    }

    private static void removeFromInventory(int amt, ArrayList<Product> toRemove) {
        for (Product p : toRemove) {
            for (int i = 0; i < amt; i++) {
                removeOneOf(p.getName());
            }
        }
    }

    public static void loadData(String boardFile, String figureFile) {
        clear();
        BoardGameService.loadJSONData(boardFile);
        FigurineService.loadJSONData(figureFile);
        updateOccurrences();
    }

    public static void loadData() {
        clear();
        BoardGameService.loadJSONData();
        FigurineService.loadJSONData();
        updateOccurrences();
    }

    private static void updateOccurrences() {
        occurrences.clear();
        for (Product p : currentInventory) {
            processOccurrences(p);
        }
    }

    private static void processOccurrences(Product p) {
        String productName = p.getName().toLowerCase();
        if (p instanceof Figurine) {
            productName += ((Figurine) p).getColor();
        }
        if (occurrences.containsKey(productName)) {
            occurrences.put(productName, occurrences.get(productName) + 1);
        } else {
            occurrences.put(productName, 1);
        }
    }

    static {
        currentInventory = new ArrayList<>();
        rollbackList = new ArrayList<>();
        occurrences = new HashMap<>();
    }

}
