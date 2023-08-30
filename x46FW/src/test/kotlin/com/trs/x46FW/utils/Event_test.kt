package com.trs.x46FW.utils

import com.trs.x46FW.utils.Event
import org.junit.jupiter.api.Test

class Event_test
{
    @Test
    fun ev_test()
    {
        var ev:Event = Event(tr = true) {
            println("test")
        }

        ev()
    }
}