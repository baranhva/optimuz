package nl.hva.optimuz.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.hva.optimuz.Account

class AccountViewModel : ViewModel() {

    private val _name = MutableLiveData<String>().apply {
        value = Account.name
    }
    private val _dateOfBirth = MutableLiveData<String>().apply {
        value = Account.dateOfBirth.toString()
    }
    private val _gender = MutableLiveData<String>().apply {
        value = Account.gender
    }

    val name: LiveData<String> = _name
    val dateOfBirth: LiveData<String> = _dateOfBirth
    val gender: LiveData<String> = _gender

}