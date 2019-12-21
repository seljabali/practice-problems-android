package com.seljabali.practiceproblems.ui.binarytree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R

class BinaryTreeFragment : BaseFragment() {

    companion object {
        val TAG = BinaryTreeFragment ::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_binary_tree, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tree = BinaryTree(5)
        tree.addNode(7)
        tree.addNode(1)
        print(tree.toString())
    }
}