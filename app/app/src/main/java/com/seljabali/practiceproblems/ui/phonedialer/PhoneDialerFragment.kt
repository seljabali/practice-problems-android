package com.seljabali.practiceproblems.ui.phonedialer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import kotlinx.android.synthetic.main.fragment_phone_dialer.*
import java.util.ArrayList
import java.util.HashMap

class PhoneDialerFragment : BaseFragment() {

    companion object {
        val TAG = PhoneDialerFragment::class.java.simpleName
    }

    lateinit var numberToCharactersMap: HashMap<Int, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numberToCharactersMap = HashMap()
        numberToCharactersMap[2] = listOf("A", "B", "C")
        numberToCharactersMap[3] = listOf("D", "E", "F")
        numberToCharactersMap[4] = listOf("G", "H", "I")
        numberToCharactersMap[5] = listOf("J", "K", "L")
        numberToCharactersMap[6] = listOf("M", "N", "O")
        numberToCharactersMap[7] = listOf("P", "Q", "R", "S")
        numberToCharactersMap[8] = listOf("T", "U", "V")
        numberToCharactersMap[9] = listOf("W", "X", "Y", "Z")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_dialer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearButton.setOnClickListener { onClearClicked() }
        calculateButton.setOnClickListener { onCalculateClicked() }
    }

    override fun onStart() {
        super.onStart()
        inputATextView!!.requestFocus()
    }

    private fun resetTexts() {
        inputATextView!!.setText("")
        resultsTextView!!.text = ""
        inputATextView!!.requestFocus()
    }

    private fun getPossibleWordsEntered(textA: String): List<String> {
        if (textA.isBlank()) {
            return ArrayList()
        }
        val listsOfLettersPerNumbersEntered = getListsOfLettersPerNumbers(textA)
        return getPossibleWordsEntered(listsOfLettersPerNumbersEntered, "")
    }

    private fun getPossibleWordsEntered(
        remainingLetterLists: List<List<String>>?,
        intermediaryWord: String
    ): List<String> {
        if (remainingLetterLists?.isEmpty() != false) {
            return listOf(intermediaryWord)
        }
        val intermediaryWords = ArrayList<String>()
        val currentLetterList = remainingLetterLists[0]
        for (i in currentLetterList.indices) {
            val currentLetter = currentLetterList[i]
            intermediaryWords.addAll(
                getPossibleWordsEntered(
                    remainingLetterLists.subList(
                        1,
                        remainingLetterLists.size
                    ), intermediaryWord + currentLetter
                )
            )
        }
        return intermediaryWords
    }

    private fun getListsOfLettersPerNumbers(numbersAsText: String): ArrayList<List<String>>? {
        if (numbersAsText.isEmpty()) {
            return null
        }
        val listOfLetters = ArrayList<List<String>>()
        for (i in numbersAsText.indices) {
            val currentNumber = Integer.valueOf(numbersAsText.substring(i, i + 1))
            if (numberToCharactersMap.containsKey(currentNumber)) {
                listOfLetters.add(numberToCharactersMap[currentNumber]!!)
            }
        }
        return listOfLetters
    }

    internal fun onClearClicked() {
        resetTexts()
    }

    internal fun onCalculateClicked() {
        val textA = inputATextView!!.text.toString()
        if (textA.isEmpty()) {
            Toast.makeText(context, R.string.enter_value, Toast.LENGTH_SHORT).show()
            return
        }
        hideKeyboard(context, view)
        val possibleTextsEntered = getPossibleWordsEntered(textA)
        resultsTextView!!.text = getListFlattened(", ", possibleTextsEntered)
    }

    private fun hideKeyboard(context: Context?, view: View?) {
        if (context == null || view == null) {
            return
        }
        val inputMethodManager = context.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getListFlattened(delimiter: String, stringList: List<String>): String {
        if (stringList.isEmpty()) {
            return ""
        }
        val stringBuilder = StringBuilder()
        for (i in stringList.indices) {
            val currentString = stringList[i]
            if (currentString.isNotEmpty()) {
                stringBuilder.append(currentString)
                if (i + 1 != stringList.size && (stringList[i + 1]).isNotEmpty()) {
                    stringBuilder.append(delimiter)
                }
            }
        }
        return stringBuilder.toString()
    }
}