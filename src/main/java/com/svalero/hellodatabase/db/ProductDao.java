package com.svalero.hellodatabase.db;

import com.svalero.hellodatabase.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {

    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public boolean registerProducts(String name, float price) throws SQLException {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, price);
        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;

    }

    public boolean modifyProduct(int productId, String name, float price) throws SQLException{
        String sql = "UPDATE products SET name = ?, price = ?, WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        statement.setString(2, name);
        statement.setFloat(3, price);
        int affectedRows = statement.executeUpdate();

        return affectedRows != 0;

    }
    public boolean deleteProducts(String name) throws SQLException {
        String sql = "DELETE FROM products WHERE name = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        int affectedRows = statement.executeUpdate();
        return affectedRows != 0;
    }

    public List<Product> getAllProducts() throws  SQLException {
        String sql  = "SELECT * FROM products";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return getProductlist(resultSet);
    }

    public List<Product> getProducts(float price) throws SQLException{
        String sql  = "SELECT * FROM products WHERE price = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setFloat(1, price);
        ResultSet resultSet = statement.executeQuery();
        return getProductlist(resultSet);
    }

    private List<Product> getProductlist(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            int productId = resultSet.getInt("id");
            String name = resultSet.getNString("name");
            float price = resultSet.getFloat("price");
            Product product = new Product(productId, name, price);
            products.add(product);
        }

        return products;
    }

}
