package nl.hva.optimuz.models


data class Questionnaire (

    val id: Int,
    val title: String,
    val isNew: Boolean,
    val questions: List<Question>

)

