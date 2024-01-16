package com.trs.x46FW.DSS

import com.trs.x46FW.utils.exception.Ix46FWException
import com.trs.x46FW.utils.arsize
import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.exception.Ix46FWError

@x46FW_API
class DMAN_Error(msg:String, D:DMAN, CA:Throwable? = null) : Ix46FWError(msg, CA)
{

    val SD = D
    val TC = D.TC
    val frTC = D.TC.arsize - 1
    val rRC = D.TC.arsize
    constructor(Df:DMAN) : this(msg = "NO_MSG", D = Df)

}