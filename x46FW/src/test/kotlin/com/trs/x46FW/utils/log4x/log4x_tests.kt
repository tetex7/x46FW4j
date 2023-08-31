package com.trs.x46FW.utils.log4x

//import com.trs.x46FW.utils.log4x.Level
import org.junit.jupiter.api.Test
import com.trs.x46FW.utils.log4x.Logger

class log4x_tests
{
    @Test
    fun log_test()
    {
        val v = Logger()
        v.level = Level.ALL
        v.INFO("HI")
        v.DEBUG("dfg")
    }
}