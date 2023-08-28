//@file:JvmName("<?")

package com.trs.x46FW.internal
import com.trs.x46FW.utils.Ix46FW_error
import org.apache.commons.lang3.SystemUtils

@Suppress("NOTHING_TO_INLINE")
internal fun wintest()
{
    btc.df()
    if((SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_MAC) && WINT)
    {
        //Thread.currentThread().interrupt()
        //sd()
        throw Ix46FW_error()
        //while ():
    }
    else
    {
        return
    }
}

