package com.daoexample.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GlobalLogger {

    public static void setup() {
        try {
            Logger rootLogger = Logger.getLogger(""); // Logger raíz
            
            // Evita duplicar mensajes quitando handlers existentes si quieres solo uno
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler h : handlers) {
                rootLogger.removeHandler(h);
            }

            // FileHandler
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fileHandler);

            // Nivel del rootLogger
            rootLogger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("[Log error]: " + e.getMessage());
        }
    }
}
