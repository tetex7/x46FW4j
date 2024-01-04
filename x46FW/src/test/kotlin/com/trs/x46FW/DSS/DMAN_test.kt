package com.trs.x46FW.DSS

import com.trs.x46FW.internal.XLOG
//import com.trs.x46FW.internal.XDMAN
import org.junit.jupiter.api.Test

class DMAN_test
{
    /*class Dt1 : IDemon
    {
        override val uuid: UUID = UUID.randomUUID()
        override var R: FLAG = false
        override val name: String = "Dt1"
        override var E: FLAG = false
        override val D: FLAG = false
        override val DID:Short = 586
        override var PRI:Short = 1
        override val run: () -> Unit = {
            this.R = true
            for (i in 0..200)
            {
                println("d1 $i")
            }
            this.R = false
        }
    }

    class Dt2 : IDemon
    {
        override val uuid: UUID = UUID.randomUUID()
        override var R: FLAG = false
        override val name: String = "Dt2"
        override var E: FLAG = false
        override val D: FLAG = false
        override val DID:Short = 586
        override var PRI:Short = 1
        override val run: () -> Unit = {
            this.R = true
            for (i in 0..200)
            {
                println("d2 $i")
            }
            this.R = false
        }
    }*/



    @Test
    fun dman_test()
    {
        //var ft = Dt1()
        //var ft2 = Dt2()
        val d:DMAN = DMAN()

        val Dt1 = DEM_MK(name = "dt2") {
            for (i in 0..200)
            {
                println("d1 $i")
            }
        }

        val Dt2 = DEM_MK(name = "dt2") {
            println(this.THR.name)//Thread.currentThread().name)
            while (R)
            {
                println(this.kids_stack.pop())
            }
        }
        println(Dt2)
        d += Dt1
        d += Dt2

        //Thread.sleep(800)
        val id = DEM_MK(name = "frun_test", PRI = RESV) RET@{
            this.E = true

            println(Thread.currentThread().name)
            println(this)
            //this.addKids(Dt2)
            return@RET
        }

        for (v in d)
        {
            XLOG.DEBUG(v.toString())
        }


        id(d)
        d.PAS = true
        d.bluk_add(Dt2, Dt1, Dt1, Dt2)
        d.map_srink()
        //d.PAS = false


        /*XDMAN["FUCL"] = DEM_MK(PRI = 16) {
            println(this.THR.name)
        }*/
    }
}
