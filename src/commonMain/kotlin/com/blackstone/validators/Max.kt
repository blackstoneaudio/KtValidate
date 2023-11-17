package com.blackstone.validators

import com.blackstone.Rule


class Max(val max: Int, override var message: String? = null) : Rule {

    override fun validate(input: String, fieldName: String): Boolean {
        message = message ?: "The $fieldName input was too long. Expected less than ${max + 1} characters but has ${input.length} characters"

        return input.length <= max
    }

}
