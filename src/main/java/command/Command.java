package command;

public interface Command {

    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String EDIT_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_ID = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_MAX_OF_PRICE_PRODUCT = "8";
    String PRINT_MIN_OF_PRICE_PRODUCT = "9";
    String PRINT_AVG_OF_PRICE_PRODUCT = "10";

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_CATEGORY + " for ADD CATEGORY");
        System.out.println("Please input " + EDIT_CATEGORY_BY_ID + " for EDIT CATEGORY BY ID");
        System.out.println("Please input " + DELETE_CATEGORY_BY_ID + " for DELETE CATEGORY BY ID");
        System.out.println("Please input " + ADD_PRODUCT + " for ADD PRODUCT");
        System.out.println("Please input " + EDIT_PRODUCT_BY_ID + " for EDIT PRODUCT BY ID");
        System.out.println("Please input " + DELETE_PRODUCT_BY_ID + " for DELETE PRODUCT BY ID");
        System.out.println("Please input " + PRINT_SUM_OF_PRODUCTS + " for PRINT SUM OF PRODUCTS");
        System.out.println("Please input " + PRINT_MAX_OF_PRICE_PRODUCT + " for PRINT MAX OF PRICE PRODUCT");
        System.out.println("Please input " + PRINT_MIN_OF_PRICE_PRODUCT + " for PRINT MIN OF PRICE PRODUCT");
        System.out.println("Please input " + PRINT_AVG_OF_PRICE_PRODUCT + " for PRINT AVG OF PRICE PRODUCT");
    }
}
