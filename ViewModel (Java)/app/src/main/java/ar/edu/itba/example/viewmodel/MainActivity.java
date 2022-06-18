package ar.edu.itba.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import ar.edu.itba.example.viewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        showCounter(model.getCounter());

        binding.increment.setOnClickListener(view -> showCounter(model.incrementCounter()));
    }

    private void showCounter(int counter) {
        binding.result.setText(getResources().getString(R.string.result, counter));
    }
}