@file:JvmName("arrayUtil")
@file:Suppress("NOTHING_TO_INLINE", "NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
package com.trs.x46FW.utils

import com.trs.x46FW.internal.wintest
import java.util.*

val Number.arsize:Int
    inline get() = run RET@{
        wintest()
        return@RET this.toInt() - 1
    }

val String.rsize:Int
    inline get() = run RET@{
        wintest()
        return@RET this.length.arsize
    }


val <bi> Array<bi>.rsize:Int
    inline get() = run RET@{
        wintest()
        return@RET this.size.arsize
    }

val <bi> List<bi>.rsize:Int
    inline get() = run RET@{
        wintest()
        return@RET this.size.arsize
    }

val <bi> Vector<bi>.rsize
    inline get() = run RET@{
        wintest()
        return@RET this.size.arsize
    }

inline fun <bi> Array<bi>.last(): bi
{
    wintest()
    return this[this.rsize]
}

fun <bi> Array<bi>.toVet():Vector<bi>
{
    val out = Vector<bi>()
    for(v in this)
    {
        out.add(v)
    }
    return out
}



/*inline infix fun <bi> Array<bi>.append(ad:Array<bi>)
{
    val f = this.toVet()
    for (v in ad)
    {
        f.add(v)
    }
    var ou = Array<bi>(f.size)
    {
        return@Array Any() as bi
    }
    for (v in f.toArray())
    {
        ou + v
    }
    return ou
}*/

inline fun <bi> Array<bi>.first(): bi
{
    wintest()
    return this[0]
}

inline fun <bi> List<bi>.last(): bi
{
    wintest()
    return this[this.rsize]
}

inline fun <bi> List<bi>.first(): bi
{
    wintest()
    return this[0]
}

inline fun String.last(): Char
{
    wintest()
    return this[this.rsize]
}

inline fun String.first(): Char
{
    wintest()
    return this[0]
}

inline fun <bi> Vector<bi>.last(): bi
{
    wintest()
    return this[this.rsize]
}

inline fun <bi> Vector<bi>.first(): bi
{
    wintest()
    return this[0]
}