package com.seljabali.practiceproblems.ui.htree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import kotlinx.android.synthetic.main.fragment_htree.*

class HTreeFragment : BaseFragment() {

    companion object {
        val TAG = HTreeFragment::class.java.simpleName
        private val MAX_DEPTH = 6
    }

    private var htreeLevel = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_htree, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        decreaseLevelButton.setOnClickListener { onDecreaseLevelClicked() }
        increaseLevelButton.setOnClickListener { onIncreaseLevelClicked() }
        htreeLevelTextView.text = htreeLevel.toString()
    }

    private fun onIncreaseLevelClicked() {
        if (htreeLevel >= MAX_DEPTH) {
            Toast.makeText(context, R.string.depth_too_great, Toast.LENGTH_SHORT).show()
            return
        }
        htreeLevel++
        drawHtree()
    }

    private fun onDecreaseLevelClicked() {
        if (htreeLevel == 1) return
        htreeLevel--
        drawHtree()
    }

    private fun drawHtree() {
        htreeLevelTextView.text = htreeLevel.toString()
        htreeView.setDepth(htreeLevel)
    }

}
