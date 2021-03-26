package nl.hva.optimuz.ui.medicine_scheme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R

class MedicineSchemeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_medicine, container, false)
        val main = (activity as MainActivity)
//        val saveButton : Button = root.findViewById(R.id.save_button)



        return root
    }


}

