package com.example.projeto02.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto02.listitems.Products;
import com.example.projeto02.R;
import com.example.projeto02.adapter.ProductAdapter;
import com.example.projeto02.databinding.FragmentInicioBinding;
import com.example.projeto02.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private ProductAdapter productAdapter;
    private List<Product> allProducts = new ArrayList<>();

    public InicioFragment() {
        // Requerido para o fragmento, mas não faz nada
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout do fragmento
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Configuração do RecyclerView
        RecyclerView recyclerViewProducts = binding.recycleViewProducts;
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewProducts.setHasFixedSize(true);

        // Carregar produtos
        loadProducts();

        // Configurar lista inicial
        filterProductsByCategory("Tudo");

        // Configuração dos botões de filtro
        binding.btTudo.setOnClickListener(view -> {
            updateButtonStyles("Tudo");
            filterProductsByCategory("Tudo");
        });

        binding.btComida.setOnClickListener(view -> {
            updateButtonStyles("Comida");
            filterProductsByCategory("Comida");
        });

        binding.btDoces.setOnClickListener(view -> {
            updateButtonStyles("Doces");
            filterProductsByCategory("Doces");
        });

        binding.btSobremesas.setOnClickListener(view -> {
            updateButtonStyles("Sobremesas");
            filterProductsByCategory("Sobremesas");
        });

        binding.btBebidas.setOnClickListener(view -> {
            updateButtonStyles("Bebidas");
            filterProductsByCategory("Bebidas");
        });

        return rootView;
    }

    private void loadProducts() {
        Products products = new Products();
        allProducts = products.getInitialProducts();

        if (allProducts.isEmpty()) {
            Log.d("InicioFragment", "Nenhum produto encontrado.");
        } else {
            Log.d("InicioFragment", "Produtos carregados com sucesso.");
        }
    }

    private void filterProductsByCategory(String category) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            if (category.equals("Tudo") || product.getCategory().equalsIgnoreCase(category)) {
                filteredList.add(product);
            }
        }

        if (filteredList.isEmpty()) {
            Log.d("InicioFragment", "Nenhum produto na categoria: " + category);
        } else {
            Log.d("InicioFragment", "Produtos na categoria " + category + ": " + filteredList.size());
        }

        ProductAdapter productAdapter = new ProductAdapter(getContext(), filteredList);
        binding.recycleViewProducts.setAdapter(productAdapter);
    }

    private void updateButtonStyles(String category) {
        resetButtonStyle(binding.btTudo);
        resetButtonStyle(binding.btComida);
        resetButtonStyle(binding.btDoces);
        resetButtonStyle(binding.btSobremesas);
        resetButtonStyle(binding.btBebidas); // Adicionando o reset para Bebidas

        switch (category) {
            case "Tudo":
                setButtonSelected(binding.btTudo);
                binding.textlistTitle.setText("Tudo");
                break;
            case "Comida":
                setButtonSelected(binding.btComida);
                binding.textlistTitle.setText("Comida");
                break;
            case "Doces":
                setButtonSelected(binding.btDoces);
                binding.textlistTitle.setText("Doces");
                break;
            case "Sobremesas":
                setButtonSelected(binding.btSobremesas);
                binding.textlistTitle.setText("Sobremesas");
                break;
            case "Bebidas":
                setButtonSelected(binding.btBebidas);
                binding.textlistTitle.setText("Bebidas");
                break;
        }
    }

    private void resetButtonStyle(View button) {
        button.setBackgroundResource(R.drawable.bg_button_disable);
        ((android.widget.TextView) button).setTextColor(getResources().getColor(R.color.Dark_gray));
    }

    private void setButtonSelected(View button) {
        button.setBackgroundResource(R.drawable.bg_button_enable);
        ((android.widget.TextView) button).setTextColor(Color.WHITE);
    }
}
