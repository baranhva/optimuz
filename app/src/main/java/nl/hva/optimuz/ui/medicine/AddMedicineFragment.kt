package nl.hva.optimuz.ui.medicine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_add_medicine.*
import nl.hva.optimuz.Configuration
import nl.hva.optimuz.MainActivity
import nl.hva.optimuz.R
import nl.hva.optimuz.State
import nl.hva.optimuz.ui.register.RegisterFragment
import org.json.JSONObject
import kotlin.jvm.Throws

// TODO: add validation

class AddMedicineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_medicine, container, false)
        val main = (activity as MainActivity)
        val addMedicineButton : Button = root.findViewById(R.id.add_medicine_button)

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

        addMedicineButton.setOnClickListener{
            val name = root.findViewById<EditText>(R.id.medicine_name).text.toString()
            val amountOfUnit = root.findViewById<EditText>(R.id.medicine_unit_amount).text.toString().toFloat()
            val unit = root.findViewById<Spinner>(R.id.medicine_unit).selectedItem.toString()
            val amountPerPackage = root.findViewById<EditText>(R.id.medicine_amount).text.toString().toInt()

            addMedicine(name, amountOfUnit, unit, amountPerPackage)
        }

        return root
    }

    private var isBusyAddingMedicine = false

    private fun addMedicine(name: String, amountOfUnit: Float, unit: String, amountPerPackage: Int){

        if (!isBusyAddingMedicine) {
            isBusyAddingMedicine = true

            val queue = Volley.newRequestQueue(activity?.applicationContext)
            val url = Configuration.URL + "/patient/medicine"
            val body = JSONObject()
            body.put("name", name)
            body.put("amountOfUnit", amountOfUnit)
            body.put("unit", unit)
            body.put("amountPerPackage", amountPerPackage)

            Log.d("Medicine", body.toString())

            val postRequest = object : JsonObjectRequest(Request.Method.POST, url, body,
                    { response ->
                        // do something
                        (activity as MainActivity).openFragment(MedicineOverviewFragment.newInstance())
                        isBusyAddingMedicine = false
                    },
                    { error ->
                        Log.e("Medicine", error.toString())
                        Toast.makeText(activity, "That didn't work!", Toast.LENGTH_SHORT).show()
                        isBusyAddingMedicine = false
                    }
            ) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Content-Type", "application/json")
                    headers.put("Authorization", "Bearer ${State.accessToken}")
                    return headers
                }
            }

            queue.add(postRequest)

        } else {
            Log.d("Medicine", "Already adding medicine...")
        }

    }

    companion object {
        fun newInstance(): AddMedicineFragment = AddMedicineFragment()
    }


}

