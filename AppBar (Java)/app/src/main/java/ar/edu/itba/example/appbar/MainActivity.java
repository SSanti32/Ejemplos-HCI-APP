package ar.edu.itba.example.appbar;

import android.os.Bundle;

import ar.edu.itba.example.appbar.databinding.ActivityMainBinding;
import ar.edu.itba.example.appbar.databinding.ToolbarMainBinding;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ToolbarMainBinding binding2 = ToolbarMainBinding.bind(binding.getRoot());
        binding2.toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(binding2.toolbar);
    }
}
