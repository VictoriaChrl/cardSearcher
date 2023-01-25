package com.example.testapp.utils

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R


class CardAdapter: RecyclerView.Adapter<CardAdapter.Holder>() {

    private var cardList: List<String> = emptyList()

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val cardBin = view.findViewById<TextView>(R.id.cardBin)

        fun bind(card: String) {
            cardBin.text = card
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_card))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val newList = cardList[position]
        newList.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun updateList(newList: List<String>){
        cardList = newList
    }
}

