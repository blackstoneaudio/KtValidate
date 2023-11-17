package com.blackstone.validators

import com.blackstone.Rule

class SpecialCharacter(private val minSpecialCharacter: Int, override var message: String? = null) :
    Rule {
    override fun validate(input: String, fieldName: String): Boolean {
        // Count the number of non-alphanumeric characters
        val specialCharacterCount = input.count { !it.isLetterOrDigit() }

        // Update the error message with the actual count of non-alphanumeric characters
        message = message ?: "$fieldName must contain at least $minSpecialCharacter non-alphanumeric characters, but has $specialCharacterCount. example:!@#$%^&*()"

        // Check if the count of non-alphanumeric characters meets the minimum requirement
        return specialCharacterCount >= minSpecialCharacter
    }
}