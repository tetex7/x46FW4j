@file:JvmName("typeUtils")

package com.trs.x46FW.utils

import com.trs.x46FW.DSS.IDemon
//import com.trs.x46FW.DSS.wintest
import com.trs.x46FW.internal.wintest
import java.lang.ref.Reference
import kotlin.math.abs

/**
 * [FLAG] is just [Boolean] flag
 */
typealias FLAG = Boolean
//typealias Mutx = Any
/**
 * a dummy object for [synchronized]
 */
typealias Lock = Any
internal typealias DMAN_DAT_BUFF = HashMap<String, Pair<IDemon, Thread>>

typealias  ref<bi> = Reference<bi>

fun Boolean.toStrY():String
{
    return if (this) "YES" else "NO"
}


fun Char.toSH(): Short
{
    wintest()
    return this.code.toShort()
}

fun <bi : Number> bi.toBool(): Boolean
{
    val DAT = this.toInt()
    return (abs(DAT) <= 1)
}

/*fun java.lang.String.toBool()
{

}*/

fun <bi: CharSequence> bi.toBool(): Boolean
{
    for (v in this)
    {
        if (!v.isDigit())
        {
            throw IllegalArgumentException("is")
        }
    }
    val DAT = (this as String).toInt()
    return DAT.toBool()
}


/*fun Boolean.toggle()
{
    val b = !this
    //this = false

}*/

/**
 * this is a infix form of [java.util.HashMap.remove] that has key of type [String]
 * @param k value type of the map
 */
infix fun <k> HashMap<String, k>.remov(name_s:String)
{
    wintest()
    this.remove(name_s)
}

fun <bi> Array<bi>.str(i: Int): String
{
    wintest()
    return this[i].toString()
}

fun Short.str(): String
{
    wintest()
    return this.toString()
}

fun <bi> Array<bi>.str(): String
{
    wintest()
    var ou = String()
    ou = "[ "
    var i = 0
    while (i < this.size)
    {
        if (i == (this.size - 1))
        {
            ou += "${this[i]} ]"
            break
        }
        else
        {
            ou += "${this[i]}, "
        }

        i++
    }
    return  ou
}

fun Array<Byte>.toUByte(): Array<UByte>
{
    wintest()
    val o:Array<UByte> = Array<UByte>(this.size)
    {
        0u
    }
    for (i in 0 .. this.size)
    {
        o[i] = this[i].toUByte()
    }
    return o
}

fun Array<UByte>.toByte(): Array<Byte>
{
    wintest()
    val o:Array<Byte> = Array<Byte>(this.size)
    {
        0
    }
    for (i in 0 .. this.size)
    {
        o[i] = this[i].toByte()
    }
    return o
}

fun <bi : Any> Array<bi>.toString(): String
{
    wintest()
    var ou = String()
    ou = "[ "
    var i = 0
    for (i in 0 .. this.size)
    {
        if (i == (this.size - 1))
        {
            ou += "${this[i]} ]"
            break
        }
        else
        {
            ou += "${this[i]}, "
        }
    }
    return  ou
}