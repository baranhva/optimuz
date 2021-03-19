package nl.hva.optimuz.ui.login

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

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val loginButton: Button = root.findViewById(R.id.login_button)
        val switchButton: Button = root.findViewById(R.id.switch_to_register)
        val main = (activity as MainActivity)
        val feedback = root.findViewById<TextView>(R.id.feedback)

        loginButton.setOnClickListener {
            val email = root.findViewById<EditText>(R.id.login_email).text.toString()
            val password = root.findViewById<EditText>(R.id.login_password).text.toString()

            performLogin(email, password)
        }

        switchButton.setOnClickListener {
            main.navigateToFragment(R.id.navigation_register)
        }

        return root
    }

    private var isBusyLoggingIn = false

    private fun performLogin(email: String, password: String) {
        if (!isBusyLoggingIn) {
            isBusyLoggingIn = true

            val queue = Volley.newRequestQueue(getActivity()?.applicationContext)
            val url = Configuration.URL + "/auth/login"
            val body = JSONObject()
            body.put("email", email)
            body.put("password", password)

            val postRequest = JsonObjectRequest(Request.Method.POST, url, body,
                { response ->
                    State.accessToken = response.getString("accessToken")
                    State.refreshToken = response.getString("refreshToken")
                    State.loggedIn = true
                    findNavController().popBackStack()
                    (activity as MainActivity).navigateToFragment(R.id.navigation_home)
                    isBusyLoggingIn = false
                },
                { error ->
                    Log.e("MyActivity", "LOGIN", error)
                    Toast.makeText(activity, "That didn't work!", Toast.LENGTH_SHORT).show()
                    isBusyLoggingIn = false
                }
            )

            queue.add(postRequest)
        } else {
            Log.d("Login", "Already busy with logging in")
        }
    }

    private fun performLoginWithoutBackend(email: String, password: String) {
        val main = (activity as MainActivity)

        if (email == "email" && password == "pass") {
            State.loggedIn = true
            if (false) { // if new user...
                main.navigateToFragment(R.id.navigation_setup)
            } else {
                findNavController().popBackStack(R.id.navigation_login, true)
                main.navigateToFragment(R.id.navigation_home)
            }
        } else {
//            feedback.text = "Invalid email/password combination"
        }
    }


}

