package com.trs.x46FW.utils.qlang

class Qlang_inst(block:()->String)
{
    private var __work:()->String
    fun work():String = __work()

    init
    {
        __work = block
    }
}