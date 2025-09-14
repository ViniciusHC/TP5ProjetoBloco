package org.example;

import java.io.*;
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
                produtoLido.setPreco(Float.parseFloat(produtos[2]));
                produtoLido.setQuantidade(Integer.parseInt(produtos[3]));
                produtosEmEstoque.add(produtoLido);
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("Erro: leitura do arquivo");
        }
        return produtosEmEstoque;
    }

    public void gravarProdutos(ArrayList<Produto> produtos) {
        String Arquivo = "c:/Arquivo/TP3 Projeto Bloco/Pedidos.csv";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Arquivo), "UTF-8"));
            bw.write("id; nome; preco; quantidade");
            bw.newLine();
            for (Produto produto : produtos) {
                bw.write(
                    produto.getId() + ";" +
                        produto.getNome() + ";" +
                        produto.getPreco() + ";" +
                        produto.getQuantidade()
                );
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo");
        }}


}
