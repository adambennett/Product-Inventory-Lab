package io;

import models.Inventory;
import services.FigurineService;

public class App {

    private FigurineService figService = new FigurineService(); // (1)
    public static Inventory inventory;

    public static void main(String[] args){
        App application = new App(); // (2)
        application.init();  // (3)
    }

    public void init(){
        // (4)
        // application logic here
        // call methods to take user input and interface with services
        Console.printWelcomeWithPrompt();
    }

    static {
        inventory = new Inventory();
    }


}
