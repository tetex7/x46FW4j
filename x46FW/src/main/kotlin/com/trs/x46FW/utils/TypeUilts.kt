@file:JvmName("typeUtils")

package com.trs.x46FW.utils


//import com.trs.x46FW.DSS.wintest
import com.trs.x46FW.internal.wintest
import java.io.File
import java.io.OutputStreamWriter
import com.trs.x46FW.internal.nativeUtils.PrintUils

import kotlin.math.abs

fun printf(s:String) = PrintUils.printf(s)

@Suppress("UNCHECKED_CAST")
fun <bi> dud():bi
{
    return Any() as bi
}

val String.strlen:Long
    get()
    {
        return PrintUils.Strlen(this)
    }
val String.rstrlen:Long
    get()
    {
        return this.strlen + 1
    }

fun Number.toSFstring(): String
{
    return if(!(this.toInt() > 10)) "0${this.toInt()}" else this.toInt().toString()
}


fun Boolean.toStrY():String
{
    return if (this) "YES" else "NO"
}

val Boolean.YorN:String
    get(){
        return toStrY()
    }


fun Char.toSH(): Short
{
    wintest()
    return this.code.toShort()
}

fun Number.toBool(): Boolean
{
    val DAT = this.toInt()
    return (abs(DAT) <= 1)
}

fun String.toBool(): Boolean
{
    for (v in this)
    {
        if (!v.isDigit())
        {
            throw IllegalArgumentException("is")
        }
    }
    val DAT = (this).toInt()
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


operator fun Char.times(by:Number):String
{
    var str = ""
    for (i in 0..by.toLong())
    {
        str += this
    }
    return str
}

fun <bi> Array<bi>.toString(): String
{
    wintest()
    var ou = String()
    ou = "[ "
    //var i = 0
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