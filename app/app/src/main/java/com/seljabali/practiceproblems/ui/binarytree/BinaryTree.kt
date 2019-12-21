package com.seljabali.practiceproblems.ui.binarytree

class BinaryTree<V: Comparable<V>> {
    var root: TreeNode<V>? = null

    constructor(rootNode: TreeNode<V>) {
        this.root = rootNode
    }

    constructor(value: V) {
        this.root = TreeNode(value)
    }

    public fun addNode(value: V) {
        val nodeToAdd = TreeNode(value)
        if (root == null) {
            this.root = nodeToAdd
        }
        addNodeHelper(root!!, nodeToAdd)
    }

    private fun addNodeHelper(parentNode: TreeNode<V>, nodeToAdd: TreeNode<V>) {
        val parentCompare = parentNode.compareTo(nodeToAdd)
        when {
            parentCompare == 0 -> return
            parentCompare < 0 ->  {
                if (parentNode.leftNode == null) {
                    parentNode.leftNode = nodeToAdd
                    return
                }
                addNodeHelper(parentNode.leftNode!!, nodeToAdd)
            }
            parentCompare > 0 -> {
                if (parentNode.rightNode == null) {
                    parentNode.rightNode = nodeToAdd
                    return
                }
                addNodeHelper(parentNode.rightNode!!, nodeToAdd)
            }
        }
    }

    public fun removeValue(value: V) {
        val thisRoot = root ?: return
        val rootValueCompare = thisRoot.value.compareTo(value)
        if (rootValueCompare == 0) {
            if (thisRoot.leftNode == null && thisRoot.rightNode == null) {
                root = null
                return
            }
            if (thisRoot.leftNode == null && thisRoot.rightNode != null) {
                root = thisRoot.rightNode
                return
            }
            if (thisRoot.leftNode != null && thisRoot.rightNode == null) {
                root = thisRoot.leftNode
                return
            }
            return
        }
    }

    public fun findValue(value: V): TreeNode<V>? {
        val thisRoot = root ?: return null
        val compareValue = thisRoot.value.compareTo(value)
        if (compareValue == 0) return root
        return findValueHelper(thisRoot, value)
    }

    private fun findValueHelper(parentNode: TreeNode<V>, value: V): TreeNode<V>? {
        val compareValue = parentNode.value.compareTo(value)
        if (compareValue == 0) return parentNode
        if (compareValue < 0) {
            if (parentNode.leftNode == null) return null
            return findValueHelper(parentNode.leftNode!!, value)
        }
        if (parentNode.rightNode == null) return null
        return findValueHelper(parentNode.rightNode!!, value)
    }

    override fun toString(): String {
        val thisRoot = root ?: return ""
        return "$thisRoot \n \n ${thisRoot.leftNode?.toString() ?: ""} \t \t ${thisRoot.rightNode?.toString() ?: ""}"
    }
}

class TreeNode<V: Comparable<V>>(nodeValue: V) : Comparable<TreeNode<V>>{
    var value: V = nodeValue
    var leftNode: TreeNode<V>? = null
    var rightNode: TreeNode<V>? = null

    override fun compareTo(other: TreeNode<V>): Int {
        val otherValue = other.value
        return value.compareTo(otherValue)
    }

    override fun toString(): String {
        return "$value"
    }
}