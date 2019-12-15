package com.seljabali.practiceproblems.ui.compression

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import kotlinx.android.synthetic.main.fragment_compress_text.*

class CompressTextFragment : BaseFragment() {

    companion object {
        val TAG = CompressTextFragment::class.java.simpleName
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_compress_text, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compressButton.setOnClickListener { onCompressClicked() }
        clearButton.setOnClickListener { onClearClicked() }
        inputATextView.requestFocus()
        onCompressClicked()
    }

    private fun getCompressedText(count: Int, letter: Char): String = "$count$letter"

    private fun getEncoding(inputString: String): String {
        if (inputString.isBlank()) return ""
        if (inputString.length == 1) return getCompressedText(1, inputString[0])
        val lowerCasedInput = inputString.toLowerCase()
        var resultEncoding = ""
        var currentLetter = lowerCasedInput[0]
        var currentLetterCount = 0
        for (index in lowerCasedInput.indices) {
            val letter = lowerCasedInput[index]
            if (index == 0) {
                currentLetter = letter
                currentLetterCount = 1
                continue
            }
            val isAtLastIndex = index == lowerCasedInput.length - 1
            if (currentLetter == letter) {
                currentLetterCount++
                if (isAtLastIndex) {
                    resultEncoding += getCompressedText(currentLetterCount, currentLetter)
                    break
                }
                continue
            }
            resultEncoding += getCompressedText(currentLetterCount, currentLetter)
            currentLetter = letter
            currentLetterCount = 1
            if (isAtLastIndex) {
                resultEncoding += getCompressedText(1, currentLetter)
                break
            }
        }
        return resultEncoding
    }

    private fun onCompressClicked() {
        val result = getEncoding(inputATextView.text.toString())
        outputTextView.text = result

    }

    private fun onClearClicked() {
        outputTextView.text = ""
        inputATextView.setText("")
        inputATextView.requestFocus()
    }
}