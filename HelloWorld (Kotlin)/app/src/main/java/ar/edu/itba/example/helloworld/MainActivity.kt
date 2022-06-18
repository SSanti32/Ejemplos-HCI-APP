package ar.edu.itba.example.helloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.edu.itba.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}