package com.example.projeto02.utils;

import com.example.projeto02.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoManager {
    private static final List<Pedido> pedidos = new ArrayList<>();

    public static void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public static List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos); // Retorna uma cópia da lista
    }
}
