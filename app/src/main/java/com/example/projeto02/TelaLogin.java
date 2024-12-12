package com.example.projeto02;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaLogin extends AppCompatActivity {

    private TextView tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button LoginButton;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Iniciar();

        tela_cadastro.setOnClickListener(v -> {
            Intent intent = new Intent(TelaLogin.this, Cadastro.class);
            startActivity(intent);
        });

        LoginButton.setOnClickListener(v -> {
            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            } else {
                AutenticarUsuario(v);
            }
        });
    }

    private void AutenticarUsuario(View v) {
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressBar.setVisibility(View.VISIBLE);
                // Atraso para simular carregamento, depois vai para a tela principal
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(TelaLogin.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                }, 3000);
            } else {
                Snackbar snackbar = Snackbar.make(v, "Erro ao logar usuário", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (usuarioAtual != null) {
            // Se o usuário já estiver logado, redireciona para a tela principal (NavigationActivity)
            Intent intent = new Intent(TelaLogin.this, NavigationActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Se o usuário não estiver logado, ele permanece na tela de login
            // Isso garante que o app não redirecione para a tela errada
        }
    }

    private void Iniciar() {
        tela_cadastro = findViewById(R.id.Loginteext);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        LoginButton = findViewById(R.id.LoginButton);
        progressBar = findViewById(R.id.progressBar);
    }
}
