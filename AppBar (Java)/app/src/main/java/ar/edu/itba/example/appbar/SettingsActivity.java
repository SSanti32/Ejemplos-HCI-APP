package ar.edu.itba.example.appbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import ar.edu.itba.example.appbar.databinding.ActivitySettingsBinding;
import ar.edu.itba.example.appbar.databinding.ToolbarMainBinding;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySettingsBinding binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ToolbarMainBinding binding2 = ToolbarMainBinding.bind(binding.getRoot());
        setSupportActionBar(binding2.toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareItem.setVisible(false);

        MenuItem settingsItem = menu.findItem(R.id.action_settings);
        settingsItem.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }
}
