package com.seljabali.practiceproblems.ui.parans

import java.util.*


/*

Assume you have an android app which takes in the user input in the form of letters, numbers and parenthesis like "(" or ")".

You need to write a function which would remove invalid/unmatched parenthesis from the input.

Eg. ab(c)e)c123( --> ab(c)ec123
Eg. () --> ()
Eg. )())))))))( --> ()

Hints: You can take your own strategy whether to remove the closest invalid parenthesis or furthest. Eg. (a)b)c --> (ab)c or (a)bc
 */

/*

Assume you have an android app which takes in the user input in the form of letters, numbers and parenthesis like "(" or ")".

You need to write a function which would remove invalid/unmatched parenthesis from the input.

Eg. ab(c)e)c123( --> ab(c)ec123
Eg. () --> ()
Eg. )())))))))( --> ()

Hints: You can take your own strategy whether to remove the closest invalid parenthesis or furthest. Eg. (a)b)c --> (ab)c or (a)bc
 */
internal object Solution {
    fun validateInputString(input: String?): String {
        if (input == null || input.length == 0) return ""
        if (input.length == 1 && !isBrace(input[0])) return input
        val goodBraces = getGoodBraces(input)
        var result = ""
        for (i in 0 until input.length) {
            val currentChar = input[i]
            if (!isBrace(currentChar)) {
                result += currentChar
                continue
            }
            if (goodBraces.containsKey(i)) {
                result += currentChar
            }
        }
        return result
    }

    fun isBrace(character: Char): Boolean {
        return character == '(' || character == ')'
    }

    fun getGoodBraces(input: String): HashMap<Int, Char?> {
        val goodBraces =
            HashMap<Int, Char?>()
        val leftBraces = ArrayList<Int>()
        for (i in 0 until input.length) {
            val currentChar = input[i]
            if (!isBrace(currentChar)) continue
            if (currentChar == '(') {
                leftBraces.add(i)
                continue
            }
            if (leftBraces.isEmpty()) continue
            val outerMostLeftBrace = leftBraces[0]
            leftBraces.removeAt(0)
            goodBraces[outerMostLeftBrace] = '('
            goodBraces[i] = ')'
        }
        return goodBraces
    }

    // a(((b)c)d)
// #1 pass, hash of good braces
//  hash of left braces, hash of right braces
//  as soon as you see a left, add it
//  as soon as you see a right, add to good braces hash, and remove first one from left braces
// #2 pass, remove braces that are not in the hash of good braces
//  create a result string
//  for every character pass to result, if brace ensure is in good brace hash, else ommit
    @JvmStatic
    fun main(args: Array<String>) { // valid strings
        org.junit.Assert.assertEquals("", validateInputString(""))
        org.junit.Assert.assertEquals("nobrackets", validateInputString("nobrackets"))
        org.junit.Assert.assertEquals("()", validateInputString("()"))
        org.junit.Assert.assertEquals("a()b()c()", validateInputString("a()b()c()"))
        org.junit.Assert.assertEquals("a(((b)c)d)", validateInputString("a(((b)c)d)"))
        org.junit.Assert.assertEquals("a(b(c(d)))", validateInputString("a(b(c(d)))"))
        // invalid strings
        org.junit.Assert.assertEquals("", validateInputString("("))
        org.junit.Assert.assertEquals("abc", validateInputString(")abc("))
        org.junit.Assert.assertEquals("a(bc)defgh", validateInputString("a(bc)d((efgh"))
        org.junit.Assert.assertEquals("()", validateInputString(")())))))))("))
        org.junit.Assert.assertEquals("a(b)cde", validateInputString("a(((b)cde"))
        org.junit.Assert.assertEquals("abcd", validateInputString("(a(b(c(d("))
        // TODO - update these based on implementation
        org.junit.Assert.assertEquals("(a)bc", validateInputString("(a)b)c"))
        org.junit.Assert.assertEquals("ab(c)ec123", validateInputString("ab(c)e)c123("))
        println("Success!")
    }
}

