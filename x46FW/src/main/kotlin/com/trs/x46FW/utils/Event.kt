package com.trs.x46FW.utils

import kotlin.concurrent.thread


/**
 * @since 1.0
 */
class Event(tr:FLAG = false, _block: () -> Unit)
{

    private val `tr?` = tr
    private val b = _block
    private val thr = thread(start = false, block = _block)

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