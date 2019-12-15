package com.seljabali.practiceproblems.ui.stocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import kotlinx.android.synthetic.main.fragment_stocks.*

class StocksFragment : BaseFragment() {

    companion object {
        val TAG = StocksFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_stocks, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profitButton.setOnClickListener { onFindProfitClicked() }
        clearButton.setOnClickListener { onClearClicked() }
        inputATextView.requestFocus()
        onFindProfitClicked()
    }

    private fun onFindProfitClicked() {
        val inputtedText = inputATextView.text.toString()
        if (inputtedText.isBlank()) return
        val stockPrices = inputtedText.split(",", ignoreCase = true).map { it.trim().toInt() }
        outputTextView.text = getHighestProfitSlowly(stockPrices).toString()

    }

    private fun getHighestProfitSlowly(stockPrices: List<Int>): Int {
        if (stockPrices.size <= 1) return 0
        var highestProfit = 0
        for (buyIndex in 0 until stockPrices.size) {
            val currentBuyPrice = stockPrices[buyIndex]
            for (sellIndex in buyIndex until stockPrices.size) {
                val currentSellPrice = stockPrices[sellIndex]
                val currentProfit = currentSellPrice - currentBuyPrice
                if (currentProfit > highestProfit) {
                    highestProfit = currentProfit
                }
            }
        }
        return highestProfit
    }

    private fun onClearClicked() {
        outputTextView.text = ""
        inputATextView.setText("")
        inputATextView.requestFocus()
    }
}