package nl.hva.optimuz.ui.medicine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.airbnb.paris.Paris
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import nl.hva.optimuz.Configuration
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import org.json.JSONObject

// TODO: get medicines from backend

class MedicineOverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_medicine_overview, container, false)
        val main = (activity as MainActivity)
        val addMedicineButton : FloatingActionButton = root.findViewById(R.id.add_medicine_button)
        val container : TableLayout = root.findViewById(R.id.medicineView)

        val dummyData = arrayOf("Ibuprofen", "Paracetamol")

        for (medicine in dummyData){
            val medicineButton = Button(main)
            medicineButton.text = medicine
            Paris.styleBuilder(medicineButton).add(R.style.Widget_MaterialComponents_Button).apply()
            container.addView(medicineButton)
            medicineButton.setOnClickListener{
                Log.d("Medicine", medicine)
            }
        }

        addMedicineButton.setOnClickListener {
            main.navigateToFragment(R.id.navigation_add_medicine)
        }

        return root
    }

}

