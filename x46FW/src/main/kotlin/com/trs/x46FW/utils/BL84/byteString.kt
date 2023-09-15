package com.trs.x46FW.utils.BL84

import com.trs.x46FW.utils.arsize
import java.lang.IllegalArgumentException
import java.util.stream.IntStream

@OptIn(ExperimentalUnsignedTypes::class)
class byteString(strA: CharArray) : CharSequence//, Comparable<byteString>
{
    override val length: Int = strA.size

    var buff:UByteArray = UByteArray(strA.size)

    init
    {
        for (i in 0 .. strA.size.arsize)
        {
            buff[i] = strA[i].code.toByte().toUByte()
        }
    }

    constructor(str:String) : this(strA = str.toCharArray())

    override fun get(index: Int): Char
    {
        return Char(buff[index].toByte().toInt())
    }

    override fun subSequence(startIndex: Int, endIndex: Int): CharSequence
    {
        val charArray:CharArray = CharArray(endIndex - startIndex)
        for (i in startIndex .. startIndex)
        {
            charArray[i] = this[i]
        }
        return String(charArray)
    }

    val charString:String
        get() {
            var b:CharArray = CharArray(buff.size)
            for (i in 0 .. buff.size)
            {
                b[i] = Char(buff[i].toInt())
            }
            return String(b)

        }

    override fun toString(): String
    {
        return charString
    }

    override fun isEmpty(): Boolean
    {
        return buff.isEmpty()
    }

    override fun chars(): IntStream
    {
        //val ib = IntArray(buff.size)
        val b = IntStream.builder()
        for (i in 0 .. buff.size)
        {
            b.add(buff[i].toInt())
        }

        return b.build()!!
    }

    override fun equals(other: Any?): Boolean
    {
        if ((other is CharSequence))
        {
            val strbuf = other as String
            return strbuf == this.toString()
        }
        else
        {
            throw IllegalArgumentException()
        }
    }
}