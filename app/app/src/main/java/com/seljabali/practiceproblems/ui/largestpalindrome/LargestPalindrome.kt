package com.seljabali.practiceproblems.ui.largestpalindrome

object LargestPalindrome {
    fun getLargestPalindrome(text: String?): String {
        if (text == null || "" == text) {
            return ""
        }
        for (windowSize in text.length downTo 2) {
            var index = 0
            while (index + windowSize <= text.length) {
                val candidate = text.substring(index, index + windowSize)
                if (isPalindrome(candidate)) {
                    return candidate
                }
                index++
            }
        }
        return text[0].toString() + ""
    }

    fun isPalindrome(text: String): Boolean {
        var i = 0
        while (i < Math.ceil(text.length / 2.toDouble())) {
            if (text[i] != text[text.length - i - 1]) {
                return false
            }
            i++
        }
        return true
    }
}