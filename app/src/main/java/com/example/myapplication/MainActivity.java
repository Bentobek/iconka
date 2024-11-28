package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.button.setOnClickListener(v -> {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    super.run();
                    for (int i = 0; i <= 100; i++) {
                        int proceent = i;
                        runOnUiThread(() -> {
                            binding.text.setText(proceent + " %");
                            binding.progressBar.setProgress(proceent, true);

                        });
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    runOnUiThread(() -> {
                        binding.text.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.GONE);
                        binding.lottie.setVisibility(View.VISIBLE);
                        binding.lottie.playAnimation();
                    });

                }
            };

            thread.start();
        });


        binding.buttonActivity.setOnClickListener(v -> {
  startActivity(new Intent(MainActivity.this,SecondActivity.class));
        });
    }
}