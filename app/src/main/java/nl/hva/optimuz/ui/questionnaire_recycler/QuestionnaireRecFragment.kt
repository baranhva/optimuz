package nl.hva.optimuz.ui.questionnaire_recycler

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.questionnaire_rec_fragment.*
import nl.hva.optimuz.R
import nl.hva.optimuz.adapater.QuestionnairesAdapter
import nl.hva.optimuz.models.Question
import nl.hva.optimuz.models.Questionnaire

class QuestionnaireRecFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionnaireRecFragment()
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

        val questions = listOf( Question(1, "How old are you?"))

        val questionnaires = listOf(
                Questionnaire(1, "Health Questions", true,questions),
                Questionnaire(2, "Sport Questions", true, questions) ,
                Questionnaire(3, "Diet Questions", false, questions)
        )

        recyclerViewQuestionnaires.layoutManager = LinearLayoutManager(activity)
        recyclerViewQuestionnaires.adapter=QuestionnairesAdapter(questionnaires)


    }

}