package com.seljabali.practiceproblems.ui.anagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import kotlinx.android.synthetic.main.fragment_anagram.*
import java.util.HashMap

class AnagramFragment : BaseFragment() {

    companion object {
        val TAG = AnagramFragment::class.java.simpleName
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_anagram, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculateButton.setOnClickListener { onCalculateClicked() }
        clearButton.setOnClickListener { onClearClicked() }
        inputATextView.requestFocus()
    }

    private fun isAnagram(textA: String, textB: String): Boolean {
        if (textA.isBlank() or textB.isBlank()) {
            return false
        }
        val charCountMap = HashMap<Char, Int>()
        addToMapCharCount(charCountMap, textA.toCharArray())
        removeFromMapCharCount(charCountMap, textB.toCharArray())
        for (integer in charCountMap.values) {
            if (integer != 0) {
                return false
            }
        }
        return true
    }

    private fun addToMapCharCount(charCountMap: HashMap<Char, Int>, charArray: CharArray) {
        for (i in charArray.indices) {
            val currentCharacter = charArray[i]
            if (charCountMap.containsKey(currentCharacter)) {
                charCountMap[currentCharacter] = charCountMap[currentCharacter]!! + 1
            } else {
                charCountMap[currentCharacter] = 1
            }
        }
    }

    private fun removeFromMapCharCount(charCountMap: HashMap<Char, Int>, charArray: CharArray) {
        for (i in charArray.indices) {
            val currentCharacter = charArray[i]
            if (charCountMap.containsKey(currentCharacter)) {
                charCountMap[currentCharacter] = charCountMap[currentCharacter]!! - 1
            } else {
                charCountMap[currentCharacter] = -1
            }
        }
    }

    private fun resetTexts() {
        inputATextView.setText("")
        inputBTextView.setText("")
        inputATextView.requestFocus()
    }

    private fun onCalculateClicked() {
        val textA = inputATextView.text.toString()
        val textB = inputBTextView.text.toString()
        val isAnagram = isAnagram(textA, textB)
        Toast.makeText(context, isAnagram.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onClearClicked() {
        resetTexts()
    }
}