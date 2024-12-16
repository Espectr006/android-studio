package com.example.projeto02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto02.R;
import com.example.projeto02.model.Pedido;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {
    private List<Pedido> listaPedidos;

    public PedidoAdapter(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);

        // Definindo a imagem do produto
        holder.imgPedido.setImageResource(pedido.getImagemProduto());

        // Definindo os textos
        holder.txtNomeProduto.setText(pedido.getNomeProduto());
        holder.txtQuantidade.setText("Quantidade: " + pedido.getQuantidade());
        holder.txtPrecoTotal.setText("Preço Total: R$ " + String.format("%.2f", pedido.getPrecoTotal()));

        // Caso o pedido esteja sendo preparado
        holder.txtPedidoPreparando.setVisibility(View.VISIBLE);  // ou a lógica para mostrar ou esconder
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPedido;
        TextView txtNomeProduto;
        TextView txtQuantidade;
        TextView txtPrecoTotal;
        TextView txtPedidoPreparando;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPedido = itemView.findViewById(R.id.imgPedido);
            txtNomeProduto = itemView.findViewById(R.id.txtNomeProduto);
            txtQuantidade = itemView.findViewById(R.id.txtQuantidade);
            txtPrecoTotal = itemView.findViewById(R.id.txtPrecoTotal);
            txtPedidoPreparando = itemView.findViewById(R.id.txtPedidoPreparando);
        }
    }
}

