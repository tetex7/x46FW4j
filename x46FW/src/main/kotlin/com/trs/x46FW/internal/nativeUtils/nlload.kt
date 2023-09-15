package com.trs.x46FW.internal.nativeUtils

import com.trs.x46FW.internal.jnlibs_dir
import com.trs.x46FW.utils.FLAG
import java.util.*
import com.trs.x46FW.utils.*
import org.json.JSONObject
import java.io.File
import java.util.Stack
import com.trs.x46FW.internal.btc.df
import kotlin.random.Random

private val libnames = run RET@{
    val lman:JSONObject = JSONObject(getfileStr("JAR:/$jnlibs_dir/lib_man.json"))
    val temp = lman["libs"]
    val df = df()
    val d = TRY() __RET@{
        if (temp is Array<*> && temp.isArrayOf<String>())
        {
            @Suppress("UNCHECKED_CAST")
            return@__RET (lman["libs"] as Array<String>).toList()
        }
        else
        {
            throw RuntimeException("bad lib_man.json")
        }
    }
    return@RET d.rd!!

}


internal var LLIBS:FLAG = false
    get() {
        return field
    }
    private set(value) {
        field = value
    }

internal fun loadjarlib():Stack<String>?
{
    if (!LLIBS)
    {
        var ou:Stack<String> = Stack()
        for (v in libnames)
        {
            val so_lib:String = "${System.getProperty("java.io.tmpdir")}/$v"
            val f: File = File(so_lib)
            f.writeBytes(getfile("JAR:/$jnlibs_dir/$v").readAllBytes()!!)
            ou.push(so_lib)
            f.deleteOnExit()
        }
        LLIBS = true
    }
    return null
}

internal val libs:List<String> = loadjarlib()?.toList()!!
    get()
    {
        return field
    }