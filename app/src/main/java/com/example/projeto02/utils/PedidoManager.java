package com.example.projeto02.utils;

import com.example.projeto02.model.Pedido;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class PedidoManager {
    private static PedidoManager instance;
    private FirebaseFirestore db;
    private CollectionReference pedidosRef;

    private PedidoManager() {
        db = FirebaseFirestore.getInstance();
        pedidosRef = db.collection("pedidos");
    }

    public static PedidoManager getInstance() {
        if (instance == null) {
            instance = new PedidoManager();
        }
        return instance;
    }

    public void adicionarPedido(Pedido pedido) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pedido.setUserId(userId);
        pedidosRef.document(userId).collection("pedidos").add(pedido)
                .addOnSuccessListener(documentReference -> {
                    // Pedido salvo com sucesso
                })
                .addOnFailureListener(e -> {
                    // Erro ao salvar pedido
                });
    }

    public void getPedidosPagos(final OnCompleteListener<QuerySnapshot> listener) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pedidosRef.document(userId).collection("pedidos")
                .whereEqualTo("pago", true)  // Filtra pedidos pagos
                .get()
                .addOnCompleteListener(listener);
    }
}
