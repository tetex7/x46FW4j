package com.trs.x46FW.utils

interface Ithreaded
{
    val in_lock: Lock

    fun <bi> t_run(bl:() -> bi):bi
    {
        return synchronized(in_lock) RET@{
            return@RET bl()
        }

    }
}