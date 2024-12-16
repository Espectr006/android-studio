package com.example.projeto02;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto02.adapter.PedidoAdapter;
import com.example.projeto02.model.Pedido;
import com.example.projeto02.utils.PedidoManager;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

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

        // Inicializa a lista de pedidos
        listaPedidos = new ArrayList<>();

        // Exemplo de como você pode buscar pedidos pagos
        PedidoManager.getInstance().getPedidosPagos(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    for (DocumentSnapshot document : querySnapshot) {
                        // Extraímos os dados do documento e criamos um Pedido
                        Pedido pedido = document.toObject(Pedido.class);
                        if (pedido != null) {
                            listaPedidos.add(pedido);  // Adiciona o pedido à lista
                        }
                    }
                    // Atualiza a RecyclerView com os pedidos
                    pedidosAdapter = new PedidoAdapter(listaPedidos);
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
            } else {
                // Caso ocorra erro ao buscar os pedidos
                System.out.println("Erro ao obter pedidos: " + task.getException());
            }
        });
    }
}
