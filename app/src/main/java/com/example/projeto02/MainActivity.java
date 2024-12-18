package com.example.projeto02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.projeto02.databinding.ActivityNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_frag);
        NavigationUI.setupActionBarWithNavController(this, navController);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        BottomNavigationView bottomNavigationView = binding.bottomNavigation;
        NavController navController1 = Navigation.findNavController(this, R.id.nav_host_frag);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_frag);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
