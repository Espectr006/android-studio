package com.example.projeto02;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ObrigadoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrigado_screen);

        getWindow().setStatusBarColor(Color.parseColor("#E0E0E0"));
    }
}
