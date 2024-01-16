@file:JvmName("expUtils")
@file:Suppress("NOTHING_TO_INLINE", "NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
package com.trs.x46FW.utils.exception

//mport com.trs.disasm.lexer.INP_STAT
import com.trs.x46FW.internal.XLOG
import com.trs.x46FW.internal.wintest
import com.trs.x46FW.ui.error_box
import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.TOP_CODES
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.system.exitProcess
import java.util.*

val EX_STACK:Stack<Throwable> = Stack()

data class EX_DATA(val row_ex:Throwable?, val cex: Ix46FWException? = row_ex?.to_x46FW_err())


data class TRY_DATA<out bi>(val exed: FLAG, val ex_data: EX_DATA, val rd:bi?, val time:Long = 0)

inline fun MK_ECODE(TOP_CODE:Number, SUP_CODE:Number): Int
{
    wintest()
    return (TOP_CODE.toInt() + SUP_CODE.toInt())
}

inline fun MK_ECODE(TOP_CODE: TOP_CODES, SUP_CODE:Number): Int
{
    wintest()
    return (TOP_CODE + SUP_CODE.toInt())
}


/*@x46FW_API
@Deprecated("")
fun assert(value: Boolean, ex:Boolean = false): Boolean
{
    wintest()
    val str = "Assertion failed"
    val _ex = AssertionError(str)

    val _s = "Assertion failed at ${_ex.stackTrace?.get(1)?.className}.${_ex.stackTrace?.get(1)?.methodName}(${_ex.stackTrace?.get(1)?.fileName}:${_ex.stackTrace?.get(1)?.lineNumber})"

    error_box(_s, trem = ex, v = MK_ECODE(100,40))
    if (!value)
    {
        println(_s)
        if (!ex)
        {
            return  value
        }
        else
        {
            while (ex){}
        }
    }
    else
    {
        return value
    }

    return value
}*/

@Suppress("NOTHING_TO_INLINE")
inline fun exp(ex:Throwable, msg_pt:String, msg_box:Boolean = false, exit:Boolean = false, code:Int = 100, TI:String = "", thr:Thread? = null)
{
    //wintest()

    println("(!) $msg_pt")
    //throw ex
    try
    {
        throw ex
    }
    catch (e: Throwable)
    {
        ///println(ex.STACK_TRACE_STR())
        XLOG.TRACE("\n${ex.STACK_TRACE_STR()}")
        if (!exit)
        {
            return
        }
        else
        {
            if (msg_box)
            {
                error_box(ex.message!!, exit, code, TI, thr)
                while (true);
            }
            else
            {
                exitProcess(code)
            }
        }
    }
}

fun <bi : Throwable> bi.to_x46FW_err(): Ix46FWException
{
    if (Ix46FWException::class.java.isAssignableFrom(this::class.java))
    {
        //@Suppress("UNCHECKED_CAST")
        return this as Ix46FWException

    }
    val m = this.message ?: "NO_MGS"
    val _e = this
    val e = Ix46FWException(m, _e)
    /*if (e.CAS != null)
    {
        val est = e.stackTrace.clone()
        e.stackTrace = e.CAS?.stackTrace?.clone()
        e.CAS?.stackTrace = est
    }*/
    return e
}

fun Throwable.STACK_TRACE_STR(): String
{
    wintest()
    val __msg__ = "(!) ERROR IN '${this.stackTrace?.get(0)?.methodName}' AT (\"${this.stackTrace?.get(0)?.className}.${this.stackTrace?.get(0)?.methodName}(${this.stackTrace?.get(0)?.fileName}:${this.stackTrace?.get(0)?.lineNumber})\")\n\n" +
    "STACK_TRACE:\n{\n\t${this.stackTraceToString().replace("\n\t", "\n\t\t").replace("Caused by:","\tCaused by:")}}\n"
    return __msg__
}

@Suppress("LocalVariableName")
fun <bi> TRY(NO_EX: FLAG = false, err_box: FLAG = false, exit: FLAG = true, code:Int = 69, TI:String = "", fu: () -> bi?): TRY_DATA<bi>
{

    try
    {
        wintest()
        try
        {
            return TRY_DATA(true, EX_DATA(null), fu())
        }
        catch (ex: Throwable)
        {
            if (ex is Error || ex is IError)
            {
                exp(ex, ex.message ?: "NO_MSG", err_box, true, code, TI)
            }
            EX_STACK.push(ex)
            if (NO_EX) {
                //error_box(ex.to_disasm_err().MSG, false, code, TI)
                //return Pair(Pair(false, ex), null)
                //ex.STACK_TRACE_STR()
                //val ol = DEM_MK(name = "TRY-${Random(4663).nextInt(5995, 999998)}", PRI = 16) {

                thread(name = "TRY-${Random(4663).nextInt(5995, 9559)}")
                {
                    exp(ex, ex.message ?: "NO_MSG", err_box, false, code, TI, Thread.currentThread())
                }

                //XDMAN[ol.name] = ol
                return TRY_DATA(false, EX_DATA(ex), null, 0)
            }
            exp(ex, ex.message ?: "NO_MSG", err_box, exit, code, TI)


            //throw RuntimeException()

            //return Pair(Pair(false, ex), null)
            //throw RuntimeException()
            return TRY_DATA(false, EX_DATA(ex), null)
        }
    }
    catch (ex:Throwable)
    {
        ex.printStackTrace()
        exitProcess(MK_ECODE(TOP_CODES.EXP_C + TOP_CODES.CATE_C, 69))
    }
}
