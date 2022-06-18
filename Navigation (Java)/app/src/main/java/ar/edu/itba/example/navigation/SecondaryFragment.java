package ar.edu.itba.example.navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondaryFragment extends Fragment {

    public static final String VALUE_PARAMETER = "value";

    public SecondaryFragment() {
        super(R.layout.secondary_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Recover parameter without safe args
        //if (getArguments() != null) {
        //    int value = getArguments().getInt(VALUE_PARAMETER);
        //    Toast.makeText(view.getContext(), "Value: " + value, Toast.LENGTH_LONG).show();
        //}

        // Recover parameter with safe args
        int value = SecondaryFragmentArgs.fromBundle(getArguments()).getValue();
        Toast.makeText(view.getContext(), "Value: " + value, Toast.LENGTH_LONG).show();
    }
}