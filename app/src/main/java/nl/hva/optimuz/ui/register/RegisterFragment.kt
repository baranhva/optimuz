package nl.hva.optimuz.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import nl.hva.optimuz.Configuration
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import nl.hva.optimuz.ui.home.HomeFragment
import nl.hva.optimuz.ui.login.LoginFragment
import nl.hva.optimuz.ui.questionnaire.QuestionnaireFragment
import org.json.JSONObject

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)
        val registerButton: Button = root.findViewById(R.id.register_button)
        val switchButton: Button = root.findViewById(R.id.switch_to_login)
        val main = (activity as MainActivity)

        registerButton.setOnClickListener {
            val firstName = root.findViewById<EditText>(R.id.register_first_name).text.toString()
            val lastName = root.findViewById<EditText>(R.id.register_last_name).text.toString()
            val email = root.findViewById<EditText>(R.id.register_email).text.toString()
            val password = root.findViewById<EditText>(R.id.register_password).text.toString()

            performRegister(firstName, lastName, email, password)
        }

        switchButton.setOnClickListener{
            val homeFragment = HomeFragment.newInstance()
            main.openFragment(homeFragment)
        }

        return root
    }

    private fun performRegister(firstName: String, lastName: String, email: String, password: String) {
        val queue = Volley.newRequestQueue(getActivity()?.applicationContext)
        val url = Configuration.URL + "/patient/register"
        val body = JSONObject()
        body.put("firstName", firstName)
        body.put("lastName", lastName)
        body.put("email", email)
        body.put("password", password)

        val postRequest = JsonObjectRequest(Request.Method.POST, url, body,
                { response ->
                    Toast.makeText(activity, "Successfully created!", Toast.LENGTH_SHORT).show()
                    val loginFragment = LoginFragment.newInstance()
                    (activity as MainActivity).openFragment(loginFragment)
                },
                { error ->
                    Log.e("MyActivity", "REGISTER", error)
                    Toast.makeText(activity, "That didn't work!", Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(postRequest)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }


    companion object {
        fun newInstance(): RegisterFragment = RegisterFragment()
    }

}