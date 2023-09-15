package com.trs.x46FW.DSS

import com.trs.x46FW.utils.Ix46FW_error
import com.trs.x46FW.utils.arsize
import com.trs.x46FW.internal.x46FW_API

@x46FW_API
class DMAN_err(msg:String, D:DMAN, CA:Throwable? = null) : Ix46FW_error(msg, CA)
{

    val SD = D
    val TC = D.TC
    val frTC = D.TC.arsize - 1
    val rRC = D.TC.arsize
    constructor(Df:DMAN) : this(msg = "NO_MSG", D = Df)

}