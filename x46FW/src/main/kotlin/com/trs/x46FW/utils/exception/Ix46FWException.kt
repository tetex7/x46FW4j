package com.trs.x46FW.utils.exception

//import com.trs.x46FW.DSS.wintest
import com.trs.x46FW.internal.wintest
import com.trs.x46FW.internal.x46FW_API

//import com.trs.disasm.ui.exp
//typealias FLAG = Boolean
//import java.lang.Th
@x46FW_API
class Ix46FWException(msg: String, CA:Throwable? = null, sup:Boolean = false) : Exception(msg, CA, sup, true), IException //Throwable(msg, CA)
{
    val CAS = super.cause
    val MSG:String = msg
    constructor() : this("NO_MSG")
    init
    {
        wintest()
        //this.MSG = msg
    }
}
