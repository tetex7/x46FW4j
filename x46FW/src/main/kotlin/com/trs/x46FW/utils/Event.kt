package com.trs.x46FW.utils

import kotlin.concurrent.thread

private typealias E_FUN = () -> Unit
/**
 * @since 1.0
 */
class Event(tr:FLAG = false, _block: E_FUN)
{

    private val `tr?`:FLAG = tr
    private var b:E_FUN = _block
    private val thr = thread(start = false, block = b)

    operator fun invoke(data_d:Any? = null)
    {
        call(data_d)
    }

    fun call(data_d:Any? = null)
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

/*class  EventC<bi>(tr:FLAG = false, t:bi, _block: bi.() -> Unit)
{

    private val `tr?` = tr
    private val b = _block
    private val tt = t
    private val thr = thread(start = false, block = {t._block()})

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
            tt.b()
        }

    }
}*/