package com.trs.x46FW.utils.log4x

import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.Ithreaded
import com.trs.x46FW.utils.Lock
import com.trs.x46FW.utils.toSFstring
import java.io.PrintStream
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

@x46FW_API
class Logger: Ithreaded
{
    override val in_lock:Lock = Lock()


    companion object
    {

        const val h_hour = "%h%"
        const val h_minute = "%m%"
        const val h_second = "%s%"
        const val h_day = "%d%"
        const val h_month = "%mm%"
        const val h_year = "%y%"
        const val h_tname = "%tname%"
        const val p_temp = "%temp%"
        const val q_rand = "%rand%"
        private val h_class_path = "%cpath%"
        const val h_level = "%level%"
    }

    var log_path = "$p_temp/log4x-[$h_month:$h_day:$h_year]-[$q_rand].log"

    fun path_prs():String
    {
        return t_run RET@{
            val io = log_path.replace(h_hour, LocalTime.now().hour.toSFstring())
                .replace(h_minute, LocalTime.now().minute.toSFstring())
                .replace(h_second, LocalTime.now().second.toSFstring())
                .replace(h_tname, Thread.currentThread().name)
                .replace(p_temp, System.getProperty("java.io.tmpdir"))
                .replace(q_rand, Random.nextInt(1221, 991199).toString())
                .replace(h_month, LocalDate.now().month.value.toSFstring())
                .replace(h_day, LocalDate.now().dayOfMonth.toSFstring())
                .replace(h_year, LocalDate.now().year.toSFstring())
                .replace(h_tname, Thread.currentThread().name)
            //.replace(h_level, ll.toString())
            return@RET io
        }
    }

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
                Level.OFF -> {
                }
            }
            return@RET level
        }
    }

    var PS:PrintStream = System.out!!
    var PS_ERR:PrintStream = System.err!!

    fun header_prs(ll:Level):String
    {
        return t_run RET@{
            val io = LOG_HEAD.replace(h_hour, LocalTime.now().hour.toSFstring())
                .replace(h_minute, LocalTime.now().minute.toSFstring())
                .replace(h_second, LocalTime.now().second.toSFstring())
                .replace(h_tname, Thread.currentThread().name)
                .replace(h_level, ll.toString())
                .replace(h_month, LocalDate.now().month.value.toSFstring())
                .replace(h_day, LocalDate.now().dayOfMonth.toSFstring())
                .replace(h_year, LocalDate.now().year.toSFstring())
                .replace(q_rand, Random.nextInt(212, 9119).toString())
            return@RET io
        }
    }

    private fun clog(v:Level):FLAG
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
                PS.println("${header_prs(Level.INFO)} $txt")
            }
            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs(Level.INFO)} $txt")
            }
        }
    }

    fun TRACE(txt:String)
    {
        t_run {
            PS_ERR.println("${header_prs(Level.TRACE)} $txt")
            if (LOG_FILE != null) {
                LOG_FILE?.println("${header_prs(Level.TRACE)} $txt")
            }
        }
    }

    fun DEBUG(txt:String)
    {
        t_run {
            if (clog(Level.DEBUG))
            {
                PS.println("${header_prs(Level.DEBUG)} $txt")
            }
            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs(Level.DEBUG)} $txt")
            }
        }
    }

    fun WARNING(txt:String)
    {
        t_run {
            if (clog(Level.WARNING))
            {
                PS.println("${header_prs(Level.WARNING)} $txt")
            }
            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs(Level.WARNING)} $txt")
            }
        }
    }

    fun ERROR(txt:String)
    {
        t_run {
            if (clog(Level.INFO))
            {
                PS_ERR.println("${header_prs(Level.ERROR)} $txt")
            }
            if (LOG_FILE != null)
            {
                LOG_FILE?.println("${header_prs(Level.ERROR)} $txt")
            }
        }
    }
}