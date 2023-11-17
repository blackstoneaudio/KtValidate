package com.blackstone.validators

import com.blackstone.Rule

class Alpha(private val minAlphaChars: Int, override var message: String? = null) : Rule {
    override fun validate(input: String, fieldName: String): Boolean {
        // Count the number of alphabetic characters (case insensitive)
        val alphaCount = input.count { it.isLetter() }

        // Update the error message with the actual count of alpha characters
        message = message ?: "$fieldName must contain at least $minAlphaChars alphabetic characters, but has $alphaCount"

        // Check if the count of alphabetic characters meets the minimum requirement
        return alphaCount >= minAlphaChars
    }
}