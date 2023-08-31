package com.trs.x46FW.utils.log4x

import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.Lock
import com.trs.x46FW.utils.toSFstring
import java.io.PrintStream
import java.time.LocalTime
import java.util.regex.Pattern
import java.lang.System.Logger.Level as LLevel

@x46FW_API
class Logger
{
    private val iLock:Lock = Lock()

    companion object
    {
        val h_hour = Pattern.compile("%h%").pattern()!!
        val h_minute = Pattern.compile("%m%").pattern()!!
        val h_second = Pattern.compile("%s%").pattern()!!
        val h_tname = Pattern.compile("%tname%").pattern()!!
        private val h_class_path = Pattern.compile("%cpath%").pattern()!!
        val h_level = Pattern.compile("%level%").pattern()!!
    }

    var PS:PrintStream = System.out!!
    var PS_ERR:PrintStream = System.err!!

    private fun header_prs(ll:LLevel):String
    {
        val io = LOG_HEAD.replace(h_hour, LocalTime.now().hour.toSFstring())
                .replace(h_minute, LocalTime.now().minute.toSFstring())
                .replace(h_second, LocalTime.now().second.toSFstring())
                .replace(h_tname, Thread.currentThread().name)
                .replace(h_level, ll.toString())
        return io
    }

    private fun clog(v:LLevel):FLAG
    {
        return ((level == LLevel.ALL) || (v == level))
    }

    var LOG_HEAD = "[$h_hour:$h_minute:$h_second]:[$h_tname]:[$h_level]:"
    var level:LLevel = LLevel.ALL

    fun INFO(txt:String)
    {
        if (clog(LLevel.INFO)) {
            PS.println("${header_prs(LLevel.INFO)} $txt")
        }
    }

    fun TRACE(txt:String)
    {
        if (clog(LLevel.TRACE)) {
            PS_ERR.println("${header_prs(LLevel.TRACE)} $txt")
        }
    }

    fun DEBUG(txt:String)
    {
        if (clog(LLevel.DEBUG)) {
            PS.println("${header_prs(LLevel.DEBUG)} $txt")
        }
    }

    fun WARNING(txt:String)
    {
        if (clog(LLevel.WARNING)) {
            PS.println("${header_prs(LLevel.WARNING)} $txt")
        }
    }

    fun ERROR(txt:String)
    {
        if (clog(LLevel.INFO)) {
            PS_ERR.println("${header_prs(LLevel.INFO)} $txt")
        }
    }
}