package com.example.winlinetipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BetAdapter(
    private val items: List<Bet>,
    private val onClick: (Bet) -> Unit
) : RecyclerView.Adapter<BetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bet = items[position]
        holder.bind(bet)
        holder.itemView.setOnClickListener { onClick(bet) }
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        fun bind(bet: Bet) {
            tvTitle.text = bet.title
        }
    }
}