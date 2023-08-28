@file:JvmName("expUtils")
@file:Suppress("NOTHING_TO_INLINE", "NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
package com.trs.x46FW.utils

//mport com.trs.disasm.lexer.INP_STAT
import com.trs.x46FW.internal.wintest
import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.ui.error_box
import java.lang.AssertionError
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

data class EX_DATA(val row_ex:Throwable?, val cex: Ix46FW_error? = row_ex?.to_x46FW_err())
data class TRY_DATA<out bi>(val exed:Boolean, val ex_data: EX_DATA, val rd:bi?, val time:Long = 0)

inline fun <bi1 : Number, bi2 : Number> MK_ECODE(TOP_CODE:bi1, SUP_CODE:bi2): Int
{
    wintest()
    return (TOP_CODE.toInt() + SUP_CODE.toInt())
}

inline fun <bi : Number> MK_ECODE(TOP_CODE:TOP_CODES, SUP_CODE:bi): Int
{
    wintest()
    return (TOP_CODE.`val` + SUP_CODE.toInt())
}


@x46FW_API
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
}

@Suppress("NOTHING_TO_INLINE")
inline fun <bi: Throwable> exp(ex: bi, msg_pt:String, msg_box:Boolean = false, exit:Boolean = false, code:Int = 100, TI:String = "", thr:Thread? = null)
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
        println(ex.STACK_TRACE_STR())
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

fun <bi : Throwable> bi.to_x46FW_err(): Ix46FW_error
{
    val xe = Ix46FW_error::class
    if (this is Ix46FW_error)
    {
        return this

    }
    val m = this.message ?: "NO_MGS"
    val e = Ix46FW_error(m, this)
    if (e.CAS != null)
    {
        val est = e.stackTrace
        e.stackTrace = e.CAS?.stackTrace
        e.CAS?.stackTrace = est
    }
    return e
}

fun Throwable.STACK_TRACE_STR(): String
{
    wintest()
    val __msg__ = "(X) ERROR IN '${this.stackTrace?.get(0)?.methodName}' AT (\"${this.stackTrace?.get(0)?.className}.${this.stackTrace?.get(0)?.methodName}(${this.stackTrace?.get(0)?.fileName}:${this.stackTrace?.get(0)?.lineNumber})\")\n\n" +
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
        catch (ex: Throwable) {

            if (NO_EX) {
                //error_box(ex.to_disasm_err().MSG, false, code, TI)
                //return Pair(Pair(false, ex), null)
                //ex.STACK_TRACE_STR()
                //val ol = DEM_MK(name = "TRY-${Random(4663).nextInt(5995, 999998)}", PRI = 16) {
                val ty = measureTimeMillis {
                    var d: Thread? = null
                    d = thread(name = "TRY-${Random(4663).nextInt(5995, 9559)}")
                    {
                        exp(ex, ex.message ?: "NO_MSG", err_box, false, code, TI, d)
                    }
                }

                //XDMAN[ol.Name] = ol
                return TRY_DATA(false, EX_DATA(ex), null, ty)
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
