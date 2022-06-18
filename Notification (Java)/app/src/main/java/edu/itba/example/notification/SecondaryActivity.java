package edu.itba.example.notification;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.itba.example.notification.databinding.ActivitySecondaryBinding;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySecondaryBinding binding = ActivitySecondaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.textView.setText(intent.getStringExtra(MainActivity.EXTRA_NOTIFICATION_TITLE));
    }
}
