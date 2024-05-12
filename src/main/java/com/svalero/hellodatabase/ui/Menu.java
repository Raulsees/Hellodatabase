package com.svalero.hellodatabase.ui;

import com.svalero.hellodatabase.db.Database;
import com.svalero.hellodatabase.db.ProductDao;
import com.svalero.hellodatabase.domain.Product;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner keyboard;
    private Database database;


    public Menu() {
        keyboard = new Scanner(System.in);

    }

    public void showMenu() {
        database = new Database("SYS", "Pass");
        try {
            database.connect();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }


        do {
            System.out.println("Gestión de Productos");
            System.out.println("1. Ver los productos");
            System.out.println("2. Registrar un producto");
            System.out.println("3. Modificar un producto");
            System.out.println("4. Eliminar un producto");
            System.out.println("5. Buscar productos por precio");
            System.out.println("q. Desconectar");
            String choice = keyboard.nextLine();

            switch (choice) {
                case "1":
                    listProducts();
                    break;
                case "2":
                    registerProduct();
                    break;
                case "3":

                    break;

                case "4":
                    deleteProduct();
                    break;

                case "5":

                    break;

                case "q":
                    closeConnection();
                    break;
            }

        } while (true);

    }

    private void listProducts() {
        ProductDao productDao = new ProductDao(database.getConnection());
        try {
            List<Product> allProducts = productDao.getAllProducts();
            allProducts.forEach(product -> System.out.println(product.getName()));
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }


    private void registerProduct() {
        System.out.print("Nombre: ");
        String name = keyboard.nextLine();
        System.out.print("Precio: ");
        float price = Float.parseFloat(keyboard.nextLine());

        ProductDao productDao = new ProductDao(database.getConnection());
        try {
            boolean done = productDao.registerProducts(name, price);
            System.out.println("Hecho: " + done);
        } catch (SQLIntegrityConstraintViolationException sqlicve) {
            System.out.print("El nombre del producto ya existe");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    private void deleteProduct() {
        System.out.print("Nombre: ");
        String name = keyboard.nextLine();

        try {
            ProductDao productDao = new ProductDao(database.getConnection());
            boolean removed = productDao.deleteProducts(name);
            if (removed) {
                System.out.println("El producto ha sido eliminado correctamente");
            } else {
                System.out.println("No habia ningún producto con ese nombre");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            database.close();
            System.exit(0);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}

