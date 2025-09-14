package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProdutoRepository {


   public ArrayList<Produto> lerProdutos(){
        String Arquivo = "C:\\Users\\vinic\\OneDrive\\Área de Trabalho/produtos.csv";
        ArrayList<Produto> produtosEmEstoque = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Arquivo));
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] produtos = linha.split(";");

                Produto produtoLido = new Produto();
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
