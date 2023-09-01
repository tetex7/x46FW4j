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
        val v = Logger()
        v.toFile = true
        v.INFO(v.path_prs())

        XLOG.INFO("TEST")
        v(XLOG.path_prs())

        v("TEST1")

        v.INFO("HI")
        v.DEBUG("dfg")
    }
}