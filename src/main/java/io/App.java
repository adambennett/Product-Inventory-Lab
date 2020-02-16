package io;

import models.Inventory;

public class App {

    public static void main(String[] args)
    {
        App application = new App();
        application.init();
    }

    public void init(){
        Inventory.loadData();
        Console mainConsole = new Console();
        mainConsole.printPrompt(Console.PromptMessage.WELCOME, true);
    }

}
