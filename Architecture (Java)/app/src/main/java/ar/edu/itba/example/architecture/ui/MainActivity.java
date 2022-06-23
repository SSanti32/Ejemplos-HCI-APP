package ar.edu.itba.example.architecture.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import ar.edu.itba.example.architecture.R;
import ar.edu.itba.example.architecture.databinding.ActivityMainBinding;
import ar.edu.itba.example.architecture.ui.room.RoomFragment;
import ar.edu.itba.example.architecture.ui.room.RoomListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getAll.setOnClickListener(v -> replaceFragment(new RoomListFragment(), false));

        binding.add.setOnClickListener(v -> replaceFragment(new RoomFragment(), true));

        replaceFragment(new RoomListFragment(), true);
    }

    public void replaceFragment(Fragment newFragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.commit();
    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    public void showProgressBar() {
        binding.loading.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        binding.loading.setVisibility(View.GONE);
    }
}