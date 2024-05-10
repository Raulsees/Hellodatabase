package com.svalero.hellodatabase;

import com.svalero.hellodatabase.db.Database;
import com.svalero.hellodatabase.db.ProductDao;
import com.svalero.hellodatabase.ui.Menu;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Menu menu = new Menu();
        menu.showMenu();
    }
}
