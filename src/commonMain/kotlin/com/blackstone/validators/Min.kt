package com.blackstone.validators

import com.blackstone.Rule


class Min(val min: Int, override var message: String? = null) : Rule {

    override fun validate(input: String, fieldName: String): Boolean {
        message = message ?: "The $fieldName input was not long enough. Expected $min characters but got ${input.length} characters"

        return input.length >= min
    }
}

