package com.blackstone.domain.util.validators

import com.blackstone.domain.util.Rule

class Min(val min: Int, override var message: String? = null) : Rule {

    override fun validate(input: String, fieldName: String): Boolean {
        message = message ?: "The $fieldName input was not long enough. Expected $min but got ${input.length}"

        return input.length > min
    }
}

