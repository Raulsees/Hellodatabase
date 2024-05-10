package com.svalero.hellodatabase.db;

import com.svalero.hellodatabase.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public int registerProducts(String name, float price) throws SQLException {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, price);
        int affectedRows = statement.executeUpdate();

        return affectedRows;

    }

    public void modifyProducts() {

    }
    public boolean deleteProducts(String name) throws SQLException {
        String sql = "DELETE FROM products WHERE name = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        int affectedRows = statement.executeUpdate();
        return affectedRows != 0;
    }

    public List<Product> getProducts() {
        return null;
    }


}
