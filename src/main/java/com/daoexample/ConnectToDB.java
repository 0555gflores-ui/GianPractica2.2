package com.daoexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ConnectToDB {

    private static final ConnectToDB INSTANCE = new ConnectToDB();
    private Connection connection;

    private static final String URL = "jdbc:sqlite:C:\\sqlite\\ejemplopl.db";

    private ConnectToDB() {
        try {
            // Informa de conexion correcta
            connection = DriverManager.getConnection(URL);
            System.out.println("Conectado");
        } catch (SQLException e) {
            Logger.getLogger(ConnectToDB.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    public static ConnectToDB getInstance() {
        return INSTANCE;
    }

    public Connection getConection() {
        return connection;
    }
}
