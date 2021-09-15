package com.blackstone

interface Rule {
    var message: String?
    fun validate(input: String, fieldName: String): Boolean // if it was valid
}