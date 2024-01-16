package com.trs.x46FW.utils

abstract class Ithreaded
{
    val in_lock: Lock = Lock()

    inline fun <bi> t_run(bl:() -> bi):bi = synchronized(in_lock, bl)
}