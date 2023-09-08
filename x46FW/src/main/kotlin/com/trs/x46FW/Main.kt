package com.trs.x46FW

import com.trs.x46FW.DSS.DEM_MK
import com.trs.x46FW.DSS.DMAN
//import com.trs.x46FW.internal.`?`

private fun test_all()
{
    val dMAN = DMAN()
    val ff = DEM_MK(name = "teat") {
        println(this.DMA?.TC!!)
        println("${this.name} IS\n${this.THR.name}")
    }
    val t = DEM_MK(name = "fd") {
        //println("YOUR FUCKED")
        this.DMA?.killAll_S()
        println("SHOW IS ${dMAN.show_sat_rq()}")
    }
    dMAN add ff
    println("SHOW IS ${dMAN.show_sat_rq()}")
}

internal fun main()
{
    test_all()
}
