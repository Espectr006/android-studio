package com.example.projeto02.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto02.NavigationActivity;
import com.example.projeto02.ObrigadoScreen;
import com.example.projeto02.databinding.ActivityPagamentoBinding;
import com.example.projeto02.model.Pedido;
import com.example.projeto02.utils.PedidoManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Pagamento extends AppCompatActivity {

    private ActivityPagamentoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar a cor da barra de status
        getWindow().setStatusBarColor(Color.parseColor("#E0E0E0"));

        // Obter dados enviados pela intent
        String name = getIntent().getStringExtra("name");
        int amount = getIntent().getIntExtra("amount", 1);
        double total = getIntent().getDoubleExtra("Total", 0.0);
        String saucesAndDrinks = getIntent().getStringExtra("MolhoseBebidas");
        int imgProduct = getIntent().getIntExtra("imgProduct", 0);  // Obtém o ID da imagem do produto

        // Formatar valor total para moeda
        NumberFormat currencyFormat = DecimalFormat.getCurrencyInstance();

        // Atualizar os textos na interface
        binding.txtTotal.setText(String.format("%s\nQuantidade: %d\nMolhos e Bebidas: %s\nTotal: %s",
                name, amount, saucesAndDrinks, currencyFormat.format(total)));

        // Esconder os campos de pagamento inicialmente
        binding.editPix.setVisibility(View.GONE);
        binding.editCartaoCredito.setVisibility(View.GONE);  // Esconde o campo Cartão de Crédito inicialmente

        // Configurar botão de pagamento
        binding.btPagar.setOnClickListener(v -> {
            if (binding.CartaodeCredito.isChecked()) {
                // Pagamento com cartão de crédito
                String cartaoCredito = binding.editCartaoCredito.getText().toString().trim();
                if (!cartaoCredito.isEmpty()) {
                    // Cria o pedido e adiciona à lista de pedidos
                    Pedido pedido = new Pedido(imgProduct, name, amount, total, true);  // Marca como pago
                    PedidoManager.getInstance().adicionarPedido(pedido); // Adiciona o pedido à lista

                    Toast.makeText(this, "Pagamento com Cartão de Crédito", Toast.LENGTH_SHORT).show();
                    showObrigadoScreen();
                } else {
                    Toast.makeText(this, "Preencha o campo do Cartão de Crédito", Toast.LENGTH_SHORT).show();
                }
            } else if (binding.Pix.isChecked()) {
                // Exibir campo Pix
                String pix = binding.editPix.getText().toString().trim();

                if (!pix.isEmpty()) {
                    // Cria o pedido e adiciona à lista de pedidos
                    Pedido pedido = new Pedido(imgProduct, name, amount, total, true);  // Marca como pago
                    PedidoManager.getInstance().adicionarPedido(pedido); // Adiciona o pedido à lista

                    // Pagamento com Pix
                    Toast.makeText(this, "Pagamento com Pix", Toast.LENGTH_SHORT).show();
                    showObrigadoScreen();
                } else {
                    Toast.makeText(this, "Preencha o campo Pix", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Selecione uma forma de pagamento", Toast.LENGTH_SHORT).show();
            }
        });

        // Alterar visibilidade do campo Pix dinamicamente
        binding.Pix.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.editPix.setVisibility(View.VISIBLE);  // Mostra o campo Pix
                binding.editCartaoCredito.setVisibility(View.GONE);  // Esconde o campo Cartão de Crédito
            } else {
                binding.editPix.setVisibility(View.GONE);  // Esconde o campo Pix
            }
        });

        // Alterar visibilidade do campo Cartão de Crédito dinamicamente
        binding.CartaodeCredito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.editCartaoCredito.setVisibility(View.VISIBLE);  // Mostra o campo Cartão de Crédito
                binding.editPix.setVisibility(View.GONE);  // Esconde o campo Pix
            } else {
                binding.editCartaoCredito.setVisibility(View.GONE);  // Esconde o campo Cartão de Crédito
            }
        });
    }

    private void showObrigadoScreen() {
        Intent intent = new Intent(this, ObrigadoScreen.class);
        startActivity(intent);
        finish(); // Finaliza a tela atual

        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(this, NavigationActivity.class); // Altere MainActivity para sua tela inicial
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpa a pilha de atividades
            startActivity(homeIntent);
        }, 2000);
    }
}
