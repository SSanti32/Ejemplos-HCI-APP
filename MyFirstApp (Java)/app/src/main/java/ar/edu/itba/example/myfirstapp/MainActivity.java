package ar.edu.itba.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ar.edu.itba.example.myfirstapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "ar.edu.itba.example.myfirstapp.MESSAGE";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.send.setOnClickListener(view -> {
            sendMessage();
        });
        // Callback can be specified in Button layout using
        // android:onClick attribute if calling method has
        // the following signature public void sendMessage(View view)
    }

    // Called when the user taps the Send button
    public void sendMessage() {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = binding.editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}