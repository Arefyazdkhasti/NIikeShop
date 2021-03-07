package com.example.nikeshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.dataClass.DataQuestion
import kotlinx.android.synthetic.main.item_recycler_question.view.*

class RecyclerQuestionAdapter(
    private val data: List<DataQuestion>
) : RecyclerView.Adapter<RecyclerQuestionAdapter.QuestionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder =
        QuestionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_recycler_question,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtQuestion = itemView.txt_question_recycler_question
        private val txtAnswer = itemView.txt_answer_recycler_question

        fun setData(data: DataQuestion) {
            txtQuestion.text = data.question
            txtAnswer.text = data.answer
        }
    }
}