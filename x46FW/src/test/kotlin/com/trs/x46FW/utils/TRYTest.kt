/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.trs.x46FW.utils

import kotlin.test.Test

class TRYTest
{
    @Test
    fun bask_test()
    {
        /*val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")*/
        val o = TRY(NO_EX = true)
        {
            throw RuntimeException()
        }
    }

    @Test
    fun bask_ret_test()
    {
        /*val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")*/
        val o = TRY(NO_EX = true)
        {
            return@TRY "TEST"
        }
        println(o.rd)
    }

}
