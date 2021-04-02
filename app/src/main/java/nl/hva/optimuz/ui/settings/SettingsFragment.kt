package nl.hva.optimuz.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import nl.hva.optimuz.State
import nl.hva.optimuz.ui.login.LoginFragment

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val textView: TextView = root.findViewById(R.id.text_settings)
        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val accountButton: Button = root.findViewById(R.id.account_button)
        val logoutButton: Button = root.findViewById(R.id.logout_button)
        val main = (activity as MainActivity)

        accountButton.setOnClickListener {
//            main.navigateToFragment(R.id.navigation_account)
        }

        logoutButton.setOnClickListener {
            logout()
        }

        return root
    }

    private fun logout() {
        State.loggedIn = false
//        findNavController().popBackStack(R.id.navigation_settings, true) // doesnt work properly
//        (activity as MainActivity).navigateToFragment(R.id.navigation_login)
//        (activity as MainActivity).changeFragment(LoginFragment())
    }
}