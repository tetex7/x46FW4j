package com.trs.x46FW.internal

import com.trs.x46FW.DSS.DMAN
import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.log4x.Logger


internal var XBOOT: FLAG = false
//internal val XDMAN:DMAN = DMAN()
internal val XLOG:Logger = run RET@{
    val f = Logger()
    val g = "[x46FW]-${f.LOG_HEAD}"
    val path = "${f.log_path.replace("log4x", "[x46FW]")}"
    f.LOG_HEAD = g
    f.log_path = path
    f.toFile = true
    return@RET f
}