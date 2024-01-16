package com.trs.x46FW.DSS

class OutThreadedException(msg:String, D:DMAN, CA:Throwable? = null) : DMAN_Exception(msg, D, CA)
{
    constructor(s:String, DS:DMAN) : this(s, DS, null)
    constructor(DS:DMAN) : this("NO_MSG", DS, null)
}