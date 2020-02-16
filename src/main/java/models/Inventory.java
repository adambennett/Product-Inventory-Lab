package models;

import services.BoardGameService;
import services.FigurineService;

import java.util.ArrayList;

public class Inventory {

    private static ArrayList<Product> allProducts; //= new ArrayList<>();

    public static ArrayList<Product> getProducts() {
        return allProducts;
    }

    public static void clear() {
        allProducts.clear();
    }

    public static void add(Product p) {
        allProducts.add(p);
    }

    public static void remove(Product p) {
        allProducts.remove(p);
    }

    public static void remove(int ID) {
        ArrayList<Product> toRemove = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getId() == ID) {
                toRemove.add(p);
            }
        }

        for (Product p : toRemove) {
            allProducts.remove(p);
        }
    }

    public static Boolean isProductID(int id) {
        for (Product p : allProducts) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static Integer size() {
        return allProducts.size();
    }

    public static void set(Product p, int index) {
        allProducts.set(index, p);
    }

    public static Product get(Product p) {
        if (allProducts.contains(p)) {
            for (Product prod : allProducts) {
                if (prod.equals(p)) {
                   return prod;
                }
            }
        }

        return null;
    }

    public static Integer amtOfProductByType(Product p) {
        int amt = 0;
        for (Product prod : allProducts) {
            if (prod.getType().equals(p.getType())) {
                amt++;
            }
        }
        return amt;
    }

    public static Integer amtOfProductByName(Product p) {
        int amt = 0;
        for (Product prod : allProducts) {
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

    public static void increaseAmtOfProduct(int id, int amt) {
        ArrayList<Product> toAdd = new ArrayList<>();
        for (Product p : allProducts) {
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

        refreshInventory(amt, toAdd);
    }

    public static void increaseAmtOfProduct(String prodName, int amt) {
        ArrayList<Product> toAdd = new ArrayList<>();
        for (Product p : allProducts) {
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

        refreshInventory(amt, toAdd);
    }

    private static void refreshInventory(int amt, ArrayList<Product> toAdd) {
        for (Product p : toAdd) {
            for (int i = 0; i < amt; i++) {
                if (p instanceof BoardGame) {
                    allProducts.add(p.copyAsBoardGame());
                } else if (p instanceof Figurine) {
                    allProducts.add(p.copyAsFigurine());
                }
            }
        }
    }

    public static void loadData() {
        BoardGameService.loadData();
        FigurineService.loadData();
    }

    static {
        allProducts = new ArrayList<>();
        //BoardGameService.loadData();
        //FigurineService.loadData();
    }

}
