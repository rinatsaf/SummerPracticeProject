package com.example.winlinetipo.ui

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.winlinetipo.R
import com.example.winlinetipo.viewmodel.BetViewModel

class BetDetail : Fragment() {

    private var viewModel: BetViewModel? = null
    private var betId: Int = 0

    companion object {
        fun newInstance(id: Int): BetDetail {
            val fragment = BetDetail()
            fragment.arguments = Bundle().apply { putInt("id", id) }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        betId = arguments?.getInt("id") ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.bet_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(BetViewModel::class.java)
        val bet = viewModel!!.getBetById(betId)

        view.findViewById<TextView>(R.id.text_title).text = bet?.title
        view.findViewById<TextView>(R.id.text_amount).text = "Сумма: \${bet?.amount}₽"
        view.findViewById<TextView>(R.id.text_odds).text = "Коэффициент: \${bet?.odds}"
        view.findViewById<TextView>(R.id.text_description).text = bet?.description
    }
}