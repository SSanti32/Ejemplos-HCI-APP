package ar.edu.itba.example.saveinstancestate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import ar.edu.itba.example.saveinstancestate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "SaveInstanceState";
    public static final String COUNTER = "counter";
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null)
            Log.d(TAG, "MainActivity: onCreate() first time");
        else {
            Log.d(TAG, "MainActivity: onCreate() recreated");

            // Restore counter variable value when activity is recreated
            counter = savedInstanceState.getInt(COUNTER);
        }

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.result.setText(getResources().getString(R.string.result, counter));

        binding.increment.setOnClickListener(view -> {
            ++counter;
            binding.result.setText(getResources().getString(R.string.result, counter));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "MainActivity: onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "MainActivity: onDestroy()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "MainActivity: onSaveInstanceState");

        // Save counter variable current value so it can be restored
        // when activity is recreated
        outState.putInt(COUNTER, counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "MainActivity: onRestoreInstanceState");
    }
}