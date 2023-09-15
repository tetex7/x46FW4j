//@file:JvmName("<?")

package com.trs.x46FW.internal
import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.Ix46FW_error
//import jdk.internal.reflect.Reflection
import org.apache.commons.lang3.SystemUtils

/*fun callerClassA(vararg classs: Class<*>):FLAG
{
    for (v in classs)
    {
        if (Reflection.getCallerClass() != v)
        {
            return false
        }
    }
    return true
}*/


internal inline fun nameof():FLAG
{

    //Reflection.
    return true
}

@Suppress("NOTHING_TO_INLINE")
internal fun wintest()
{
    //callerClassA(btc::class.java, btc::class.java)
    //btc.df()
    if((SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_MAC) && WINT)
    {
        throw Ix46FW_error()
    }
    else
    {
        return
    }
}

