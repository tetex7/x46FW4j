package com.trs.x46FW.utils.qlang

data class D_inst(var tag:String, var value:String)
{
    fun toPair():Pair<String, String> = Pair(tag, value)
}
