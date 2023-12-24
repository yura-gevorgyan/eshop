package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Category category) {

        String query = "INSERT INTO category(`name`) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * from category WHERE id =" + categoryId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                return new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                Category category = new Category(id, name);
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void editCategory(Category categoryById) {
        String query = "UPDATE category SET `name` = '%s' WHERE id =" + categoryById.getId();
        String sql = String.format(query, categoryById.getName());

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoryById(int id) {
        Category categoryById = getCategoryById(id);

        if (categoryById == null) {
            System.out.println("Category with " + id + " ID exists !!!");
            return;
        }

        String sql = "DELETE  FROM category WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
