package ar.edu.itba.example.myfirstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.edu.itba.example.myfirstapp.databinding.ActivityDisplayMessageBinding

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDisplayMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Set the message as TextView layout text.
        binding.textView.text = message
    }
}