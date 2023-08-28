@file:Suppress("PackageName")

package com.trs.x46FW.DSS

import com.trs.x46FW.utils.FLAG
import kotlin.random.Random
import java.util.UUID

/*enum class DPRI(va:Short)
{
    HIGH(1),
    LOW(0);

    val vg = va

    fun toShort(): Short
    {
        return vg
    }
}

fun Short.toDPRI(): DPRI
{
    if (this <= (1.toShort()))
    {
        return DPRI.HIGH
    }
    else
    {
        return DPRI.LOW
    }
}*/

/**
 * [IDemon] is the base structure for a demon on an instance of [DMAN]
 * @see DEM_MK
 * @see DMAN
 * @author Tete
 */
interface IDemon
{
    /**
     * the main for demons
     * @see DEM_MK
     */
    val run:() -> Unit

    /**
     * the name of the demon
     */
    val Name:String
    val GNAME:String
    var DMA:DMAN?
    var R: FLAG
    var E: FLAG
    val D: FLAG
    val THR:Thread
    var STAT:TR_STAT
    val DID:Short
    var PRI:Short
    val uuid:UUID

    /**
     * runs the IDemon on the local [DMAN] through [D]
     * on the back end it runs [DMAN.frun]
     * @param D the local [DMAN]
     * @see DMAN.frun
     * @see DEM_MK
     * @author Tete
     */
    operator fun invoke(D:DMAN)
    {
        D frun this
    }

    fun DAT_BLOCK():IDemon_DAT_BLOCK
    {
        return IDemon_DAT_BLOCK(
                Name,
                R,
                E,
                D,
                //THR,
                STAT,
                DID,
                PRI,
                uuid,
                GNAME
        )
    }
    //val rrun:() -> Unit
    //fun run()
}

const val RESV:Short = -5

/**
 * Creates a [IDemon] that runs the specified [block] of code.
 * @param block a block of code to run
 * @param name the name of the [IDemon]
 * @param PRI the priority of [IDemon] for the [DMAN_SCH]
 * @param _uuid a UUID for the [IDemon]
 * @return a [IDemon] object
 * @author Tete
 * @see IDemon
 * @see DMAN
 */
fun DEM_MK(
        name:String = "DEM-${Random.nextInt(Short.MAX_VALUE.toInt())}",
        PRI:Short = 0,
        did:Short = Random.nextInt(Short.MAX_VALUE.toInt()).toShort(),
        _uuid:UUID = UUID.randomUUID(),
        block: IDemon.() -> Unit): IDemon {
    val ID = object : IDemon {
        override val uuid: UUID = _uuid
        override var R: FLAG = false
        override val Name: String = name
        override var DMA:DMAN? = null
        override var E: FLAG = false
        override val D: FLAG = false
        override val THR: Thread = Thread.currentThread()
        override val DID:Short = did
        override var STAT: TR_STAT = TR_STAT.TR_NA
        override var PRI:Short = PRI
        override val GNAME: String = "$Name-($uuid)-[$DID]"
        override fun toString(): String
        {
            return this.DAT_BLOCK().toString()
        }
        override val run: () -> Unit = rRET@{
            R = true
            STAT = TR_STAT.TR_OK
            this.block()
            if (E)
            {
                STAT = TR_STAT.TR_ERR
                return@rRET
            }
            STAT = TR_STAT.TR_STOPED
            R = false
        }
    }
    return ID
}