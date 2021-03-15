package nl.hva.optimuz.ui.login

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
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import nl.hva.optimuz.Configuration
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import nl.hva.optimuz.State

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

//            performLogin(loginEmail, loginPassword)
            if (loginEmail == "email" && loginPassword == "pass"){
                State.loggedIn = true
                findNavController().popBackStack(R.id.navigation_login, true)
                main.navigateToFragment(R.id.navigation_home)
            } else{
                feedback.text = "Invalid email/password combination"
            }
        }

        switchButton.setOnClickListener {
            main.navigateToFragment(R.id.navigation_register)
        }

        return root
    }

    private fun performLogin(email: String, password: String) {
        val queue = Volley.newRequestQueue(getActivity()?.applicationContext)
        val url = Configuration.URL + "/auth/login"
        Toast.makeText(activity, "Called", Toast.LENGTH_SHORT).show()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.POST, url,
                { token ->
                    State.token = token
                    State.loggedIn = true
                    findNavController().popBackStack()
                    (activity as MainActivity).navigateToFragment(R.id.navigation_home)
                },
                { error ->
                    Log.e("MyActivity", "LOGIN", error)
                    Toast.makeText(activity, "That didn't work!", Toast.LENGTH_SHORT).show()
                })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}