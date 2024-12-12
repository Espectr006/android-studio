package com.example.projeto02.listitems;

import android.util.Log;

import com.example.projeto02.R;
import com.example.projeto02.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Products {

    public List<Product> getInitialProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.acaraje, "Acarajé", "R$ 10,00", "Comida"));
        productList.add(new Product(R.drawable.beiju, "Beijú", "R$ 5,00", "Comida"));
        productList.add(new Product(R.drawable.bobo, "Bobó de Camarão", "R$ 20,00", "Comida"));
        productList.add(new Product(R.drawable.caruru, "Caruru", "R$ 25,00", "Comida"));
        productList.add(new Product(R.drawable.cocada, "Cocada", "R$ 5,00", "Doces"));
        productList.add(new Product(R.drawable.cuscuz, "Cuscuz de Tapioca", "R$ 10,00", "Comida"));
        productList.add(new Product(R.drawable.moqueca, "Moqueca", "R$ 25,00", "Comida"));
        productList.add(new Product(R.drawable.mungunza, "Munguzá", "R$ 5,00", "Sobremesas"));
        productList.add(new Product(R.drawable.quindim, "Quindim", "R$ 10,00", "Sobremesas"));
        productList.add(new Product(R.drawable.sarapatel, "Sarapatel", "R$ 25,00", "Comida"));
        productList.add(new Product(R.drawable.vatapa, "Vatapá", "R$ 20,00", "Comida"));
        productList.add(new Product(R.drawable.pudim, "Pudim", "R$ 10,00", "Sobremesas"));
        productList.add(new Product(R.drawable.pastel, "Pastel", "R$ 10,00", "Comida"));
        productList.add(new Product(R.drawable.brigadeiro, "Brigadeiro", "R$ 5,00", "Doces"));
        productList.add(new Product(R.drawable.beijinho, "Beijinho", "R$ 5,00", "Doces"));
        productList.add(new Product(R.drawable.arrozdoce, "Arroz Doce", "R$ 5,00", "Sobremesas"));
        productList.add(new Product(R.drawable.bemcasados, "Bem Casados", "R$ 10,00", "Doces"));
        productList.add(new Product(R.drawable.brownie, "Brownie", "R$ 10,00", "Doces"));
        productList.add(new Product(R.drawable.docedeleite, "Doce de Leite", "R$ 10,00", "Doces"));
        productList.add(new Product(R.drawable.gelato, "Gelato", "R$ 5,00", "Sobremesas"));
        productList.add(new Product(R.drawable.ganache, "Ganache", "R$ 5,00", "Sobremesas"));
        productList.add(new Product(R.drawable.goiabada, "Goiabada", "R$ 5,00", "Doces"));
        productList.add(new Product(R.drawable.feijoada, "Feijoada", "R$ 20,00", "Comida"));
        productList.add(new Product(R.drawable.jujuba, "Jujuba", "R$ 5,00", "Doces"));
        productList.add(new Product(R.drawable.lasanha, "Lasanha", "R$ 20,00", "Comida"));
        productList.add(new Product(R.drawable.pacoca, "Paçoca", "R$ 5,00", "Doces"));
        productList.add(new Product(R.drawable.coca, "Coca-Cola", "R$ 12,00", "Bebidas"));
        productList.add(new Product(R.drawable.pepsi, "Pepsi", "R$ 10,00", "Bebidas"));
        productList.add(new Product(R.drawable.fanta, "Fanta de Uva", "R$ 8,00", "Bebidas"));
        productList.add(new Product(R.drawable.sprite, "Sprite", "R$ 10,00", "Bebidas"));
        productList.add(new Product(R.drawable.lara, "Fanta de Laranja", "R$ 8,00", "Bebidas"));
        productList.add(new Product(R.drawable.att, "Guarana Antártica", "R$ 10,00", "Bebidas"));
        productList.add(new Product(R.drawable.delvalle, "Del Valle de Uva", "R$ 10,00", "Bebidas"));
        productList.add(new Product(R.drawable.laranja, "Del Valle de Laranja", "R$ 10,00", "Bebidas"));
        productList.add(new Product(R.drawable.cuja, "Del Valle de Cajú", "R$ 10,00", "Bebidas"));
        productList.add(new Product(R.drawable.delmanga, "Del Valle de Manga", "R$ 10,00", "Bebidas"));




















        Log.d("Products", "Total de produtos carregados: " + productList.size());

        return productList;
}
}