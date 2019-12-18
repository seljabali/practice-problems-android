package com.seljabali.practiceproblems.ui.linkedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.logger.Logger
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R

class LinkedListsFragment : BaseFragment() {

    companion object {
        val TAG = LinkedListsFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_linked_lists, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linkedIntList = LinkedList<Int>()
        linkedIntList.add(4)
        Logger.v(linkedIntList.toString())
    }

}