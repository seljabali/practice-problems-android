package com.seljabali.practiceproblems.ui.linkedlist

class LinkedList<V : Comparable<V>> {
    private var headNode: LinkedNode<V>? = null
    private var tailNode: LinkedNode<V>? = null

    fun add(value: V) {
        val nodeToAdd = LinkedNode(value)
        if (headNode == null) {
            this.headNode = nodeToAdd
            this.tailNode = headNode
            return
        }
        tailNode?.setNextNode(nodeToAdd)
        tailNode = nodeToAdd
    }

    override fun toString(): String {
        if (headNode == null) return ""
        var currentNode = headNode
        var resultString = "${currentNode?.getValue() ?: ""}, "
        currentNode = currentNode?.getNextNode()
        while (currentNode?.getNextNode() != null) {
            resultString += "${currentNode.getValue() ?: ""}, "
        }
        return resultString
    }
}