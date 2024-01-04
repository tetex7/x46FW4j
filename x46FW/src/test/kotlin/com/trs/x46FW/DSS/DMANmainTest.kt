package com.trs.x46FW.DSS

import com.trs.x46FW.utils.*
import org.junit.jupiter.api.Test

class DMANmainTest
{
    val dman:DMAN = DMAN(false)
    val work = DEM_MK("dt_work", 19, 4014)
    {
        println("dt_worker")

        snk_wait()
        this.kids_stack.pop()
        while (kids_stack.peek().data != "EOS")
        {
            delay(5)
        }
        for(v in kids_stack)
        {
            if (v.data is Int)
            {
                println(v.data)
            }

        }
    }

    val main = DEM_MK("dt_main", 10, 4054) RET@{
        //this.DMA?.add(work)
        /*try
        {*/
            mount(work)
            delay(44)
        /*}
        catch (exception:Exception)
        {
            this.E = true
            this.exps.push(exception)
            return@RET
        }*/
        callSNK("dt_work", ON)
        for (i in 0 .. 990)
        {

            send("dt_work", "inc", i)
        }
        return@RET

    }

    @Test
    fun test()
    {
        dman.bluk_add(main)
        dman.start()
        delay(590*2)
    }
}