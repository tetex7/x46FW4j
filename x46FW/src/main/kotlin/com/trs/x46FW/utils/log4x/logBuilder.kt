package com.trs.x46FW.utils.log4x
import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.FLAG
import java.io.PrintStream
@x46FW_API
class logBuilder
{


    private var logger:Logger = Logger()

    var toFile:FLAG
        get()
        {
            return logger.toFile
        }
        set(`val`)
        {
            logger.toFile = `val`
        }

    var logHaed:String
        get() = logger.LOG_HEAD
        set(value)
        { logger.LOG_HEAD = value}

    var logFilePath:String
        set(value) {
            logger.log_path = value
        }
        get() = logger.log_path

    fun setPS(ps:PrintStream):logBuilder
    {
        logger.PS = ps
        return this
    }

    fun head(head:String): logBuilder
    {
        logHaed = head
        return this
    }

    var toCAN:FLAG
        get() = logger.toCAN
        set(value)
        {
            logger.toCAN = value
        }

    fun file(v:FLAG): logBuilder
    {
        toFile = v
        return this
    }

    fun CAN(v:FLAG): logBuilder
    {
        toCAN = v
        return this
    }

    fun path(p:String): logBuilder
    {
        logFilePath = p
        return this
    }

    fun setERR(ps:PrintStream):logBuilder
    {
        logger.PS_ERR = ps
        return this
    }

    fun build():Logger = logger
}