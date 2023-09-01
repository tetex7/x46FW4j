package com.trs.x46FW.utils.qlang

import com.trs.x46FW.utils.toSFstring
import org.junit.jupiter.api.Test
import java.time.LocalTime

class qlang_test1
{


    @Test
    fun main_test()
    {
        val qlang:Qlang = Qlang()
        qlang["%test%"] = Qlang_inst RET@{
            return@RET LocalTime.now().hour.toSFstring()
        }
        println("qlang test: ${qlang("%test%")}")

    }
}