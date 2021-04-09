package nl.hva.optimuz.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import nl.hva.optimuz.Account
import nl.hva.optimuz.R
import nl.hva.optimuz.ui.register.RegisterFragment

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_account, container, false)

        val name: TextView = root.findViewById(R.id.account_name)
        val dateOfBirth: TextView = root.findViewById(R.id.account_date_of_birth)
        val gender: TextView = root.findViewById(R.id.account_gender)

        accountViewModel.name.observe(viewLifecycleOwner, Observer {
            name.text = it
        })
        accountViewModel.dateOfBirth.observe(viewLifecycleOwner, Observer {
            dateOfBirth.text = it
        })
        accountViewModel.gender.observe(viewLifecycleOwner, Observer {
            gender.text = it
        })

        return root
    }

    companion object {
        fun newInstance(): AccountFragment = AccountFragment()
    }

}