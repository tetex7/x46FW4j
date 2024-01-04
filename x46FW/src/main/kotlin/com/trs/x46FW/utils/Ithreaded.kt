package com.trs.x46FW.utils

abstract class Ithreaded
{
    val in_lock: Lock = Lock()

    fun <bi : Any> t_run(bl:() -> bi):bi
    {
        return synchronized(in_lock) RET@{
            return@RET bl()
        }

    }
}