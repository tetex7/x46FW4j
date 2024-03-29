package com.trs.x46FW.internal

import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.internal.cli.cli_pars
import com.trs.x46FW.utils.log4x.Logger
import kotlin.random.Random


internal var XBOOT: FLAG = false
//internal val XDMAN:DMAN = DMAN()


internal val jnlibs_dir = "com/trs/x46FW/nlibs"

val runID = Random.nextInt(2992, 992299)

internal var xargs = cli_pars(conf.x46fw_args)
internal val lb = Logger.Builder()
internal val XLOG:Logger = lb.file(true)
    .path(conf.xlog_path)//lb.logFilePath.replace("log4x", "[x46FW]"))
    .head("[x46FW]-${lb.logHaed}")
    .CAN(conf.xlog_can)
    .file(conf.xlog_file)
    .build().run {
        if (!WINT)
        {
            this.WARNING("\nX46FW WARNING: WINT IS OFF\nthis is not recommended\nunintended side effects are likely")
        }
        return@run this
    }