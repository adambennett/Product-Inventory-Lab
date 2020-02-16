package models;

import services.BoardGameService;
import services.FigurineService;

import java.util.ArrayList;

public class Inventory {

    private static ArrayList<Product> currentInventory;
    private static ArrayList<Product> rollbackList;

    public static void rollback() {
        currentInventory = rollbackList;
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
    }

    public static void add(Product p) {
        int loopMax = 100;
        while (isProduct(p.getId()) && loopMax > 0) {
            p.setId(Product.generateID());
            loopMax--;
        }
        currentInventory.add(p);
    }

    public static void addAll(ArrayList<Product> prods) {
        int loopMax = 5;
        for (Product p : prods) {
            while (isProduct(p.getId()) && loopMax > 0) {
                p.setId(Product.generateID());
                loopMax--;
            }
            currentInventory.add(p);
        }
    }

    public static void addRollback(Product p) {
        int loopMax = 100;
        while (isProduct(p.getId()) && loopMax > 0) {
            p.setId(Product.generateID());
            loopMax--;
        }
        rollbackList.add(p);
    }

    public static void remove(Product p) {
        currentInventory.remove(p);
    }

    public static void remove(int ID) {
        ArrayList<Product> toRemove = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p.getId() == ID) {
                toRemove.add(p);
            }
        }

        for (Product p : toRemove) {
            currentInventory.remove(p);
        }
    }

    public static void remove(String name) {
        ArrayList<Product> toRemove = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                toRemove.add(p);
            }
        }

        for (Product p : toRemove) {
            currentInventory.remove(p);
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
        for (Product p : currentInventory) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static Integer size() {
        return currentInventory.size();
    }

    public static void set(Product p, int index) {
        currentInventory.set(index, p);
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

    public static Integer amtOfProductByType(Product p) {
        int amt = 0;
        for (Product prod : currentInventory) {
            if (prod.getType().equals(p.getType())) {
                amt++;
            }
        }
        return amt;
    }

    public static Integer amtOfProductByName(Product p) {
        int amt = 0;
        for (Product prod : currentInventory) {
            if (p instanceof Figurine && prod instanceof Figurine) {
                Figurine fig = (Figurine)p;
                Figurine figB = (Figurine)prod;
                if (fig.getColor().toLowerCase().equals(figB.getColor().toLowerCase()) && fig.getName().toLowerCase().equals(figB.getName().toLowerCase())) {
                    amt++;
                }
            }
            else if (prod.getName().toLowerCase().equals(p.getName().toLowerCase())) {
                amt++;
            }
        }
        return amt;
    }

    public static void increaseAmtOfProduct(int id, int amt, boolean copyExact) {
        ArrayList<Product> toAdd = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p.getId() == id) {
                if (p instanceof Figurine) {
                   toAdd.add(p);
                   break;
                } else if (p instanceof BoardGame) {
                    toAdd.add(p);
                    break;
                }
            }
        }

        refreshInventory(amt, toAdd, copyExact);
    }

    public static void increaseAmtOfProduct(String prodName, int amt, boolean copyExact) {
        ArrayList<Product> toAdd = new ArrayList<>();
        for (Product p : currentInventory) {
            if (p.getName().toLowerCase().equals(prodName.toLowerCase())) {
                if (p instanceof Figurine) {
                    toAdd.add(p);
                    break;
                } else if (p instanceof BoardGame) {
                    toAdd.add(p);
                    break;
                }
            }
        }

        refreshInventory(amt, toAdd, copyExact);
    }

    private static void refreshInventory(int amt, ArrayList<Product> toAdd, boolean copyExact) {
        for (Product p : toAdd) {
            for (int i = 0; i < amt; i++) {
                if (p instanceof BoardGame) {
                    currentInventory.add(p.copyAsBoardGame());
                } else if (p instanceof Figurine) {
                    currentInventory.add(p.copyAsFigurine(copyExact));
                }
            }
        }
    }

    public static void loadData() {
        BoardGameService.loadJSONData();
        FigurineService.loadJSONData();
    }

    static {
        currentInventory = new ArrayList<>();
        rollbackList = new ArrayList<>();
    }

}
