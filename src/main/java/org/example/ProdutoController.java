package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdutoController {

    static ProdutoRepository repository = new ProdutoRepository();
    static ArrayList<Produto> produtos = repository.lerProdutos();

    private static String resource = "/produtos";
        public static void config(Javalin app){
            app.get(resource, ProdutoController::getProductsList);

        }

    private static void getProductsList(Context context){
        context.status(200).json(produtos);
    }
}
