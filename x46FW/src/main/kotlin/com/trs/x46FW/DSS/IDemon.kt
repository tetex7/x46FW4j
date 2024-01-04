@file:Suppress("PackageName")

package com.trs.x46FW.DSS

import com.trs.x46FW.internal.XLOG
import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.*
import java.lang.NullPointerException
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
@x46FW_API
abstract class IDemon
{


    /**
     * the main for demons
     * @see DEM_MK
     */
    abstract val run:() -> Unit


    val d = 0
    /**
     * the name of the demon
     */

    val kids:hashStrMap<IDemon> = hashStrMap()
        get() = synchronized(this) { field }
    abstract var dade:IDemon?
    val work_stack: Stack<Any?> = Stack()
        get() = synchronized(this) { field }
    val kids_stack:Stack<DemonStackData> = Stack()
        get() = synchronized(this) { field }
    abstract val name:String
    abstract val GNAME:String
    var DMA:DMAN? = null
        get() = synchronized(this) { field }
        set(value) = synchronized(this) {
            field = value
        }
    var R: FLAG = false
        get() = synchronized(this) { field }
        set(value) = synchronized(this) {
            field = value
        }
    abstract var E: FLAG
    abstract val D: FLAG
    abstract val THR:Thread
    abstract var STAT:TR_STAT
    abstract val DID:Short
    abstract var SNK:FLAG
    abstract val uuid: UUID

    /**
     * the priority of [IDemon] for the [DMAN_SCH]
     */
    abstract var PRI:Short

    abstract val exps: Stack<Throwable?>

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
     * runs the IDemon through a [Event] never hitting a [DMAN] or a [DMAN_SCH]'s [Stack]
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

    fun snk_wait()
    {
        if (dade == null)
        {
            return
        }
        while (this.SNK == ON)
        {
            delay(1)
        }
    }

    fun mount(kid:IDemon)
    {
        if (DMA == null)
        {
            return
        }

        kid.dade = this
        this.kids.put(kid.name, kid)
        send(kid.name, "START", "SOS")
        DMA!!.frun(kid)
    }

    fun callSNK(kname:String, v:FLAG)
    {
        kids[kname]?.SNK = v
    }

    fun callSNKb(v:FLAG)
    {
        dade?.SNK = v
    }

    fun send(kname:String, info_name:String, data:Any?)
    {
        callSNK(kname, ON)
        kids[kname]?.kids_stack?.push(DemonStackData(info_name, data, this))
        callSNK(kname, OFF)
    }

    fun sendB(kname:String, info_name:String, data:Any?)
    {
        callSNKb(ON)
        dade?.kids_stack?.push(DemonStackData(info_name, data, this))
        callSNKb(OFF)
    }



    /*fun addKids(Id:IDemon, F:FLAG = false)
    {
        /*if (!Id.R || !F)
        {
            throw RuntimeException()
        }*/
        if (Id.dade != null)
        {
            throw RuntimeException()
        }
        else
        {
            kids.set(Id.name, Id)
            Id.dade = this
            Id.SNK = ON
            //Id.snk_wait()
        }
    }*/

    /*fun rmKids(name:String)
    {
        kids[name]?.dade = null
        kids.remov(name)
    }

    private fun pushkids(name: String, data:Any?)
    {
        kids[name]?.kids_stack?.push(DemonStackData(name, data, this)) ?: throw NullPointerException()
    }

    private fun pushAll(data:Any?)
    {
        for (v in kids)
        {
            v.value.kids_stack.push(DemonStackData(name, data, this))
        }
    }*/

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
    name:String = "DEM-${Random.nextInt(1441, 94549)}",
    PRI:Short = 0,
    did: Short = Random.nextInt(Short.MAX_VALUE.toInt()).toShort(),
    _uuid:UUID = UUID.randomUUID(),
    block: IDemon.() -> Unit): IDemon {
    val ID = object : IDemon() {

        override var exps: Stack<Throwable?> = Stack()
            get() = synchronized(this) { field }
            set(value) = synchronized(this) {
                field = value
            }

        override val name: String = name
            get() = synchronized(this) { field }
        override var E: FLAG = false
            set(value) = synchronized(this){
                field = value
                if (field)
                {
                    STAT = TR_STAT.TR_ERR
                }
            }
            get() = synchronized(this) { field }
        override val D: FLAG = false
            get() = synchronized(this) { field }
        override val THR: Thread = Thread.currentThread()
            get() = synchronized(this) { field }
        override val DID:Short = did
            get() = synchronized(this) { field }
        override var SNK: FLAG = OFF
            get() = synchronized(this) { field }
            set(value) = synchronized(this) {
                field = value
            }
        override var STAT: TR_STAT = TR_STAT.TR_NA
            get() = synchronized(this) { field }
            set(value) = synchronized(this) {
                field = value
            }
        override val uuid: UUID = _uuid
            get() = synchronized(this) { field }

        override var PRI:Short = PRI
            get() = synchronized(this) { field }
            set(value) = synchronized(this) {
                field = value
            }
        override val GNAME: String = "${this.name}-($uuid)-[$DID]"
            get() = synchronized(this) { field }
        override var dade: IDemon? = null
            get() = synchronized(this) { field }
            set(value) = synchronized(this) {
                field = value
            }

        override fun toString(): String
        {
            return this.DAT_BLOCK().toString()
        }

        override val run: () -> Unit = rRET@{
            R = true
            this.work_stack.push(0b0101110111010)
            //this.work_stack.push(this)

            STAT = TR_STAT.TR_OK
            try
            {
                this.block()
            }
            catch (e:Throwable)
            {
                exps.push(e)
                R = false
                E = true
                //STAT = TR_STAT.TR
                for (v in exps)
                {
                    XLOG.DEBUG(v?.STACK_TRACE_STR()!!)
                }
                return@rRET
            }
            if (E)
            {
                R = false
                return@rRET
            }
            STAT = TR_STAT.TR_ENDED
            R = false
            return@rRET
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