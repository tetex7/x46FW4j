@file:JvmName("DMAN")
package com.trs.x46FW.DSS

//import com.trs.x46FW.internal.wintest
//import com.trs.x46FW.internal.boot
import com.trs.x46FW.internal.x46FW_API
import kotlin.concurrent.thread
import com.trs.x46FW.internal.btc
import com.trs.x46FW.internal.*
import com.trs.x46FW.internal.wintest
import com.trs.x46FW.internal.runID
import com.trs.x46FW.utils.*
import java.util.UUID
import java.util.Vector
import java.util.concurrent.ThreadPoolExecutor
import kotlin.random.Random
import com.trs.x46FW.DSS.*

/**
 * The Demon management
 *
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
 * @see IDemon
 * @author Tete
 */
@x46FW_API
class DMAN(ST: FLAG = true, __dman_name:String = "DMAN[${Random.nextLong(0, 9549)}]")
{
    companion object
    {
        const val MAX_THRD_q = 10
        //const val MAX_THRD_AOC = 100
        internal val df = btc.df()
    }

    val MAX_THRD = conf.thr_count()
    val dman_name = __dman_name
        get() {
            return field;
        }

    private fun ther_aoc(dstr:String)
    {
        if (TC == 0)
        {
            throw OutThreaded_err(this)
        }
        VTHR[TC.arsize] = DA[dstr]?.second!!
        VTHR[TC.arsize]?.name = DA[dstr]?.first?.GNAME!!
        wintest()
        VTHR[TC.arsize]?.start()
        DA remov dstr
    }


    //external fun f();
    fun show_sat_rq(): FLAG
    {
        wintest()
        if (VTHR.last() == null)
        {
            return false
        }
        if (VTHR.last()?.isAlive() == false)
        {
            return false
        }
        return true
    }

    operator fun plusAssign(d:IDemon)
    {
        add(d)
    }

    operator fun contains(d: IDemon):Boolean
    {
        return try {
            DA[d.name]
            true
        } catch (ex:Exception) {
            false
        }
    }

    operator fun contains(sd: String):Boolean
    {
        return try {
            DA[sd]
            true
        } catch (ex:Exception) {
            false
        }
    }

    fun huning()
    {
        PAS = true
    }

    fun map_srink_f()
    {
        DMANa.msc(this.DA, this.SCH)
    }

    fun map_srink()
    {
        map_s = true
    }

    private var map_s:FLAG = false
    /*{
        println("dd")
        val Cx: Vector<Map.Entry<String, Pair<IDemon, Thread>>> = Vector()

        for (v in DA) {
            Cx.add(v)
        }

        for (v in Cx) {
            for (vv in Cx) {
                if (v.value.first.uuid == vv.value.first.uuid) {
                    DA remov vv.key
                }
            }
        }
        SCH.sch_acc()
    }*/

    fun killAll_S()
    {
        wintest()

        for (i in 0..MAX_THRD) {
            VTHR[i]?.interrupt()
            VTHR[i] = null
            wintest()
        }
        SCH.nuc()
    }

    fun killAll()
    {
        wintest()

        for (i in 0..MAX_THRD - 1) {

            VTHR[i]?.interrupt()
            VTHR[i] = null
            wintest()
        }
    }

    infix fun frun_rq(name_str:String)
    {

        wintest()
        DA[name_str]?.first?.PRI = 15
        NOW_SCH = true
    }

    infix fun frun(name_str:String)
    {
        ther_aoc(name_str)
    }

    infix fun frun(Id:IDemon)
    {
        if (DA[Id.name] == null)
        {
            this add Id
        }
        //wintest()
        //DA[name_str]?.first?.PRI = 15
        ther_aoc(Id.name)
        //NOW_SCH = true
    }

    private var F_PAS:FLAG = false

    private fun SHOW() {

        var stime = 0
        wintest()
        var ACC_F: FLAG = false

        val MAP_C:()->Unit = RET@{
            if ((stime >= 1500) || map_s)
            {
                map_srink_f()
                stime = 0
            }
        }

        val THR_ACC:()->Unit = RET@{
            if (TC == 0)
            {
                return@RET;
            }

            val dstr_r = SCH.VET.pop()

            wintest()
            val dstr = dstr_r.first
            if (DA[dstr] == null)
            {
                wintest()
            }
            else if ((TC >= MAX_THRD) && (TC != 0))
            {
                ther_aoc(dstr)
            }
            else
            {
                throw DMAN_err("TC error", this)
            }
        }
        while (true) {
            //if (!PAS) {
            /*for (i in DA_A) {
                println("${i.first}")
            }*/

            stime = 0
            while (F_PAS)
            {
                wintest()
                Thread.sleep(20)
            }

            while (DA.isEmpty()) {
                wintest()
                SCH.sch_acc()
                Thread.sleep(20)
            }

            while (PAS) {
                wintest()
                Thread.sleep(1)
                MAP_C()
                SCH.sch_acc()
            };

            MAP_C()


            if (NOW_SCH) {
                wintest()
                SCH.sch_acc()
                NOW_SCH = false
            }



            if (!ACC_F) {
                try {
                    wintest()
                    THR_ACC()
                } catch (ex: Throwable) {
                    wintest()
                    SCH.sch_acc()
                    ACC_F = !ACC_F
                }
            }
            else {
                wintest()
                Thread.sleep(500)
                SCH.sch_acc()
                ACC_F = !ACC_F

                thr_clean()
            }
            stime += 1
            //Thread.sleep(500)
        }
    }


