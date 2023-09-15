package com.trs.x46FW.utils.BL84

import org.jetbrains.annotations.Range
import java.time.LocalTime
import kotlin.random.Random

class Keys
{
    var sk1:UShort
    var sk2:UShort
    var sk3:UShort
    var sdd:UByte

    constructor()
    {
        val t = LocalTime.now()
        sk1 = (Random.nextInt(12, 255).toUShort() * t.hour.toUShort() + (15).toUShort()).toUShort()
        sk2 = (Random.nextInt(12, 255).toUShort() * t.hour.toUShort() + (15).toUShort()).toUShort()
        sk3 = (Random.nextInt(12, 255).toUShort() * t.hour.toUShort() + (15).toUShort()).toUShort()
        sdd = Random.nextInt(1, 0xF).toUByte()
    }

    constructor(rsk1:UShort, rsk2:UShort, rsk3:UShort, rsdd:UByte)
    {
        sk1 = rsk1
        sk2 = rsk2
        sk3 = rsk3
        sdd = rsdd
    }
}