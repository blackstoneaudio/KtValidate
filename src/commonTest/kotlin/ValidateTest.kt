package com.blackstone

import com.blackstone.validators.Email
import com.blackstone.validators.Max
import com.blackstone.validators.Min
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ValidateTest {

    @Test
    fun `it should validate minimum length`() {
        val validate = Validate("te") {
            fieldName = "user name"
            +Min(3)
        }
        assertFalse(validate.isValid)
        assertEquals("The user name input was not long enough. Expected 3 characters but got 2", validate.messages.first())
        assertEquals(1, validate.messages.size)
    }

    @Test
    fun `it should validate max value`() {
        val validate = Validate("aaaa") {
            fieldName = "Password"
            +Max(3)
        }
        assertFalse(validate.isValid)
        assertEquals("The Password input was too long. Expected less than 4 characters but got 4", validate.messages.first())
        assertEquals(1, validate.messages.size)
    }

    @Test
    fun `it should get a valid result`() {
        val validate = Validate("aaaaaaaa") {
            +Max(30)
            +Min(3)
        }
        assertTrue(validate.isValid)
    }

    @Test
    fun `it should be an invalid email address`() {
        val validate = Validate("alex.black!lsdjf.com") {
            +Email()
        }
        assertFalse(validate.isValid)
    }

    @Test
    fun `it should be a valid email address`() {
        val validate = Validate("test@test.com") {
            +Email()
        }
        assertTrue(validate.isValid)
    }
}
