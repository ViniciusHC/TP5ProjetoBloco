package org.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create();
        ProdutoController.config(app);
        app.start();

    }
}