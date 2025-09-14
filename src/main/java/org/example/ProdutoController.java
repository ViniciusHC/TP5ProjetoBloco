package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static Map<String, Product> products = new HashMap<>();


    private static String resource = "/products";

        public static void config(Javalin app){
//            products.put("1", new Product(1,"Camiseta", 2, 10.87f));
            app.get(resource, ProductController::getProductsList);

        }

    private static void getProductsList(Context context){
        context.status(200).json(products);
    }
}
