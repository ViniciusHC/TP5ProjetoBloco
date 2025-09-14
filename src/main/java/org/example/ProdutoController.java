package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class ProdutoController {

    static ProdutoRepository repository = new ProdutoRepository();
    static ArrayList<Produto> produtos = repository.lerProdutos();

    private static String resource = "/produtos";
        public static void config(Javalin app){
            app.get(resource, ProdutoController::getProdutoList);
            app.get(resource+"/{id}", ProdutoController::getProdutoByID);
            app.post(resource, ProdutoController::inserirProduto);
            app.put(resource+"/{id}", ProdutoController::alterarProduto);
        }

    private static void getProdutoList(Context context){
        context.status(200).json(produtos);
    }

    private static void getProdutoByID(Context context){
        Produto produto = null;
        Integer id = parseInt(context.pathParam("id"));
        for (Produto produtoNaLista : produtos){
            if (produtoNaLista.getId() == id){
                produto = produtoNaLista;
                break;
            }
        }
        if (produto != null){
            context.status(200).json(produto);
        }else {
            context.status(404).result("Produto não encontrado");
        }
    }

    private static void inserirProduto(Context context){
        Produto produto = context.bodyAsClass(Produto.class);
        int newKey = produtos.size() + 1;
        produto.setId(newKey);
        produtos.add(produto);
        repository.gravarProdutos(produtos);
        context.status(200).json(produto);
    }

    private static void alterarProduto(Context context){
        Produto produtoAlterado = context.bodyAsClass(Produto.class);
        Integer id = parseInt(context.pathParam("id"));
        for (Produto produtoNaLista : produtos){
            if (produtoNaLista.getId() == id){
                produtoNaLista.setNome(produtoAlterado.getNome());
                produtoNaLista.setPreco(produtoAlterado.getPreco());
                produtoNaLista.setQuantidade(produtoAlterado.getQuantidade());
                break;
            }
        }
        repository.gravarProdutos(produtos);
        context.status(200).json(produtoAlterado);
    }

}
