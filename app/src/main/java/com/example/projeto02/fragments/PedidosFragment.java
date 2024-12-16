package com.example.projeto02.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto02.R;
import com.example.projeto02.adapter.PedidoAdapter;
import com.example.projeto02.model.Pedido;
import com.example.projeto02.utils.PedidoManager;

import java.util.List;

public class PedidosFragment extends Fragment {

    private RecyclerView rvPedidos;
    private PedidoAdapter pedidosAdapter;
    private List<Pedido> listaPedidos;

    public PedidosFragment() {
        // Construtor vazio obrigatório para Fragmentos
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla o layout do fragmento
        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPedidos = view.findViewById(R.id.rvPedidos);
        TextView tvSemPedidos = view.findViewById(R.id.tvSemPedidos);

        // Aqui você deve obter a lista de pedidos (incluindo imagem do produto)
        listaPedidos = PedidoManager.getInstance().getPedidos();  // Certifique-se de que os pedidos têm a imagem associada

        pedidosAdapter = new PedidoAdapter(listaPedidos);
        rvPedidos.setLayoutManager(new LinearLayoutManager(getContext()));
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