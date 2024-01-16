package com.trs.x46FW.utils

import com.trs.x46FW.internal.XLOG
import com.trs.x46FW.utils.Event
import org.junit.jupiter.api.Test
import java.util.Stack
import kotlin.random.Random

class Event_test
{
    @Test
    fun ev_test()
    {
        val ev:Event = Event(tr = true) {
            val stk = Stack<Pair<String, Int>>()

            for (i in 0 .. 55)
            {
                stk.push(Pair("INDEX ${i}", Random.nextInt()))
            }

            println(stk.seek("INDEX 2"))
        }

        ev()
    }
}