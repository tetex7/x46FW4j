package com.trs.x46FW.utils.BL84

import com.trs.x46FW.utils.arsize


@OptIn(ExperimentalUnsignedTypes::class)
class BLstring
{
    var bf:UShortArray
    var ky:Keys = Keys()
    companion object
    {
        val dkeys:Keys = Keys((545).toUShort(), (415).toUShort(), (7245).toUShort(), (0xF).toUByte())


        private fun encode(v:UByteArray, k:Keys):UShortArray
        {
            var o = UShortArray(v.size)
            for(i in 0 .. v.size.arsize)
            {
                o[i] = (v[i].toUShort() + k.sk1 - k.sk2 - (k.sk3 / k.sdd)).toUShort()
            }
            return o
        }
    }

    constructor(byteStr: byteString)
    {
        bf = encode(byteStr.buff.copyOf(), ky)
    }

    constructor(Str: String)
    {
        bf = encode(byteString(Str).buff.copyOf(), ky)
    }

    operator fun get(index:Int) = uncode()[index]

    fun uncode():String
    {
        val o = bf
        val ch = CharArray(o.size)
        for(i in 0 .. o.size.arsize)
        {
            ch[i] = Char((o[i] - ky.sk1 - ky.sk2 - (ky.sk3 / ky.sdd)).toInt())
        }
        return String(ch)
    }

}