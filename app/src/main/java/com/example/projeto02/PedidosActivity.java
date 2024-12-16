package com.example.projeto02;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto02.adapter.PedidoAdapter;
import com.example.projeto02.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidosActivity extends AppCompatActivity {

    private RecyclerView rvPedidos;
    private PedidoAdapter pedidosAdapter;
    private List<Pedido> listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        rvPedidos = findViewById(R.id.rvPedidos);
        TextView tvSemPedidos = findViewById(R.id.tvSemPedidos);

        // Simulação de pedidos (substitua com dados reais do banco de dados)
        listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido(R.drawable.acaraje, "Acarajé", 1, 29.90));  // Adicionando imagem
        listaPedidos.add(new Pedido(R.drawable.pastel, "Pastel", 2, 19.90));  // Adicionando imagem
        listaPedidos.add(new Pedido(R.drawable.coca, "Coca-Cola 2L", 1, 10.00));  // Adicionando imagem

        // Configuração da RecyclerView
        pedidosAdapter = new PedidoAdapter(listaPedidos);  // Usando a variável correta
        rvPedidos.setLayoutManager(new LinearLayoutManager(this));
        rvPedidos.setAdapter(pedidosAdapter);

        // Exibe mensagem se a lista estiver vazia
        if (listaPedidos.isEmpty()) {
            tvSemPedidos.setVisibility(View.VISIBLE);
            rvPedidos.setVisibility(View.GONE);
        } else {
            tvSemPedidos.setVisibility(View.GONE);
            rvPedidos.setVisibility(View.VISIBLE);
        }
    }
}