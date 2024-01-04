 package com.trs.x46FW.internal

import com.trs.x46FW.utils.*

internal var conf_init:FLAG = false

class x46FW_conf()
{

    var xlog_file:FLAG = true
        private set(value)
        {
            field = value
        }
        get() = field

    var xlog_can:FLAG = true
        private set(value)
        {
            field = value
        }
        get() = field

    var dss_can:FLAG = false
        private set(value)
        {
            field = value
        }
        get() = field

    var x46fw_args:Array<String> = arrayOf("-x,vr", "--TODO")
        private set(value)
        {
            field = value
        }
        get() = field

    var xlog_path = "%temp%/[x46FW]-[%mm%:%d%:%y%]-[%rand%].log"
        private set(value)
        {
            field = value
        }
        get() = field

    var xlog_debug:FLAG = true
        private set(value)
        {
            field = value
        }
        get() = field

    private var DMAN_THR_NUM:Int = 0
        private set(value)
        {
            field = value
        }
        get() = field

    private var DMAN_PRI:Int = 0
        private set(value)
        {
            field = value
        }
        get() = field

    fun thr_count():Int
    {
        return if (DMAN_THR_NUM <= 0) 5 else DMAN_THR_NUM
    }
    fun thr_PRI():Int
    {
        return if (DMAN_THR_NUM <= 0) 16 else DMAN_PRI
    }
}