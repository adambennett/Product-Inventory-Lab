package models;

import java.util.ArrayList;

public class Inventory {

    private static ArrayList<Product> allProducts;

    public static ArrayList<Product> getProducts() {
        return allProducts;
    }

    public static void add(Product p) {
        allProducts.add(p);
    }

    public static void remove(Product p) {
        allProducts.remove(p);
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

    static {
        allProducts = new ArrayList<>();
    }

}
