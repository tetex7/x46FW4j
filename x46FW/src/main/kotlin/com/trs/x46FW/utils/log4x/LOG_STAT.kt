package com.trs.x46FW.utils.log4x

enum class LOG_STAT(val v:Int)
{
    L_NO(0),
    L_YES(1),
    L_LB(3);

    fun toBool():Boolean = this == L_YES
}

fun Boolean.toLOGS(): LOG_STAT
{
    return if (this) LOG_STAT.L_YES else LOG_STAT.L_NO
}