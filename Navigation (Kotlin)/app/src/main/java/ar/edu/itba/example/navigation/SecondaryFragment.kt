package ar.edu.itba.example.navigation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class SecondaryFragment : Fragment(R.layout.secondary_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Recover parameter without safe args
        //if (arguments != null) {
        //    val value: Int = arguments.getInt(VALUE_PARAMETER);
        //    Toast.makeText(view.context, "Value: $value", Toast.LENGTH_LONG).show();
        //}

        // Recover parameter with safe args
        val args: SecondaryFragmentArgs by navArgs()
        val value: Int = args.value
        Toast.makeText(view.context, "Value: $value", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val VALUE_PARAMETER = "value"
    }
}