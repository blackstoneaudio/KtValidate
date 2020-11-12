package com.blackstone.domain.util.validators

import com.blackstone.domain.util.Rule


class Max(val max: Int, override var message: String? = null) : Rule {

    override fun validate(input: String, fieldName: String): Boolean {
        message = message ?: "The $fieldName input was too long. Expected less than ${max + 1} but got ${input.length}"

        return input.length <= max
    }

}
