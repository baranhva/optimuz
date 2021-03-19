package nl.hva.optimuz.adapater

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import nl.hva.optimuz.R
import nl.hva.optimuz.models.Questionnaire
import kotlinx.android.synthetic.main.recycler_questionnaires.view.*
import nl.hva.optimuz.ui.questionnaire.QuestionnaireFragment
import nl.hva.optimuz.ui.questionnaire_recycler.QuestionnaireRecFragment

class QuestionnairesAdapter(
    context: Context?, val questionnaires: List<Questionnaire>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<QuestionnairesAdapter.QuestionnaireViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionnaireViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        var recycler = inflator.inflate(R.layout.recycler_questionnaires, parent, false)
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_questionnaires, parent, false)

        return QuestionnaireViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionnaireViewHolder, position: Int) {
        val questionnaire = questionnaires[position]
        holder.itemView.textViewTitle.text = questionnaire.title
        holder.itemView.textViewIsNew.visibility =
            if (questionnaire.isNew) View.VISIBLE else View.INVISIBLE
    }

    override fun getItemCount() = questionnaires.size

    inner class QuestionnaireViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(questionnaires[position].id)
            }
        }
    }

    interface ClickEventHandler {
        fun forwardClick(id: Int, holder: View)
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

}

