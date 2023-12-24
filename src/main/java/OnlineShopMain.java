import command.Command;
import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class OnlineShopMain implements Command {

    private static final ProductManager PRODUCT_MANAGER = new ProductManager();
    private static final CategoryManager CATEGORY_MANAGER = new CategoryManager();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Command.printCommands();
            String command = SCANNER.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryByID();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductById();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductByID();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    PRODUCT_MANAGER.printSumOfProducts();
                    break;
                case PRINT_MAX_OF_PRICE_PRODUCT:
                    PRODUCT_MANAGER.printMaxOfPrice();
                    break;
                case PRINT_MIN_OF_PRICE_PRODUCT:
                    PRODUCT_MANAGER.printMinOfPrice();
                    break;
                case PRINT_AVG_OF_PRICE_PRODUCT:
                    PRODUCT_MANAGER.printAvgOfPrice();
                    break;
                default:
                    System.out.println("Invalid Command !!!");
            }
        }

    }

    private static void deleteProductByID() {
        for (Product allProduct : PRODUCT_MANAGER.getAllProducts()) {
            System.out.println(allProduct);
        }
        try {

            System.out.println("Please choose PRODUCT BY ID");
            int id = Integer.parseInt(SCANNER.nextLine());

            Product productById = PRODUCT_MANAGER.getProductById(id);

            if (productById != null) {
                PRODUCT_MANAGER.deleteProduct(productById);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void editProductById() {
        for (Product allProduct : PRODUCT_MANAGER.getAllProducts()) {
            System.out.println(allProduct);
        }

        try {

            System.out.println("Please choose PRODUCT BY ID");
            int id = Integer.parseInt(SCANNER.nextLine());

            Product productById = PRODUCT_MANAGER.getProductById(id);
            if (productById != null) {
                System.out.println("Please input NEW NAME");
                String name = SCANNER.nextLine();

                System.out.println("Please input NEW DESCRIPTION");
                String description = SCANNER.nextLine();

                System.out.println("Please input NEW PRICE");
                double price = Double.parseDouble(SCANNER.nextLine());

                System.out.println("Please input NEW COUNT");
                int count = Integer.parseInt(SCANNER.nextLine());

                for (Category allCategory : CATEGORY_MANAGER.getAllCategories()) {
                    System.out.println(allCategory);
                }

                System.out.println("PLease choose NEW CATEGORY BY ID");
                int categoryId = Integer.parseInt(SCANNER.nextLine());

                Category categoryById = CATEGORY_MANAGER.getCategoryById(categoryId);

                if (categoryById != null) {
                    productById.setName(name);
                    productById.setDescription(description);
                    productById.setPrice(price);
                    productById.setQuantity(count);
                    productById.setCategory(categoryById);

                    PRODUCT_MANAGER.editProduct(productById);
                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void addProduct() {
        System.out.println("Please input NAME");
        String name = SCANNER.nextLine();

        System.out.println("Please input DESCRIPTION");
        String description = SCANNER.nextLine();

        try {
            System.out.println("Please input price");
            double price = Double.parseDouble(SCANNER.nextLine());

            System.out.println("Please input COUNT");
            int count = Integer.parseInt(SCANNER.nextLine());

            for (Category allCategory : CATEGORY_MANAGER.getAllCategories()) {
                System.out.println(allCategory);
            }

            System.out.println("Please choose CATEGORY BY ID");
            int id = Integer.parseInt(SCANNER.nextLine());

            Category categoryById = CATEGORY_MANAGER.getCategoryById(id);

            if (categoryById != null) {
                Product product = new Product(name, description, price, count, categoryById);
                PRODUCT_MANAGER.add(product);
            } else {
                System.out.println("Wrong CATEGORY ID");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void deleteCategoryByID() {
        List<Category> allCategories = CATEGORY_MANAGER.getAllCategories();

        for (Category allCategory : allCategories) {
            System.out.println(allCategory);
        }

        try {

            System.out.println("Please choose CATEGORY BY ID");
            int id = Integer.parseInt(SCANNER.nextLine());

            Category categoryById = CATEGORY_MANAGER.getCategoryById(id);

            if (categoryById != null) {
                List<Product> productsByID = PRODUCT_MANAGER.getProductsByID(categoryById.getId());
                if (!productsByID.isEmpty()) {
                    System.out.println("Are You sure to delete All Products with " + categoryById.getName());
                    System.out.println("Yes/No");

                    String answer = SCANNER.nextLine();
                    if (answer.equalsIgnoreCase("yes")){
                        PRODUCT_MANAGER.deleteProductByCategoryId(categoryById.getId());
                        CATEGORY_MANAGER.deleteCategoryById(id);
                    }

                }

            } else {
                System.out.println("Category with " + id + " ID exists !!!");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    private static void editCategoryById() {
        List<Category> allCategories = CATEGORY_MANAGER.getAllCategories();

        for (Category allCategory : allCategories) {
            System.out.println(allCategory);
        }
        try {


            System.out.println("Please choose CATEGORY BY ID");
            int id = Integer.parseInt(SCANNER.nextLine());

            Category categoryById = CATEGORY_MANAGER.getCategoryById(id);
            if (categoryById != null) {
                System.out.println("Please input NEW NAME");
                String name = SCANNER.nextLine();

                categoryById.setName(name);
                CATEGORY_MANAGER.editCategory(categoryById);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    private static void addCategory() {

        System.out.println("Please input NAME");
        String name = SCANNER.nextLine();
        Category category = new Category(name);
        CATEGORY_MANAGER.add(category);

    }

}
