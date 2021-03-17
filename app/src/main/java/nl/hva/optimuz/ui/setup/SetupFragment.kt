package nl.hva.optimuz.ui.setup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.Volley
import nl.hva.optimuz.Configuration
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import nl.hva.optimuz.State
import org.json.JSONObject

class SetupFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_setup, container, false)
        val saveButton: Button = root.findViewById(R.id.save_button)
        val feedback = root.findViewById<TextView>(R.id.feedback)
        val main = (activity as MainActivity)

        saveButton.setOnClickListener(){
            findNavController().popBackStack(R.id.navigation_setup, true)
            main.navigateToFragment(R.id.navigation_home)
        }

        return root
    }

}

