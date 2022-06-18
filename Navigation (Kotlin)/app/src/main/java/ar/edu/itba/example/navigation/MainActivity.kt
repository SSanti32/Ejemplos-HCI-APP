package ar.edu.itba.example.navigation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ar.edu.itba.example.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show fragment without Navigation
        //if (savedInstanceState == null) {
        //    supportFragmentManager.beginTransaction()
        //        .setReorderingAllowed(true)
        //        .add(R.id.nav_host_fragment, MainFragment::class.java, null)
        //        .commit()
        //}

        val navHostFragment: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment?.navController
        // Required to avoid App crash when configuration changes.
        navController?.setGraph(R.navigation.nav_graph)

        binding.toggleButton.setOnClickListener { view ->
            val button = view as Button
            if (button.text === resources.getString(R.string.show_main)) {
                button.setText(R.string.show_secondary)
                navController?.navigate(R.id.mainFragment)
            } else {
                button.setText(R.string.show_main)

                // Show fragment using Navigation without safe args
                //Bundle bundle = new Bundle();
                //bundle.putInt("value", 10);
                //navController.navigate(R.id.action_mainFragment_to_secondaryFragment, bundle);

                // Show fragment using Navigation with safe args
                val direction = MainFragmentDirections.actionMainFragmentToSecondaryFragment(10)
                navController?.navigate(direction)
            }
        }
    }
}