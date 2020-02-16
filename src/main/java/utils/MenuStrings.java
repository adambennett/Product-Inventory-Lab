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
    public static String mainMenuCommands;
    public static String createMenuCommands;
    public static String updateMenuCommands;
    public static String deleteMenuCommands;
    public static String reportMenuCommands;

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
                "**                          Commands                             **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Add        | Increase the amount of products in inventory     **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Commands   | Print formatted list of all commands in program  **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Create     | Move to Create Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Delete     | Move to Delete Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Exit       | Save data to JSON and quit program               **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** ExitNoSave | Quit without saving                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Figure     | Create a Figurine and add to inventory           **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Game       | Create a Board Game and add to inventory         **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Help       | Print list of commands available in current menu **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** List       | Print list of individual products                **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Load       | Resets the inventory from CSV                    **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Read       | Print list of products, grouped by quantity      **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Report     | Move to Report Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Return     | Return to Main Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Rollback   | Resets the inventory from JSON                   **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Save       | Save the inventory data to JSON                  **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** SaveCSV    | Save the inventory data to CSV                   **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Set        | Set the attributes of products in inventory      **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Sub        | Decrease the amount of products in inventory     **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Update     | Modify inventory                                 **\n" +
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
                "****************************************************************\n" +
                "***          ZipCo Inventory Manager - Report Menu           ***\n" +
                "***                                                          ***\n" +
                "***      Enter 'Help' for a List of Reporting Commands       ***\n" +
                "****************************************************************\n";

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

        forLengthChecks = "***************************************************************";
        allProducts = Utilities.getStringOfProducts(forLengthChecks.length());
        sub = "" +
                "***************************************************************\n" +
                "***           ZipCo Inventory Manager - Sub Menu            ***\n" +
                "***                                                         ***\n" +
                "***                                                         ***\n" +
                allProducts +
                "***                                                         ***\n" +
                "***           Enter 'Help' for a List of Commands           ***\n" +
                "***  Enter 'Sub' and Product ID to Decrease Quantity by 1   ***\n" +
                "***************************************************************\n";

        mainMenuCommands = "" +
                "*******************************************************************\n" +
                "**                      Main Menu Commands                       **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Commands   | Print formatted list of all commands in program  **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Create     | Move to Create Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Delete     | Move to Delete Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Exit       | Save data to JSON and quit program               **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** ExitNoSave | Quit without saving                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Help       | Print list of commands available in current menu **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Load       | Resets the inventory from CSV                    **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Read       | Print list of products, grouped by quantity      **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Report     | Move to Report Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Save       | Save the inventory data to JSON                  **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** SaveCSV    | Save the inventory data to CSV                   **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Update     | Modify inventory                                 **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "*******************************************************************\n";

        createMenuCommands = "" +
                "*******************************************************************\n" +
                "**                     Create Menu Commands                      **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Figure     | Create a Figurine and add to inventory           **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Game       | Create a Board Game and add to inventory         **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Help       | Print list of commands available in current menu **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Return     | Return to Main Menu                              **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "*******************************************************************\n";

        deleteMenuCommands = "" +
                "*******************************************************************\n" +
                "**                     Delete Menu Commands                      **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Help       | Print list of commands available in current menu **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Return     | Return to Main Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Sub        | Decrease the amount of products in inventory     **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "*******************************************************************\n";

        reportMenuCommands = "" +
                "*******************************************************************\n" +
                "**                     Report Menu Commands                      **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Help       | Print list of commands available in current menu **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** List       | Print list of individual products                **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Read       | Print list of products, grouped by quantity      **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Report     | Move to Report Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Return     | Return to Main Menu                              **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "*******************************************************************\n";

        updateMenuCommands = "" +
                "*******************************************************************\n" +
                "**                     Update Menu Commands                      **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Add        | Increase the amount of products in inventory     **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Help       | Print list of commands available in current menu **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** List       | Print list of individual products                **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Return     | Return to Main Menu                              **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Set        | Set the attributes of products in inventory      **\n" +
                "** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "** Sub        | Decrease the amount of products in inventory     **\n" +
                "**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**\n" +
                "*******************************************************************\n";

        modify = "";
        set = "";
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
            case BLANK:
                return "";
            default:
                return standard;
        }
    }
}
