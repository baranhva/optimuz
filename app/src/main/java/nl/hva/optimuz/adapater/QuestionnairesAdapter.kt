package nl.hva.optimuz.adapater

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import nl.hva.optimuz.R
import nl.hva.optimuz.models.Questionnaire
import kotlinx.android.synthetic.main.recycler_questionnaires.view.*

class QuestionnairesAdapter (val questionnaires : List<Questionnaire>) : RecyclerView.Adapter<QuestionnairesAdapter.QuestionnaireViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionnaireViewHolder {
        return QuestionnaireViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_questionnaires, parent, false)
        )

    }

    override fun getItemCount() = questionnaires.size

    override fun onBindViewHolder(holder: QuestionnaireViewHolder, position: Int) {
        val questionnaire = questionnaires[position]
        holder.view.textViewTitle.text = questionnaire.title
        holder.view.textViewIsNew.visibility = if(questionnaire.isNew) View.VISIBLE else View.INVISIBLE

    }


    class QuestionnaireViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        init {

           print("message: String")
        }
    }
}