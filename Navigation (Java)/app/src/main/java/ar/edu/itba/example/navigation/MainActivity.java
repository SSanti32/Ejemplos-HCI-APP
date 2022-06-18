package ar.edu.itba.example.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Button;

import ar.edu.itba.example.navigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Show fragment without Navigation
        //if (savedInstanceState == null) {
        //    getSupportFragmentManager().beginTransaction()
        //            .setReorderingAllowed(true)
        //            .add(R.id.nav_host_fragment, MainFragment.class, null)
        //            .commit();
        //}

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            // Required to avoid App crash when configuration changes.
            navController.setGraph(R.navigation.nav_graph);
        }

        binding.toggleButton.setOnClickListener(view -> {
            Button button = (Button)view;

            if (button.getText() == getResources().getString(R.string.show_main))
            {
                button.setText(R.string.show_secondary);

                navController.navigate(R.id.mainFragment);
            }
            else
            {
                button.setText(R.string.show_main);

                // Show fragment using Navigation without safe args
                //Bundle bundle = new Bundle();
                //bundle.putInt("value", 10);
                //navController.navigate(R.id.action_mainFragment_to_secondaryFragment, bundle);

                // Show fragment using Navigation with safe args
                MainFragmentDirections.ActionMainFragmentToSecondaryFragment direction =
                        MainFragmentDirections.actionMainFragmentToSecondaryFragment(10);
                navController.navigate(direction);
            }
        });
    }
}