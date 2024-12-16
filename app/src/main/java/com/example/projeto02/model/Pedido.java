package com.example.projeto02.model;

public class Pedido {
    private int imagemProduto;
    private String nomeProduto;
    private int quantidade;
    private double precoTotal;

    public Pedido() {
        // Construtor vazio necessário para o Firestore
    }

    public Pedido(int imagemProduto, String nomeProduto, int quantidade, double precoTotal) {
        this.imagemProduto = imagemProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    // Getters e Setters
    public int getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(int imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }
}