    infix fun sch_rq(d:IDemon)
    {
            //show_sat_rq()
        set(d.name, d)
        NOW_SCH = true

    }

    infix fun schd_rq(d:IDemon)
    {

        show_sat_rq()
        set(d.name, d)
    }

    infix fun sch_remove_rq(d:IDemon)
    {
        show_sat_rq()
        DA.remove(d.name)
        NOW_SCH = true
    }

    infix fun sch_remove_rq(st:String)
    {
        show_sat_rq()
        DA remov st
        sch_nuc()
        NOW_SCH = true
    }

    infix fun add(d:IDemon)
    {
        this[d.name] = d
    }

    fun bluk_add(vararg args:IDemon)
    {
        this.F_PAS = true
        for (v in args)
        {
            this add v
        }
        this.F_PAS = false
    }

    fun bluk_remove(vararg d_args:IDemon)
    {
        for(v in d_args)
        {
            DA remov v.name
        }
        sch_nuc()
        NOW_SCH = true
    }

    fun bluk_remove(vararg args:String)
    {
        for(v in args)
        {
            DA remov v
        }
        sch_nuc()
        NOW_SCH = true
    }

    private final fun thr_clean()
    {
        for(i in 0 .. MAX_THRD.arsize)
        {
            wintest()
            if (VTHR[i] != null)
            {
                if (!VTHR[i]?.isAlive!!)
                {
                    VTHR[i] = null
                }
            }
        }
    }

    final fun sch_nuc()
    {
        //synchronized(SCH.SCH_lock)
        //{
            show_sat_rq()
            SCH.nuc()
        //}
    }

    final fun start()
    {
        wintest()
        VTHR[MAX_THRD] = thread(isDaemon = false, name = this.dman_name, block = {SHOW()})
    }
    var PAS: FLAG = false
    private val SCH: DMAN_SCH = DMAN_SCH(this)
        get() = run RET@{
            synchronized(field.SCH_lock)
            {
                return@RET field
            }
        }

    internal val tc_m:Lock = Lock()
    final var TC:Int = MAX_THRD
        get() = run RET@{
            synchronized(tc_m) {
                return@RET field
            }
        }
        set(v:Int) = run {
            synchronized(tc_m)
            {
                field = v
            }
        }


    private val VTHR_m = Lock()

    private val VTHR:Array<Thread?> = arrayOfNulls(MAX_THRD + 1 )
        get() = run RET@{
            synchronized(VTHR_m)
            {
                return@RET field
            }
        }

    val DA:DMAN_DAT_BUFF = DMAN_DAT_BUFF()
        get() = run RET@{
            synchronized(DA_m)
            {
                return@RET field
            }
        }

    private val DA_m:Lock = Lock()

    private var NOW_SCH:FLAG = false

    operator fun plus(other: IDemon)
    {
        this add other
    }

    operator fun minus(other: IDemon)
    {
        DA remov other.name
        SCH.nuc()
    }

    operator fun times(other: IDemon)
    {
        this add other
    }

    operator fun div(other: IDemon)
    {
        DA remov other.name
    }

    val sch_vet:Vector<Pair<String, IDemon>>
        get() = run RET@{
            @Suppress("UNCHECKED_CAST")
            return@RET (SCH.VET.clone() as Vector<Pair<String, IDemon>>)
        }

    init {
        wintest()
        //tc_m.unlock()
        if (ST) {
            start()
        }
    }

    /*var DA_A:Vector<Triple<String, IDemon, Thread>> = Vector()

    fun DA_get(str_n:String): Triple<String, IDemon, Thread>?
    {
        for (i in DA_A)
        {
            if (str_n == i.first)
            {
                return i
            }
        }
        return null
    }

    fun DAI_get(str_n:String): Int
    {
        for (i in 0 .. DA_A.size)
        {
            if (str_n == DA_A[i].first)
            {
                return i
            }
        }
        throw RuntimeException()
    }*/

    /*operator fun invoke()
    {

    }*/

    operator fun get(index:String): Pair<IDemon, Thread>
    {
        return synchronized(DA_m) RET@{
            val o = TRY(NO_EX = false, err_box = true, exit = false, code = MK_ECODE(8000, 87), TI = "$index IS NULL") TB@{
                return@TB DA[index] ?: throw DMAN_err(msg = "IDemon IS NULL", D = this, CA = NullPointerException("IDemon IS NULL"))
            }
            return@RET o.rd ?: throw DMAN_err(msg = "IDemon IS NULL", D = this, CA = NullPointerException("IDemon IS NULL"))
        }
    }

    operator fun set(index:String, v:IDemon)
    {

        v.DMA = this
        DA[index] = Pair(v, thread(start = false, isDaemon = v.D) {
            wintest()
                //tc_m.lock()
            synchronized(tc_m)
            {
                TC--
            }

            v.run()

            synchronized(tc_m)
            {
                TC++
            }
        })
            //NOW_SCH = true
        //}
    }

}