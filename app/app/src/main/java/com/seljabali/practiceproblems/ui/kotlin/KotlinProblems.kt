package com.seljabali.practiceproblems.ui.kotlin

// Breadth First Search

public boolean findNode(Node node, String name) {
    if (isStrEq(node.name, name)) {
        return true;
    }
    return findNode(node.left, name) || findNode(node.right, name))
}

public boolean isStrEq(String a, String b) {
    return a.compare(b) == 0
}

// Depth First
public boolean findNode(Node node, String name) {
    if (node.left == null) {
        return isStrEq(node.name, name)
    }
    return findNode(node.left, name) || findNode(node.right, name))
}


// Does string have all unique characters?

// Time N
// Space N + N*Int
public boolean isAllCharUnique(String text) {
    HashMap<Character, Int> charLookup = HashMap<>();
    for (int i = 0; i < text.length; i++) {
        Character currentChar = text.charAt(i).toLowerCase();
        int currentCharCount = charLookup.has(currentChar) ?
        charLookup.get(currentChar) : 0
        if (currentCharCount == 1) return false
        charLookup.put(currentChar, ++currentCharCount)
    }
    return true;
}

// Time N^2
// Space N
public boolean isAllCharUnique(String text) {
    if (text == null) return true;
    if (text.size <= 1) return true;
    for (int i = 0; i < text.length - 1; i++) {
        Character currentChar = text.charAt(i).toLowerCase();
        for (int j = i + 1; j < text.length; j++) {
        if (currentChar == text.charAt(j).toLowerCase())
            return false;
    }
    }
    return true;
}


// To execute Kotlin code, please define a top level function named main
/*1. Write a loop that outputs the numbers from 1 to 100 inclusive to console


2. Modify the loop so that it outputs only the odd numbers between 1-100 to console


3. Create a function that converts a collection into a string with spaces between each item
   input: ["John", "Jacob", "Jingleheimer", "Schmidt"]
   output: "John Jacob Jingleheimer Schmidt"


4. Create a function that converts a string into a collection, using commas as a separator
   and cleaning out extra spaces
   input: "please, remove  , extra,   white, space, except here"
   output: ["please", "remove", "extra", "white", "space", "except here"]


5. Create a function that takes a collection with random integers (negative & positive),
   and returns both the number that ocurred the most times and the number of times it occurred.
   In case of multiple correct answers, return any of them. Return null in case of an error
   For example, in this array [-1, 33, 9, 48, 34343, 5, -2, 33, -1, 48, 33] number 33 occurs 3 times
   */

fun main(args: Array<String>) {
    val input = arrayListOf(-1, 33, 9, 48, 34343, 5, -2, 33, -1, 48, 33, 44, 44, 44, 44)
    System.out.println(getMostOccurringNumber(input))
}

fun getNumbersOneToOneHundred() {
    for (i in 1..100) {
        System.out.println(i.toString())
    }

}

fun getNumbersOneToOneHundredOdd() {
    for (i in 1..100) {
        if (i % 2 != 0) {
            System.out.println(i.toString())
        }
    }
}

// 3
// input: ["John", "Jacob", "Jingleheimer", "Schmidt"]
// output: "John Jacob Jingleheimer Schmidt"
fun arrayToString(array: ArrayList<String>, delimeter: String): String {
    if (array.isEmpty()) return ""
    var result = ""
    for (i in 0 until array.size) {
        if (i != array.size - 1) {
            result += array[i] + delimeter
            continue
        }
        result += array[i]
    }
    return result
}


// 4
fun getStringSplitBy(text: String, delimeter: String): List<String> {
    if (text.isEmpty()) return emptyList()
    // if (delimete.isEmpty()) return Array<>(text)
    return text.split(delimeter, ignoreCase = true, limit = 0).map { it.trim() }
}

// 5
fun getMostOccurringNumber(array: ArrayList<Int>): String {
    if (array.isEmpty()) return ""
    val numCount = HashMap<Int, Int>()
    for (i in 0 until array.size) {
        val currentNum = array[i]
        if (numCount.containsKey(currentNum)) {
            numCount[currentNum] = numCount[currentNum]!! + 1
            continue;
        }
        numCount[currentNum] = 1
    }

    var maxNum = 0
    var maxNumCount = 0
    for (key in numCount.keys) {  // not sure if keys
        numCount.get(key)?.let {
            if (it > maxNumCount) {
                maxNum = key
                maxNumCount = it
            }
        }
    }
    return "Number ${maxNum} occurs ${maxNumCount} times"
}

//aabcccccaaa
//a2b1c5a3

// Time N
// Space 2N + C
public String getCompressedString(String text) {
    if (text == null) return null;
    if (text.size <= 1) return text;
    String compressed = ""
    int mainIndex;
    Character currentChar;
    int dupCount;
    while (mainIndex < text.length) {
        currentChar = text.charAt(mainIndex);
        dupCount = 1;
        for (int i = mainIndex; i < text.length; i++) {
            if (currentChar == text.charAt(i)) {
                dupCount++;
                continue;
            }
            break;
        }
        compressed += currentChar.toString() + dupCount;
        mainIndex += dupCount;
    }
    if (compressed.size() < text.size()) {
        return compressed;
    }
    return text;
}