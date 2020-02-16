package utils;

import io.AbstractConsole;

public class MenuStrings {

    public static String welcome;
    public static String standard;
    public static String commands;
    public static String goodbye;
    public static String create;
    public static String createWithProducts;
    public static String update;
    public static String delete;
    public static String report;
    public static String add;
    public static String list;
    public static String modify;
    public static String sub;
    public static String set;

    public static void loadStrings() {
        String allProducts = "";
        String forLengthChecks = "";

        welcome = "" +
                "**************************************************\n" +
                "***           Welcome and Bienvenue            ***\n" +
                "***                    to                      ***\n" +
                "***          ZipCo Inventory Manager           ***\n" +
                "***                                            ***\n" +
                "***               Enter a Command              ***\n" +
                "***     Enter 'Help' for a List of Commands    ***\n" +
                "**************************************************\n";

        standard = "" +
                "**************************************************\n" +
                "***     ZipCo Inventory Manager - Main Menu    ***\n" +
                "**************************************************\n";

        commands = "" +
                "*******************************************************************\n" +
                "**             ZipCo Inventory Manager - Main Menu               **\n" +
                "**                                                               **\n" +
                "**                          Commands                             **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Add        | Increase the amount of products in inventory     **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Commands   | Print formatted list of all commands in program  **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Create     | Add products to inventory                        **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Delete     | Remove products from inventory                   **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Exit       | Save data to JSON and quit program               **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Exitnosave | Quit without saving                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Figure     | Create a Figurine and add to inventory           **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Game       | Create a Board Game and add to inventory         **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Help       | Print list of commands available in current menu **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** List       | Print list of individual products                **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Read       | Print list of products, grouped by quantity      **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Remove     | Delete products from inventory                   **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Report     | Generate reports about products                  **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Return     | Returns you to the main menu                     **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Rollback   | Resets the inventory from JSON                   **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Set        | Set the attributes of products in inventory      **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Sub        | Decrease the amount of products in inventory     **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Update     | Change product attributes                        **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "**                                                               **\n" +
                "*******************************************************************\n";

        goodbye = "" +
                "**************************************************\n" +
                "***      ZipCo Inventory Manager - Goodbye     ***\n" +
                "**************************************************\n";

        create = "" +
                "**************************************************\n" +
                "***    ZipCo Inventory Manager - Create Menu   ***\n" +
                "***                                            ***\n" +
                "***                                            ***\n" +
                "***         Board Game - Enter 'Game'          ***\n" +
                "***         Figurine --- Enter 'Figure'        ***\n" +
                "***                                            ***\n" +
                "***                                            ***\n" +
                "***      Enter 'Help' for a List of Commands   ***\n" +
                "**************************************************\n";

        forLengthChecks = "**************************************************";
        allProducts = Utilities.getStringOfProducts(forLengthChecks.length());
        createWithProducts = "" +
                "**************************************************\n" +
                "***    ZipCo Inventory Manager - Create Menu   ***\n" +
                "***                                            ***\n" +
                "***                                            ***\n" +
                "***         Board Game - Enter 'Game'          ***\n" +
                "***         Figurine - Enter 'Figure'          ***\n" +
                "***                                            ***\n" +
                "***                                            ***\n" +
                allProducts +
                "***                                            ***\n" +
                "***                                            ***\n" +
                "***      Enter 'Help' for a List of Commands   ***\n" +
                "**************************************************\n";

        forLengthChecks = "***************************************************************";
        allProducts = Utilities.getStringOfProducts(forLengthChecks.length());
        update = "" +
                "***************************************************************\n" +
                "***          ZipCo Inventory Manager - Update Menu          ***\n" +
                "***                                                         ***\n" +
                "***                                                         ***\n" +
                allProducts +
                "***                                                         ***\n" +
                "***                                                         ***\n" +
                "***           Enter 'Help' for a List of Commands           ***\n" +
                "***************************************************************\n";

        forLengthChecks = "***************************************************************";
        allProducts = Utilities.getStringOfProducts(forLengthChecks.length());
        delete = "" +
                "***************************************************************\n" +
                "***          ZipCo Inventory Manager - Delete Menu          ***\n" +
                "***                                                         ***\n" +
                "***                                                         ***\n" +
                allProducts +
                "***                                                         ***\n" +
                "***           Enter 'Help' for a List of Commands           ***\n" +
                "***     Enter 'Remove' and Product ID to Remove Product     ***\n" +
                "***************************************************************\n";

        report = "" +
                "**************************************************\n" +
                "*** ZipCo Inventory Manager - Enter Command(R) ***\n" +
                "**************************************************\n";

        forLengthChecks = "***************************************************************";
        allProducts = Utilities.getStringOfProducts(forLengthChecks.length());
        add = "" +
                "***************************************************************\n" +
                "***           ZipCo Inventory Manager - Add Menu            ***\n" +
                "***                                                         ***\n" +
                "***                                                         ***\n" +
                allProducts +
                "***                                                         ***\n" +
                "***           Enter 'Help' for a List of Commands           ***\n" +
                "***  Enter 'Add' and Product ID to Increase Quantity by 1   ***\n" +
                "***************************************************************\n";

        forLengthChecks = "***************************************************************";
        allProducts = Utilities.getStringOfProducts(forLengthChecks.length());
        list = "" +
                "***************************************************************\n" +
                "***         ZipCo Inventory Manager - Products Menu         ***\n" +
                "***                                                         ***\n" +
                "***                                                         ***\n" +
                allProducts +
                "***                                                         ***\n" +
                "***           Enter 'Help' for a List of Commands           ***\n" +
                "***************************************************************\n";

        modify = "";
        set = "";
        sub = "";
    }

    public static String getStringFromPromptType(AbstractConsole.PromptMessage msg) {
        switch (msg) {
            case READ:
                return standard;
            case CREATE:
                return create;
            case DELETE:
                return delete;
            case REPORT:
                return report;
            case STANDARD:
                return standard;
            case UPDATE:
                return update;
            case WELCOME:
                return welcome;
            case MODIFY:
                return modify;
            case LIST:
                return list;
            case ADD:
                return add;
            case SUB:
                return sub;
            case SET:
                return set;
            case GOODBYE:
                return goodbye;
            case CREATE_WITH_PROD:
                return createWithProducts;
            case COMMANDS:
                return commands;
            default:
                return standard;
        }
    }
}
