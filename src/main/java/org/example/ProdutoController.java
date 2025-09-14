package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class ProdutoController {

    static ProdutoRepository repository = new ProdutoRepository();
    static ArrayList<ProdutoModel> produtos = repository.lerProdutos();

    private static String resource = "/produtos";
        public static void config(Javalin app){
            app.get(resource, ProdutoController::getProdutoList);
            app.get(resource+"/{id}", ProdutoController::getProdutoByID);
            app.post(resource, ProdutoController::inserirProduto);
            app.put(resource+"/{id}", ProdutoController::alterarProduto);
            app.delete(resource+"/{id}", ProdutoController::deletarProduto);
        }

    private static void getProdutoList(Context context){
        context.status(200).json(produtos);
    }

    private static void getProdutoByID(Context context){
        ProdutoModel produtoModel = null;
        Integer id = parseInt(context.pathParam("id"));
        for (ProdutoModel produtoModelNaLista : produtos){
            if (produtoModelNaLista.getId() == id){
                produtoModel = produtoModelNaLista;
                break;
            }
        }
        if (produtoModel != null){
            context.status(200).json(produtoModel);
        }else {
            context.status(404).result("Produto não encontrado");
        }
    }

    private static void inserirProduto(Context context){
        ProdutoModel produtoModel = context.bodyAsClass(ProdutoModel.class);
        int newKey = produtos.size() + 1;
        produtoModel.setId(newKey);
        produtos.add(produtoModel);
        repository.gravarProdutos(produtos);
        context.status(200).json(produtoModel);
    }

    private static void alterarProduto(Context context){
        ProdutoModel produtoModelAlterado = context.bodyAsClass(ProdutoModel.class);
        Integer id = parseInt(context.pathParam("id"));
        for (ProdutoModel produtoModelNaLista : produtos){
            if (produtoModelNaLista.getId() == id){
                produtoModelNaLista.setNome(produtoModelAlterado.getNome());
                produtoModelNaLista.setPreco(produtoModelAlterado.getPreco());
                produtoModelNaLista.setQuantidade(produtoModelAlterado.getQuantidade());
                break;
            }
        }
        repository.gravarProdutos(produtos);
        context.status(200).json(produtoModelAlterado);
    }

    private static void deletarProduto(Context context){
        Integer id = parseInt(context.pathParam("id"));
        for (int i = 0; i < produtos.size(); i++){
            if (produtos.get(i).getId() == id){
                produtos.remove(i);
                break;
            }
        }
        repository.gravarProdutos(produtos);
        context.status(200).json("Produto removido com sucesso");
    }
}
