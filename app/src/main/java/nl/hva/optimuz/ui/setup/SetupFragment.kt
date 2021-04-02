package nl.hva.optimuz.ui.setup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.paris.Paris
import nl.hva.optimuz.*
import nl.hva.optimuz.ui.home.HomeFragment
import nl.hva.optimuz.ui.questionnaire.QuestionnaireFragment
import java.text.ParseException
import java.text.SimpleDateFormat

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

            val feedbackMessage = completeSetup(name, dateOfBirth, gender)

            if (feedbackMessage === null){
//                findNavController().popBackStack(R.id.navigation_setup, true)
                val homeFragment = HomeFragment.newInstance()
                main.openFragment(homeFragment)
            }else{
                feedback.text = feedbackMessage
            }

        }

        return root
    }

    private fun toggleGenderButton(activeButton: Button, inactiveButton: Button) {
        Paris.styleBuilder(activeButton).add(R.style.ToggleButtonActive).apply()
        Paris.styleBuilder(inactiveButton).add(R.style.ToggleButtonInactive).apply()
    }

    private fun completeSetup(name: String, dateOfBirth: String, gender: String) : String? {

        if (name == "") return "Please fill in your name"
        else if (dateOfBirth == "") return "Please fill in your date of birth"
        else if (gender == "") return "Please select your gender"

        try {
            val formattedDate = SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth)
            Account.name = name
            Account.dateOfBirth = formattedDate
            Account.gender = gender
        } catch (e: ParseException) {
            return "Please enter a valid date of birth (xx-xx-xxxx)"
        }

        return null

    }

    companion object {
        fun newInstance(): SetupFragment = SetupFragment()
    }




}

