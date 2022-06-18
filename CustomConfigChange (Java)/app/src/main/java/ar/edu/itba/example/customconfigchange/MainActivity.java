package ar.edu.itba.example.customconfigchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import ar.edu.itba.example.customconfigchange.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "CustomConfigChange";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showOrientation(getResources().getConfiguration());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d(TAG, "onConfigurationChanged");

        showOrientation(newConfig);
    }

    private void showOrientation(Configuration config) {
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.orientation.setText(R.string.landscape);
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.orientation.setText(R.string.portrait);
        }
    }
}