package com.example.projeto02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class DadosActivity extends AppCompatActivity {

    private EditText nomeUsuario, emailUsuario, editCPF, editTelefone;
    private Button btnAtualizar;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        initViews();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            usuarioID = currentUser.getUid();
            nomeUsuario.setText(currentUser.getDisplayName());
            emailUsuario.setText(currentUser.getEmail());
        }

        preencherDadosFirestore();

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarDados();
            }
        });
    }

    private void initViews() {
        nomeUsuario = findViewById(R.id.NomeCompleto2);
        emailUsuario = findViewById(R.id.EmailCompleto2);
        editCPF = findViewById(R.id.CPF2);
        editTelefone = findViewById(R.id.Telefone2);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        progressBar = findViewById(R.id.progressBar);
    }

    private void preencherDadosFirestore() {
        if (usuarioID != null) {
            progressBar.setVisibility(View.VISIBLE);
            DocumentReference document = db.collection("Usuarios").document(usuarioID);

            document.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Map<String, Object> data = task.getResult().getData();
                        if (data != null) {
                            nomeUsuario.setText((String) data.get("nomeCompleto"));
                            editCPF.setText((String) data.get("cpf"));
                            editTelefone.setText((String) data.get("telefone"));
                        }
                    } else {
                        Toast.makeText(DadosActivity.this, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void atualizarDados() {
        String nomeCompleto = nomeUsuario.getText().toString();
        String email = emailUsuario.getText().toString();
        String cpf = editCPF.getText().toString();
        String telefone = editTelefone.getText().toString();

        if (nomeCompleto.isEmpty() || email.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        if (currentUser != null) {

            if (!email.equals(currentUser.getEmail())) {
                currentUser.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            atualizarNomeUsuario(nomeCompleto, cpf, telefone);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(DadosActivity.this, "Erro ao atualizar o e-mail no Authentication", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {

                atualizarNomeUsuario(nomeCompleto, cpf, telefone);
            }
        }
    }

    private void atualizarNomeUsuario(String nomeCompleto, String cpf, String telefone) {

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nomeCompleto)
                .build();

        currentUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Atualizar no Firestore
                    Map<String, Object> userUpdates = new HashMap<>();
                    userUpdates.put("nomeCompleto", nomeCompleto);
                    userUpdates.put("email", emailUsuario.getText().toString());
                    userUpdates.put("cpf", cpf);
                    userUpdates.put("telefone", telefone);

                    db.collection("Usuarios").document(usuarioID)
                            .update(userUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(DadosActivity.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DadosActivity.this, "Erro ao atualizar os dados no Firestore", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DadosActivity.this, "Erro ao atualizar o nome no Authentication", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (usuarioID != null) {
            DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        nomeUsuario.setText(documentSnapshot.getString("nomeCompleto"));
                        editCPF.setText(documentSnapshot.getString("cpf"));
                        editTelefone.setText(documentSnapshot.getString("telefone"));
                    }
                }
            });
        }
    }
}
