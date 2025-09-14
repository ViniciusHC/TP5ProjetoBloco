package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProductRepository {




   public ArrayList<Product> lerProdutos(){
        String Arquivo = "c:/Arquivo/TP3 Projeto Bloco/Produtos.csv";
        ArrayList<Product> produtosEmEstoque = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Arquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] produtos = linha.split(";");

                Product produtoLido = new Product();
                produtoLido.setId(Integer.parseInt(produtos[0]));
                produtoLido.setNome(produtos[1]);
                produtoLido.setQuantidade(Integer.parseInt(produtos[2]));
                produtoLido.setPreco(Float.parseFloat(produtos[3]));
                produtosEmEstoque.add(produtoLido);
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("Erro: leitura do arquivo");
        }
        return produtosEmEstoque;
    }

}
