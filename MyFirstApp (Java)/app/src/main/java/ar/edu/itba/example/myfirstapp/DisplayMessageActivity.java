package ar.edu.itba.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ar.edu.itba.example.myfirstapp.databinding.ActivityDisplayMessageBinding;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDisplayMessageBinding binding = ActivityDisplayMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Set the message as TextView layout text.
        binding.textView.setText(message);
    }
}
