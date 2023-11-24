@file:Suppress("PackageName")

package com.trs.x46FW.DSS

import com.trs.x46FW.utils.Event
import com.trs.x46FW.utils.FLAG
import java.util.*
import kotlin.random.Random

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
 *
 * ```
 * object Inc_and_Print : IDemon {
 *      override val uuid: UUID = UUID.randomUUID()
 *      override var R: FLAG = false
 *      override val name: String = "Inc_and_Print"
 *      override var DMA:DMAN? = null
 *      override var E: FLAG = false
 *      override val D: FLAG = false
 *      override val THR: Thread = Thread.currentThread()
 *      override val DID:Short = Random.nextInt(Short.MAX_VALUE.toInt()).toShort()
 *      override var STAT: TR_STAT = TR_STAT.TR_NA
 *      override var PRI:Short = 10
 *      override val GNAME: String = "${this.name}-($uuid)-[$DID]"
 *
 *      fun prit()
 *      {
 *          for(i in 0 .. 10)
 *          {
 *              println(i)
 *          }
 *      }
 *
 *      override val run: () -> Unit = rRET@{
 *          R = true
 *          STAT = TR_STAT.TR_OK
 *          prit()
 *          if (E)
 *          {
 *              STAT = TR_STAT.TR_ERR
 *              return@rRET
 *          }
 *          STAT = TR_STAT.TR_STOPED
 *          R = false
 *      }
 * }
 * ```
 *
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
    val work_stack:Stack<Any>
    val name:String
    val GNAME:String
    var DMA:DMAN?
    var R: FLAG
    var E: FLAG
    val D: FLAG
    val THR:Thread
    var STAT:TR_STAT
    val DID:Short

    /**
     * the priority of [IDemon] for the [DMAN_SCH]
     */
    var PRI:Short
    val uuid:UUID

    /**
     * runs the IDemon on the local [DMAN] through [D]
     * on the back end it runs [DMAN.frun]
     * ```
     * import com.trs.x46FW.DSS.DEM_MK
     * import com.trs.x46FW.DSS.DMAN
     *
     * fun main()
     * {
     *      val dman = DMAN()
     *
     *      val damon = DEM_MK(PRI = 15) {
     *          println(this.name)
     *      }
     *
     *      damon(dman)
     * }
     * ```
     *
     * @param D the local [DMAN]
     * @see DMAN.frun
     * @see DEM_MK
     * @author Tete
     */
    operator fun invoke(D:DMAN)
    {
        D frun this
    }

    /**
     * runs the IDemon through a [Event] never hitting a [DMAN] or a [DMAN_SCH]'s stack
     * ```kt
     * import com.trs.x46FW.DSS.DEM_MK
     *
     * fun main()
     * {
     *
     *      val damon = DEM_MK(PRI = 15) {
     *          println(this.name)
     *      }
     *
     *      damon()
     * }
     * ```
     *
     * @see com.trs.x46FW.utils.Event
     * @see DEM_MK
     * @author Tete
     */
    operator fun invoke()
    {
        val V:Event = Event(true, this.run)
        V()
    }

    fun DAT_BLOCK():IDemon_DAT_BLOCK
    {
        return IDemon_DAT_BLOCK(
                name,
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
}

const val RESV:Short = -5

/**
 * Creates a [IDemon] that runs the specified [block] of code.
 * ```kt
 * import com.trs.x46FW.DSS.DEM_MK
 * import com.trs.x46FW.DSS.DMAN
 *
 * fun main()
 * {
 *      val dman = DMAN()
 *
 *      val o = DEM_MK(PRI = 15) {
 *          println(this.name)
 *      }
 *
 *      dman add o
 * }
 * ```
 *
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
        override val work_stack: Stack<Any> = Stack<Any>()
        override val uuid: UUID = _uuid
        override var R: FLAG = false
        override val name: String = name
        override var DMA:DMAN? = null
        override var E: FLAG = false
            set(value) {
                field = value
                if (field)
                {
                    STAT = TR_STAT.TR_ERR
                }
            }
        override val D: FLAG = false
        override val THR: Thread = Thread.currentThread()
        override val DID:Short = did
        override var STAT: TR_STAT = TR_STAT.TR_NA
        override var PRI:Short = PRI
        override val GNAME: String = "${this.name}-($uuid)-[$DID]"
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
                R = false
                return@rRET
            }
            STAT = TR_STAT.TR_STOPED
            R = false
        }
    }
    return ID
}

/*fun Stack<Triple<String, IDemon, DMAN>>.dumpR():String
{
    var so = String()
    so += "VET(${this[0].third.dman_name}):\n{\n\n"
    for (vae in this)
    {
        so += "\t${vae.second.toString().replace("\t", "\t\t")}"
    }
    so += "}"
    return so
}*/