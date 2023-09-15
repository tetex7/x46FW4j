package com.trs.x46FW.utils.qlang

import java.util.regex.Pattern

class qlangBuilder
{
    val ql = Qlang()
    fun add(tag:String, ini:Qlang_inst.(tag:String, p: Pattern, ctxt:String)->String): qlangBuilder
    {
        ql[Regex(tag)] = Qlang_inst(ini, Regex(tag))
        return this
    }

    fun add(tag:String, ini:Qlang_inst): qlangBuilder
    {
        ql[Regex(tag)] = ini
        return this
    }

    fun initLibSTD():qlangBuilder
    {
        ql.initLibSTD()
        return this
    }

    fun bulid():Qlang = ql

}