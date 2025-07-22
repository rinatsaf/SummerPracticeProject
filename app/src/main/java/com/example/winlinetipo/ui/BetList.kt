package com.example.winlinetipo.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.winlinetipo.R
import com.example.winlinetipo.viewmodel.BetViewModel

class BetList : Fragment() {

    private var viewModel: BetViewModel? = null
    private var adapter: ArrayAdapter<String>? = null
    private val betTitles = mutableListOf<String>()
    private val betIds = mutableListOf<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.bet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(BetViewModel::class.java)

        val listView = view.findViewById<ListView>(R.id.bet_list_view)
        val button = view.findViewById<Button>(R.id.button_create_bet)

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, betTitles)
        listView.adapter = adapter

        viewModel!!.bets.observe(viewLifecycleOwner) { bets ->
            betTitles.clear()
            betIds.clear()
            bets.forEach {
                betTitles.add("\${it.title} - \${it.amount}₽")
                betIds.add(it.id)
            }
            adapter?.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val betId = betIds[position]
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BetDetail.newInstance(betId))
                .addToBackStack(null)
                .commit()
        }

        button.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CreateBet())
                .addToBackStack(null)
                .commit()
        }
    }
}