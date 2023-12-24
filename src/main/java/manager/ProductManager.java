package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CategoryManager categoryManager = new CategoryManager();

    public void add(Product product) {
        String query = "INSERT INTO product(`name`,description,price,quantity,category_id) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {

                int id = generatedKeys.getInt(1);
                product.setId(id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * from product WHERE id =" + productId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                int quantity = resultSet.getInt(5);
                int categoryId = resultSet.getInt(6);
                Category category = categoryManager.getCategoryById(categoryId);
                return new Product(id, name, description, price, quantity, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT  * FROM product";

        List<Product> products = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                int categoryId = resultSet.getInt("category_id");

                Category categoryById = categoryManager.getCategoryById(categoryId);

                if (categoryById != null) {
                    Product product = new Product(id, name, description, price, quantity, categoryById);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void editProduct(Product productById) {
        String query = "UPDATE product SET `name` = '%s',description = '%s',price = " + productById.getPrice() + ",quantity = " + productById.getQuantity() + ", category_id =" + productById.getCategory().getId() + " WHERE id =" + productById.getId();
        String sql = String.format(query, productById.getName(), productById.getDescription());

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Product productById) {
        String sql = "DELETE FROM product WHERE id =" + productById.getId();

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printSumOfProducts() {
        String sql = "SELECT SUM(quantity) AS AllQuantity FROM product";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int allQuantity = resultSet.getInt("AllQuantity");
                System.out.println("All Quantity: " + allQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMaxOfPrice() {
        String sql = "SELECT MAX(price) AS MAX FROM product";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                double max = resultSet.getDouble("MAX");
                System.out.println("MAX: " + max);
            }
        } catch (SQLException e) {

        }
    }

    public void printMinOfPrice() {
        String sql = "SELECT MIN(price) AS MIN FROM product";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                double min = resultSet.getDouble("MIN");
                System.out.println("MIN: " + min);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAvgOfPrice() {
        String sql = "SELECT AVG(price) AS Average FROM product";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                double average = resultSet.getDouble("Average");
                System.out.println("Average: " + average);
            }
        } catch (SQLException e) {

        }
    }
}
