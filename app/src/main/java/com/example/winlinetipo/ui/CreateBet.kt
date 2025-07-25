package com.example.winlinetipo.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.winlinetipo.R
import com.example.winlinetipo.model.Bet
import com.example.winlinetipo.viewmodel.BetViewModel

class CreateBet : Fragment() {

    private var viewModel: BetViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.create_bet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(BetViewModel::class.java)

        val title = view.findViewById<EditText>(R.id.input_title)
        val amount = view.findViewById<EditText>(R.id.input_amount)
        val odds = view.findViewById<EditText>(R.id.input_odds)
        val description = view.findViewById<EditText>(R.id.input_description)
        val button = view.findViewById<Button>(R.id.button_save_bet)

        button.setOnClickListener {
            val bet = Bet(
                id = (0..999999).random(),
                title = title.text.toString(),
                amount = amount.text.toString().toDoubleOrNull() ?: 0.0,
                odds = odds.text.toString().toDoubleOrNull() ?: 1.0,
                description = description.text.toString()
            )
            viewModel!!.addBet(bet)
            parentFragmentManager.popBackStack()
        }
    }
}