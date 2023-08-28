package com.trs.test_suit

import com.trs.x46FW.DSS.DEM_MK
import com.trs.x46FW.DSS.DMAN

fun main()
{
    val dMAN = DMAN()
    val ff = DEM_MK(name = "teat") { D, T ->
        println("${D.Name} IS\n${T.name}")
    }

    val t = DEM_MK(name = "fd") { D, T ->
        println("YOUR FUCKED")
        D.DMA?.killAllA()
        println("SHOW IS ${dMAN.show_sat_rq()}")
    }
    dMAN[ff.Name] = ff
    dMAN[t.Name] = t
    println("SHOW IS ${dMAN.show_sat_rq()}")
}