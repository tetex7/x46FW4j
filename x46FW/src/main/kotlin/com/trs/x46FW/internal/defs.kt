package com.trs.x46FW.internal

import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.log4x.Logger
import kotlin.random.Random


internal var XBOOT: FLAG = false
//internal val XDMAN:DMAN = DMAN()


internal val jnlibs_dir = "com/trs/x46FW/nlibs"

val runID = Random.nextInt(2992, 992299)
private val lb = Logger.Builder()
internal val XLOG:Logger = lb.file(true)
    .path(lb.logFilePath.replace("log4x", "[x46FW]"))
    .head("[x46FW]-${lb.logHaed}")
    .CAN(true)
    .build()