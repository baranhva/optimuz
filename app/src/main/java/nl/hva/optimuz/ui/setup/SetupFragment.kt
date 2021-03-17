package nl.hva.optimuz.ui.setup

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import androidx.navigation.fragment.findNavController
import com.airbnb.paris.Paris
import com.android.volley.toolbox.Volley
import nl.hva.optimuz.Configuration
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import nl.hva.optimuz.State
import org.json.JSONObject
import kotlin.properties.Delegates

class SetupFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_setup, container, false)

        val genderMaleButton: Button = root.findViewById(R.id.gender_male_button)
        val genderFemaleButton: Button = root.findViewById(R.id.gender_female_button)
        val saveButton: Button = root.findViewById(R.id.save_button)
        val feedback = root.findViewById<TextView>(R.id.feedback)
        val main = (activity as MainActivity)

        var gender = ""

        genderMaleButton.setOnClickListener() {
            toggleGenderButton(genderMaleButton, genderFemaleButton)
            gender = "male"
        }
        genderFemaleButton.setOnClickListener(){
            toggleGenderButton(genderFemaleButton, genderMaleButton)
            gender = "female"
        }

        saveButton.setOnClickListener(){
            val name = root.findViewById<EditText>(R.id.name).text.toString()
            val dateOfBirth = root.findViewById<EditText>(R.id.date_of_birth).text.toString()
            if (name == "") feedback.text = "Please fill in your name"
            else if (dateOfBirth == "") feedback.text = "Please fill in your date of birth"
            else if (gender == "") feedback.text = "Please select your gender"
            else{
                findNavController().popBackStack(R.id.navigation_setup, true)
                main.navigateToFragment(R.id.navigation_home)
            }

        }

        return root
    }

    private fun toggleGenderButton(activeButton: Button, inactiveButton: Button){
        Paris.styleBuilder(activeButton).add(R.style.ToggleButtonActive).apply()
        Paris.styleBuilder(inactiveButton).add(R.style.ToggleButtonInactive).apply()
    }




}

