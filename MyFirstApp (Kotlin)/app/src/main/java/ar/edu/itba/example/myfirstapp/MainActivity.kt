package ar.edu.itba.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.edu.itba.example.myfirstapp.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "ar.edu.itba.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.send.setOnClickListener { sendMessage() }
        // Callback can be specified in Button layout using
        // android:onClick attribute if calling method has
        // the following signature public void sendMessage(View view)
    }

    // Called when the user taps the Send button
    private fun sendMessage() {
        val message = binding.editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}