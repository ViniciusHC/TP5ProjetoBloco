package org.example;

import java.io.*;
import java.util.ArrayList;

public class ProdutoRepository {

    String Arquivo = "C:\\Users\\vinic\\OneDrive\\Área de Trabalho/produtos.csv";

   public ArrayList<ProdutoModel> lerProdutos(){
        ArrayList<ProdutoModel> produtosEmEstoque = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Arquivo));
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] produtos = linha.split(";");

                ProdutoModel produtoModelLido = new ProdutoModel();
                produtoModelLido.setId(Integer.parseInt(produtos[0]));
                produtoModelLido.setNome(produtos[1]);
                produtoModelLido.setPreco(Float.parseFloat(produtos[2]));
                produtoModelLido.setQuantidade(Integer.parseInt(produtos[3]));
                produtosEmEstoque.add(produtoModelLido);
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("Erro: leitura do arquivo");
        }
        return produtosEmEstoque;
    }

    public void gravarProdutos(ArrayList<ProdutoModel> produtos) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Arquivo), "UTF-8"));
            bw.write("id; nome; preco; quantidade");
            bw.newLine();
            for (ProdutoModel produtoModel : produtos) {
                bw.write(
                    produtoModel.getId() + ";" +
                        produtoModel.getNome() + ";" +
                        produtoModel.getPreco() + ";" +
                        produtoModel.getQuantidade()
                );
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar arquivo");
        }}


}
