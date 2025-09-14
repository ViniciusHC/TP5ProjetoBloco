package org.example;

public class Product {
    private int id;
    private String name;
    private int quantidade;
    private float price;

    public Product(){}

    public Product(int id, String name, int quantidade, float price) {
        this.id = id;
        this.name = name;
        this.quantidade = quantidade;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }






}
