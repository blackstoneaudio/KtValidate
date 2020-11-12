package com.blackstone.domain.util

class Validate(private val input: String, block: Validate.() -> Unit) {
    val messages = mutableListOf<String>()
    var fieldName: String = "field"
    private var _isValid: Boolean = true // it's valid until toggled otherwise
    private val rules = mutableListOf<Rule>()

    val isValid get() = _isValid

    init {
        // this initializes our values
        block()
        validate()
    }

    private fun validate() {
        rules.forEach {
            if (!it.validate(input, fieldName)) {
                _isValid = false
                it.message?.also { message ->
                    messages.add(message)
                }
            }
        }
    }

    operator fun Rule.unaryPlus() {
        rules.add(this)
    }

}

