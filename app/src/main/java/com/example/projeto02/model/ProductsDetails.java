package com.example.projeto02.model;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto02.MainActivity;
import com.example.projeto02.adapter.Pagamento;
import com.example.projeto02.databinding.ActivityProductsDetailsBinding;
import com.example.projeto02.utils.PedidoManager;

import java.text.DecimalFormat;

public class ProductsDetails extends AppCompatActivity {

    private ActivityProductsDetailsBinding binding;
    private int amount = 1; // Variável para armazenar a quantidade do produto
    private double newPrice = 0.0; // Variável para o novo preço do produto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Define a cor da barra de status
        getWindow().setStatusBarColor(Color.parseColor("#E0E0E0"));

        // Obtém os extras do Intent com validação
        if (getIntent().getExtras() != null) {
            int imgProduct = getIntent().getExtras().getInt("imgProduct", 0);
            String name = getIntent().getExtras().getString("name", "Produto");
            String priceString = getIntent().getExtras().getString("price", "0.0");

            // Converte o preço para double com tratamento de erro
            double price = parseDoubleSafe(priceString);
            newPrice = price; // Preço unitário inicial

            // Formatação para exibir o preço como moeda
            DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");

            // Atualiza os componentes da interface
            binding.imgProduct.setBackgroundResource(imgProduct);
            binding.txtProductName.setText(name);
            binding.txtPrice.setText(decimalFormat.format(newPrice));

            // Configuração dos botões
            setupButtons(imgProduct, price, decimalFormat, name);
        }
    }

    private void setupButtons(int imgProduct, double unitPrice, DecimalFormat decimalFormat, String name) {
        // Botão de voltar
        binding.btBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Botão de aumentar a quantidade
        binding.btIncrese.setOnClickListener(v -> {
            if (amount < 3) {
                amount++;
                newPrice = unitPrice * amount; // Calcula o novo preço com base na quantidade
                Log.d("ProductsDetails", "Aumentando quantidade: " + amount + ", Novo preço: " + newPrice);
                updatePriceAndAmount(decimalFormat);
            }
        });

        // Botão de diminuir a quantidade
        binding.btDecrease.setOnClickListener(v -> {
            if (amount > 1) {
                amount--;
                newPrice = unitPrice * amount; // Calcula o novo preço com base na quantidade
                Log.d("ProductsDetails", "Diminuindo quantidade: " + amount + ", Novo preço: " + newPrice);
                updatePriceAndAmount(decimalFormat);
            }
        });

        // Botão de confirmar
        binding.btConfirmar.setOnClickListener(v -> {
            String extras = getSelectedExtras();
            Pedido pedido = new Pedido(imgProduct, name, amount, newPrice);  // Corrigido aqui
            PedidoManager.getInstance().adicionarPedido(pedido);

            Intent intent = new Intent(this, Pagamento.class);
            intent.putExtra("name", name);
            intent.putExtra("amount", amount);
            intent.putExtra("Total", newPrice);
            intent.putExtra("MolhoseBebidas", extras);
            startActivity(intent);
        });
    } // Fim do método setupButtons

    private void updatePriceAndAmount(DecimalFormat decimalFormat) {
        // Atualiza os textos da quantidade e do preço
        binding.txtAmount.setText(String.valueOf(amount));
        binding.txtPrice.setText(decimalFormat.format(newPrice));
    }

    private double parseDoubleSafe(String value) {
        try {
            // Remove caracteres não numéricos, exceto ponto decimal
            value = value.replaceAll("[^\\d.,]", "").replace(",", ".");
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0; // Retorna 0.0 em caso de erro
        }
    }

    private String getSelectedExtras() {
        StringBuilder extras = new StringBuilder();

        if (binding.Ketchup != null && binding.Ketchup.isChecked()) {
            extras.append("Ketchup ");
        }
        if (binding.Maionese != null && binding.Maionese.isChecked()) {
            extras.append("Maionese ");
        }
        if (binding.Refrigerante != null && binding.Refrigerante.isChecked()) {
            extras.append("Refrigerante ");
        }
        if (binding.Suco != null && binding.Suco.isChecked()) {
            extras.append("Suco ");
        }

        Log.d("ProductsDetails", "Extras selecionados: " + extras);
        return extras.toString().trim();
    }
}