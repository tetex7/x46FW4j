package com.trs.x46FW.utils

import kotlin.concurrent.thread

class Event(tr:FLAG = false, _block: () -> Unit)
{

    val `tr?` = tr
    val b = _block
    val thr = thread(start = false, block = _block)

    operator fun invoke()
    {
        call()
    }

    fun call()
    {
        if (`tr?`) {
            thr.start()
        }
        else
        {
            b()
        }

    }
}