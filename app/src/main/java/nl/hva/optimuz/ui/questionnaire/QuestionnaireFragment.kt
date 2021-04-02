package nl.hva.optimuz.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import nl.hva.optimuz.R
import nl.hva.optimuz.ui.home.HomeFragment


class QuestionnaireFragment : Fragment() {

    private lateinit var viewModel: QuestionnaireViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val qf = inflater.inflate(R.layout.questionnaire_fragment, container, false)

        val bundle = this.arguments
        val message = bundle?.get("id_key")

        val txt = qf.findViewById(R.id.textTestQuest) as TextView
        txt.text = "$message"

        Toast.makeText(activity, "Item $message created!!", Toast.LENGTH_SHORT).show()

        return qf
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestionnaireViewModel::class.java)
        // TODO: Use the ViewModel

    }

    companion object {
        fun newInstance(): QuestionnaireFragment = QuestionnaireFragment()
    }

}

