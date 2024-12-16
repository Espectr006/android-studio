package com.example.projeto02.utils;

import com.example.projeto02.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoManager {
    private static PedidoManager instance;
    private List<Pedido> pedidos;

    private PedidoManager() {
        pedidos = new ArrayList<>();
    }

    public static PedidoManager getInstance() {
        if (instance == null) {
            instance = new PedidoManager();
        }
        return instance;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
