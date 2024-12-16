package com.example.projeto02.model;

public class Pedido {
    private int imgProduto;
    private String nomeProduto;
    private int quantidade;
    private double precoTotal;
    private String userId;
    private boolean pago;

    public Pedido() {
        // Construtor vazio necessário para o Firestore
    }

    public Pedido(int imgProduto, String nomeProduto, int quantidade, double precoTotal, boolean pago) {
        this.imgProduto = imgProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
        this.pago = pago;
    }

    // Getters e Setters
    public int getImgProduto() {
        return imgProduto;
    }

    public void setImgProduto(int imgProduto) {
        this.imgProduto = imgProduto;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
