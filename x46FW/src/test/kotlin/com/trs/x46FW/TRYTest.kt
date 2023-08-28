/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.trs.x46FW

import com.trs.x46FW.utils.STACK_TRACE_STR
import com.trs.x46FW.utils.TRY
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
        println(o.ex_data.cex?.STACK_TRACE_STR())
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

    @Test
    fun arsst_test()
    {
        /*val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")*/
        com.trs.x46FW.utils.assert(1 == 0, ex = false)
        //println(o.rd)
    }
}
