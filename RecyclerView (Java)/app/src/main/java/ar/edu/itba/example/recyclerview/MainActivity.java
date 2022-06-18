package ar.edu.itba.example.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ar.edu.itba.example.recyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String> dataSet = new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        for (int i = 1; i <= 50; i++)
            addItem(i);

        adapter = new CustomAdapter(dataSet);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //binding.recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerview.setAdapter(adapter);

        binding.fab.setOnClickListener(view -> {
            addItem(dataSet.size() + 1);
            //adapter.notifyDataSetChanged();
            adapter.notifyItemInserted(dataSet.size());
        });
    }

    private void addItem(int index)
    {
        String itemText = getResources().getString(R.string.item_text, index);
        dataSet.add(itemText);
    }
}