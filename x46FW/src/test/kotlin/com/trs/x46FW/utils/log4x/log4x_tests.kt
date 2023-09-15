package com.trs.x46FW.utils.log4x

//import com.trs.x46FW.utils.log4x.Level
import org.junit.jupiter.api.Test
import com.trs.x46FW.utils.log4x.Logger
import com.trs.x46FW.internal.XLOG

class log4x_tests
{
    @Test
    fun log_test()
    {
        val v = Logger.Builder().file(true).CAN(true).build()
        //v.toFile = true
        v.INFO(v.path_prs())
        v.INFO("FF")
        v.INFO("FF")
        v.INFO("FF")
        v.INFO("FF")
        v.INFO("FF")
        v.INFO("FF")
        v.INFO("FF")
        v.INFO("FF")
        v.INFO("FF")


        XLOG.INFO("TEST")
        v(XLOG.path_prs())

        v("TEST1")

        v.INFO("HI")
        v.DEBUG("dfg")
        v.level = Level.ALL
    }
}