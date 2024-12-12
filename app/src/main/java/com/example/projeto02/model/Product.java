package com.example.projeto02.model;

public class Product {
    private int imgProduct;
    private String name;
    private String price;
    private String category;

    public Product(int imgProduct, String name, String price, String category) {
        this.imgProduct = imgProduct;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
}
}