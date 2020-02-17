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
        MainConsole mainConsole = new MainConsole();
        mainConsole.printPrompt(MainConsole.PromptMessage.WELCOME, true);
    }

}
