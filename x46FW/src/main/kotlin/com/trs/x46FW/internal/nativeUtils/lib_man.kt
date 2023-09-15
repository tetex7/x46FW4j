package com.trs.x46FW.internal.nativeUtils

data class lib_metadata(val dav_name:String, val vid:String, val con_data:String)

data class lib_data(val lib_name:String, val meta:lib_metadata)


abstract class lib_man
{
    val data:Array<lib_data> = TODO()
}

