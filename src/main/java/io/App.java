package io;

import models.Inventory;
import services.FigurineService;

public class App {

    private FigurineService figService = new FigurineService();
    public static Inventory inventory;

    public static void main(String[] args)
    {
        App application = new App();
        application.init();
    }

    public void init(){
        // (4)
        // application logic here
        // call methods to take user input and interface with services
        Console mainConsole = new Console();
        mainConsole.printPrompt(Console.PromptMessage.WELCOME, true);
    }

    static {
        inventory = new Inventory();
    }

}
