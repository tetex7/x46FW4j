package com.trs.x46FW.utils.log4x

import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.Ithreaded
import com.trs.x46FW.utils.Lock
import com.trs.x46FW.utils.qlang.Qlang
import com.trs.x46FW.utils.qlang.Qlang_inst
import java.io.PrintStream
import com.trs.x46FW.utils.qlang.libstd.*
import com.trs.x46FW.utils.qlang.Qlang.Companion.q_rand
import java.util.regex.Pattern
import com.trs.x46FW.internal.btc

@x46FW_API
class Logger: Ithreaded
{
    override val in_lock:Lock = Lock()


    companion object
    {
        fun Builder() = logBuilder()
        init
        {
            val df = btc.df()
        }
        /*const val h_hour = "%h%"
        const val h_minute = "%m%"
        const val h_second = "%s%"
        const val h_day = "%d%"
        const val h_month = "%mm%"
        const val h_year = "%y%"
        const val h_tname = "%tname%"
        const val p_temp = "%temp%"
        const val q_rand = "%rand%"
        private val h_class_path = "%cpath%"*/
        const val h_level = "%level%"
    }

    var log_path = "$p_temp/log4x-[$h_month:$h_day:$h_year]-[$q_rand].log"
    private val ql:Qlang = Qlang.Builder()
        .initLibSTD()
        .add(h_level) { tag: String, p: Pattern, ctxt: String -> level.toString() }
        .bulid()

    fun path_prs():String
    {
        return t_run RET@{
            return@RET ql(log_path).first
        }
    }

    fun isLoggable(_level: Level):FLAG = (level == Level.ALL) || (_level == level)

    fun isLoggable():FLAG = (!toCAN && !toFile)

    var toFile:FLAG = false
        get() = t_run RET@{
            return@RET field
        }
        set(value:FLAG) = t_run {
                field = value
                LOG_FILE = mk_file()
        }
    var toCAN:FLAG = true
    private fun mk_file(): PrintStream?
    {
        return t_run RET@{
            return@RET if (toFile) PrintStream(path_prs()) else null
        }

    }

    private var LOG_FILE: PrintStream? = mk_file()

    operator fun invoke(txt: String):Level
    {
        return t_run RET@{
            when (level)
            {
                Level.ALL -> INFO(txt)
                Level.WARNING -> WARNING(txt)
                Level.DEBUG -> DEBUG(txt)
                Level.TRACE -> TRACE(txt)
                Level.INFO -> INFO(txt)
                Level.ERROR -> ERROR(txt)
                Level.OFF -> return@RET level
            }
            return@RET level
        }
    }

    var PS:PrintStream = System.out!!
    var PS_ERR:PrintStream = System.err!!

    fun header_prs():String
    {
        return t_run RET@{
            return@RET ql(LOG_HEAD).first
        }
    }

    fun clog(v:Level):FLAG
    {
        return t_run RET@{
            return@RET ((level == Level.ALL) || (v == level)) && toCAN
        }
    }



    var LOG_HEAD = "[$h_hour:$h_minute:$h_second]:[$h_tname/$h_level]:"
        get() = t_run RET@{
            return@RET field
        }
        set(v) = t_run {
            field = v
        }
    var level:Level = Level.ALL
        get() = t_run RET@{
            return@RET field
        }
        set(value) = t_run RET@{
            field = value
        }

    fun INFO(txt:String)
    {
        t_run {
            if (clog(Level.INFO))
            {
                PS.println("${header_prs()} $txt")
            }
            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs()} $txt")
            }
        }
    }

    fun TRACE(txt:String)
    {
        t_run {
            PS_ERR.println("${header_prs()} $txt")
            if (LOG_FILE != null) {
                LOG_FILE?.println("${header_prs()} $txt")
            }
        }
    }

    fun DEBUG(txt:String)
    {
        t_run {
            if (clog(Level.DEBUG))
            {
                PS.println("${header_prs()} $txt")
            }

            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs()} $txt")
            }
        }
    }

    fun WARNING(txt:String)
    {
        t_run {
            if (clog(Level.WARNING))
            {
                PS.println("${header_prs()} $txt")
            }
            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs()} $txt")
            }
        }
    }

    fun ERROR(txt:String)
    {
        t_run {
            if (clog(Level.INFO))
            {
                PS_ERR.println("${header_prs()} $txt")
            }
            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs()} $txt")
            }
        }
    }

}