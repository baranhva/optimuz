package nl.hva.optimuz.ui.questionnaire_recycler

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.questionnaire_rec_fragment.*
import nl.hva.optimuz.R
import nl.hva.optimuz.adapter.QuestionnairesAdapter
import nl.hva.optimuz.models.Question
import nl.hva.optimuz.models.Questionnaire
import nl.hva.optimuz.ui.questionnaire.QuestionnaireFragment
import nl.hva.optimuz.ui.setup.SetupFragment


class QuestionnaireRecFragment : Fragment(), QuestionnairesAdapter.OnItemClickListener {

    override fun onItemClick(id: Int) {
        Toast.makeText(getActivity(), "Item $id clicked", Toast.LENGTH_SHORT).show()

        val bundleobj = Bundle()
        bundleobj.putCharSequence("id_key", "$id")

        val fragment = QuestionnaireFragment()
        fragment.setArguments(bundleobj)

        val fragmentManager: FragmentManager = requireActivity().getSupportFragmentManager()
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(((view as ViewGroup).parent as View).id, fragment, "tag")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private lateinit var viewModel: QuestionnaireRecViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.questionnaire_rec_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestionnaireRecViewModel::class.java)
        // TODO: Use the ViewModel

        val questions = listOf(Question(1, "How old are you?"))

        val questionnaires = listOf(
            Questionnaire(76766764, "Health Questions", true, questions),
            Questionnaire(27853, "Sport Questions", true, questions),
            Questionnaire(394, "Diet Questions", false, questions)
        )

        recyclerViewQuestionnaires.layoutManager = LinearLayoutManager(activity)
        recyclerViewQuestionnaires.adapter = QuestionnairesAdapter(context, questionnaires, this)

        val itemOnClick: (View, Int, Int) -> Unit = { view, position, type ->
            Log.d(TAG, "test")
        }

    }

    interface DataPassListener {
        fun passData(data: String?)
    }

    companion object {
        fun newInstance(): QuestionnaireRecFragment = QuestionnaireRecFragment()
    }


}