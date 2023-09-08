package com.trs.x46FW.utils.qlang

import java.io.File
import java.util.*
import kotlin.collections.HashMap

import java.util.Scanner


/**
 * DO NOT USE
 */
class Qlang_file
{
    private val p:Scanner
    companion object
    {
        val t_qlag = Regex("<qlang.*>\n")
        val te_qlag = Regex("\n</qlang.*>")
    }

    val tag_buff:Vector<Pair<String, *>> = Vector()

    val tag_st = Stack<String>()

    val vals:Vector<D_inst> = Vector()
    //val vals:HashMap<String, D_inst> = Vector()

    var main_ctx:D_inst = D_inst("", "")

    var RE:Stack<Regex> = Stack()

    fun stdinit()
    {
        tag_buff.add(Pair<String, (String)->String>("find") RET1@{
            return@RET1 Regex(it).find(main_ctx.value)?.value!!
        })
        tag_buff.add(Pair<String, (String, String)->Unit>("as") { name:String, v:String ->
            vals.add(D_inst(name, v))
        })
        tag_buff.add(Pair<String, (String, String)->Unit>("in") re@{ name:String, v:String ->
            main_ctx.tag = name
            main_ctx.value = v
        })
        tag_buff.add(Pair<String, (String)->Unit>("regx_add") { r:String ->
            RE.push(Regex(r))
        })
        tag_buff.add(Pair<String, ()->Unit>("rregx_run") {
            main_ctx.value = RE.pop()?.replace(main_ctx.value, "")!!
        })
        tag_buff.add(Pair<String, (String)->String>("out") RET@{
            var d = ""
            for (v in vals)
            {
               if (v.tag == it)
               {
                   d = v.value
               }
            }
            return@RET d
        })
    }

    fun addVal(string: String)
    {

    }

    //private val file = __file

    val raw_text:String //= file.readText()

    constructor(__file: File)
    {
        raw_text = __file.readText()
        p = Scanner(raw_text)
    }

    constructor(txt: String)
    {
        raw_text = txt
        p = Scanner(raw_text)
    }

    //fun _pah()

    fun pah(txt:String): Stack<String>
    {

        var to_p = t_qlag.replace(te_qlag.replace(raw_text,""), "")
        val f = Regex("_*[a-z][A-Za-z0-9_]*")

        /*while (to_p.contains("\n") || to_p.contains("\t") || to_p.contains(" "))
        {
            to_p = to_p.replace("\n", "").replace("\t", "").replace(" ", "")
        }
        while (to_p.contains(Regex("<*>")))
        {
            tag_st.push(Regex("<*>").find(to_p)?.value!!)
            to_p.replace(Regex("<*>"), "")
        }*/


        return tag_st
    }

}


//private typealias tTAG_DEF =
