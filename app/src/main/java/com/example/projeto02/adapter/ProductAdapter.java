package com.example.projeto02.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projeto02.databinding.ProductItemBinding;
import com.example.projeto02.model.Product;
import com.example.projeto02.model.ProductsDetails;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public void updateProductList(List<Product> newProductList) {
        this.productList = newProductList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Configurar dados do produto
        holder.binding.imageProduct.setImageResource(product.getImgProduct());
        holder.binding.textNome.setText(product.getName());
        holder.binding.textPreco.setText(product.getPrice());


        // Configurar clique no botão "+"
        holder.binding.btadd.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductsDetails.class);
            intent.putExtra("imgProduct", product.getImgProduct());
            intent.putExtra("name", product.getName());
            intent.putExtra("price", product.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        final ProductItemBinding binding;

        public ProductViewHolder(@NonNull ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}