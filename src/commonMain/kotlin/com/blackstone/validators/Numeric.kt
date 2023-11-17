package com.blackstone.validators

import com.blackstone.Rule

class Numeric(private val minNumericChars: Int, override var message: String? = null) : Rule {
    override fun validate(input: String, fieldName: String): Boolean {
        // Count the number of numeric characters
        val numericCount = input.count { it.isDigit() }

        // Update the error message with the actual count of numeric characters
        message = message ?: "$fieldName must contain at least $minNumericChars numeric characters, but only has $numericCount"

        // Check if the count of numeric characters meets the minimum requirement
        return numericCount >= minNumericChars
    }
}