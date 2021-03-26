package nl.hva.optimuz.ui.medicine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

class MedicineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_medicine, container, false)
        val main = (activity as MainActivity)
        val saveButton : Button = root.findViewById(R.id.save_button)

        // populate dropdown
        val unitDropdown: Spinner = root.findViewById(R.id.medicine_unit)
        ArrayAdapter.createFromResource(
                main,
                R.array.medicine_units,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            unitDropdown.adapter = adapter
        }

        saveButton.setOnClickListener{
            val name = root.findViewById<EditText>(R.id.medicine_name).text.toString()
            val amount_of_unit = root.findViewById<EditText>(R.id.medicine_unit_amount).text.toString().toFloat()
            val unit = root.findViewById<Spinner>(R.id.medicine_unit).selectedItem.toString()
            val amount_in_package = root.findViewById<EditText>(R.id.medicine_amount).text.toString().toInt()
            saveMedicine(name, amount_of_unit, unit, amount_in_package)
        }

        return root
    }

    fun saveMedicine(name: String, amount_of_unit: Float, unit: String, amount_in_package: Int){
        Log.d("TEST", "$name $amount_of_unit $unit $amount_in_package")
        (activity as MainActivity).navigateToFragment(R.id.navigation_settings)
    }

}

