package com.trs.x46FW.internal

import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.qlang.Qlang
import com.google.gson.*
import com.trs.x46FW.utils.get_conf
import com.trs.x46FW.utils.qlang.libstd.p_user_conf
import java.io.File


/*object `?`
{
    /*fun `=`(): DMAN
    {
        return XDMAN
    }*/

    /*init
    {
        boot()
    }*/
}*/
internal var conf:x46FW_conf = x46FW_conf()

internal var WINT: FLAG = false
    get() = field
    private set(v: FLAG) {
        field = v
    }
internal var conf_file_q:Qlang = Qlang.Builder().bulid()
internal val conf_file = File(conf_file_q("${p_user_conf}/x46FW_conf.json").first)

fun boot(): Int
{

    if (!XBOOT)
    {
        WINT = true
        XBOOT = true


        val lman = Gson()

        if (conf_file.exists())
        {
            conf = lman.fromJson(conf_file.readText(Charsets.US_ASCII), x46FW_conf::class.java)
            conf_init = true
        }
        else
        {
            conf_file.writeText(lman.toJson(conf), Charsets.US_ASCII)
        }
        //XLOG.log_path = conf.xlog_path
        XLOG.DEBUG(conf_file_q("${p_user_conf}/x46FW_conf.json").first)
        XLOG.log_debug = conf.xlog_debug
        XLOG.DEBUG("\n${get_conf()}")
        XLOG.DEBUG("DMAN(MAX_THRD) = ${conf.thr_count()}")
        println(XLOG.log_path)

    }
    else
    {
        return 0
    }
    /*XDMAN[".Fboot"] = DEM_MK(PRI = 14, name = ".Fboot", _uuid = UUID.fromString("6607833d-9e8b-47f1-8e14-bfb5f2dc12ce")) {
        //println(this.name)
        //println(this.THR.name)
        //D.DMA?.map_srink()
        //println("d")
    }*/

    //System.setProperty("DSS_ARC", "FALLEN_ANGEL")
    //System.setProperty("DSS_VID", "v1.0b")
    //System.setProperty("xdev_name", "Tetex7")
    return 65
    //XDMAN.start()
}
