package nl.hva.optimuz.ui.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import nl.hva.optimuz.State
import nl.hva.optimuz.ui.home.HomeViewModel

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val loginButton: Button = root.findViewById(R.id.login_button)
        val switchButton: Button = root.findViewById(R.id.switch_to_register)
        val feedback = root.findViewById<TextView>(R.id.feedback)
        val main = (activity as MainActivity)

        loginButton.setOnClickListener {
            val loginEmail = root.findViewById<EditText>(R.id.login_email).text.toString()
            val loginPassword = root.findViewById<EditText>(R.id.login_password).text.toString()

            if (loginEmail == "email" && loginPassword == "pass"){
                State.loggedIn = true
                main.switchFragment(R.id.navigation_home)
            }else{
                feedback.text = "Invalid email/password combination"
            }
        }

        switchButton.setOnClickListener {
            main.switchFragment(R.id.navigation_register)
        }


        return root

    }
}