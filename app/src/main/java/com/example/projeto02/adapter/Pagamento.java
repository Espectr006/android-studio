package com.example.projeto02.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto02.NavigationActivity;
import com.example.projeto02.ObrigadoScreen;
import com.example.projeto02.databinding.ActivityPagamentoBinding;

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
        double total = getIntent().getDoubleExtra("total", 0.0);
        String saucesAndDrinks = getIntent().getStringExtra("saucesAndDrinks");

        // Formatar valor total para moeda
        NumberFormat currencyFormat = DecimalFormat.getCurrencyInstance();

        // Atualizar os textos na interface
        binding.txtTotal.setText(String.format("%s\nQuantidade: %d\nMolhos e Bebidas: %s\nTotal: %s",name, amount, saucesAndDrinks, currencyFormat.format(total)));

        // Esconder os campos inicialmente
        binding.editPix.setVisibility(View.GONE);
        binding.editCartaoCredito.setVisibility(View.GONE);  // Esconde o campo Cartão de Crédito inicialmente

        // Configurar botão de pagamento
        binding.btPagar.setOnClickListener(v -> {
            if (binding.CartaodeCredito.isChecked()) {
                // Pagamento com cartão de crédito
                String cartaoCredito = binding.editCartaoCredito.getText().toString().trim();
                if (!cartaoCredito.isEmpty()) {
                    Toast.makeText(this, "Pagamento com Cartão de Crédito", Toast.LENGTH_SHORT).show();
                    showObrigadoScreen();
                } else {
                    Toast.makeText(this, "Preencha o campo do Cartão de Crédito", Toast.LENGTH_SHORT).show();
                }
            } else if (binding.Pix.isChecked()) {
                // Exibir campo Pix
                String pix = binding.editPix.getText().toString().trim();

                if (!pix.isEmpty()) {
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

        // Após a tela de agradecimento, redirecionar para os pedidos
        Intent pedidosIntent = new Intent(this, NavigationActivity.class); // Ou para o fragmento correspondente
        startActivity(pedidosIntent);
    }
}
