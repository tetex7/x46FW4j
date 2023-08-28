package com.trs.x46FW.DSS

class OutThreaded_err(msg:String, D:DMAN, CA:Throwable? = null) : DMAN_err(msg, D, CA)
{
    constructor(s:String, DS:DMAN) : this(s, DS, null)
    constructor(DS:DMAN) : this("NO_MSG", DS, null)
}