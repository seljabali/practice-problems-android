package com.seljabali.practiceproblems.ui.linkedlist

class LinkedNode<V : Comparable<V>>(nodeValue: V?) {
    private var value: V? = nodeValue
    private var next: LinkedNode<V>? = null

    fun setNextNode(next: LinkedNode<V>?) {
        this.next = next
    }

    fun getNextNode():LinkedNode<V>? = next

    fun setValue(value: V?) {
        this.value = value
    }

    fun getValue(): V? = value
}