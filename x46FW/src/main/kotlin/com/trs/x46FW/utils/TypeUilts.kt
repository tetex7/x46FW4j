@file:JvmName("typeUtils")

package com.trs.x46FW.utils


//import com.trs.x46FW.DSS.wintest
import com.trs.x46FW.internal.conf_file
import com.trs.x46FW.internal.wintest
//import com.trs.x46FW.tlang.ValData
import java.io.File
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap

import kotlin.math.abs

fun get_conf() = conf_file.readText(Charsets.US_ASCII)


inline fun delay(millis:Long) = Thread.sleep(millis)

@Suppress("UNCHECKED_CAST")
fun <bi> dud():bi
{
    return null as bi
}

@Suppress("NOTHING_TO_INLINE")
inline fun Any.toObj():JObj
{
    return (this as JObj)
}

val Any.obj:JObj
    inline get()
    {
        return this.toObj()
    }

fun Number.toSFstring(): String
{
    return if(this.toInt() < 10) "0${this.toInt()}" else this.toInt().toString()
}
fun <bi, vet_obj : Vector<Pair<String, bi>>> vet_obj.seekf(str:String, offSet:Int = 0): Pair<String, bi>?
{
    for (i in offSet .. this.size)
    {
        if (this[i].first == str)
        {
            return this[i]
        }
    }
    return null
}

fun <bi, vet_obj : Vector<Pair<String, bi>>> vet_obj.seek(str:String, offSet:Int = 0): bi?
{
    for (i in offSet .. this.size)
    {
         if (this[i].first == str) return this[i].second
    }
    return null
}
fun Boolean.toStrY():String
{
    return if (this) "YES" else "NO"
}

fun Boolean.toStrOff():String
{
    return if(this) "NO" else "OFF"
}

fun SWITCH(value: Boolean):Boolean = !value

operator fun Boolean.inc():Boolean
{
    return SWITCH(this)
}

operator fun Boolean.dec():Boolean
{
    return SWITCH(this)
}

val Boolean.YorN:String
    get(){
        return toStrY()
    }

val Boolean.NorO:String
    inline get(){
        return toStrOff()
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