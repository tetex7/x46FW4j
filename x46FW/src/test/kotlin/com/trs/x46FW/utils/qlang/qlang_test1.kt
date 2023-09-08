package com.trs.x46FW.utils.qlang

import com.trs.x46FW.utils.getfileStr
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class qlang_test1
{

    /*@Test
    fun stac()
    {
        val df =Qlang_file("<qlang>\n" +
                "    <in=%str%>\n" +
                "    <regx_pass>\"test.[1-9]*\"</regx_pass>\n" +
                "    <rregx_run>\n" +
                "    <find=\"test\">\n" +
                "        <as=%find%>\n" +
                "        <out=%find%>\n" +
                "    </find>\n" +
                "</qlag>").pah("rwaz")
        for(v in df)
        {
            println(v)
        }
    }*/

    @Test
    fun ran_test()
    {
        val qlang:Qlang = Qlang()
        println("qlang rtest: ${qlang("%rand+16%")}")
        println("qlang rtest1: ${qlang("%rand-16-20%")}")

    }


    @Test
    fun main_test()
    {
        val qlang:Qlang = Qlang()
        qlang[Regex("%test-[0-9]*%")] = RET@{ tag:String, p:Pattern, ctxt:String ->
            //assert(tag == Qlang.q_NAT)
            return@RET "tag:\"$tag\"\nNUM=\'${tag.replace(Qlang.qp_rtc.toRegex(), "")}\'"
        }
        println("qlang test: ${qlang("%test-1656%")}")

    }
}