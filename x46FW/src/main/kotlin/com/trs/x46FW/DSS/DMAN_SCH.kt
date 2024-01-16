package com.trs.x46FW.DSS

import com.trs.x46FW.internal.wintest
import com.trs.x46FW.utils.*
import com.trs.x46FW.internal.x46FW_API
import java.util.*
import kotlin.concurrent.thread
import com.trs.x46FW.internal.*
import com.trs.x46FW.utils.exception.MK_ECODE
import com.trs.x46FW.utils.exception.TRY


//import kotlinx.
/**
 * scheduler for [DMAN] and [Stack] holder
 * @param dvcall the local ctx for scheduling
 * @see DMAN
 */
class DMAN_SCH(dvcall: DMAN) : JObj()
{
    //final val SCH_lock:Lock = Lock()

    val FAS:FLAG = true


    var VET = Stack<Pair<String, IDemon>>()
        get() = run RET@{
            synchronized(this)
            {
                return@RET field
            }

        }
        private set(v) = run RET@{
            synchronized(this)
            {
                field = v
            }
            //this.wait(100010001)
        }

    fun nuke()
    {
        wintest()
        VET = Stack()
        sch_acc()
    }

    private var vcall = dvcall

    operator fun invoke()
    {
        this.sch_acc()
    }

    fun pre_sch_acc()
    {
        val PRI_M = conf.thr_PRI()
        for (PI in PRI_M downTo PRI_M - 4)
        {
            wintest()
            thread(name = "${vcall.dman_name}_PRE_SCH_TASK_$PI")
            {
                TRY(code = MK_ECODE(TOP_CODES.DSS_C, PI))
                {
                    synchronized(this)
                    {
                        XLOG.DEBUG("PI(${PI})")
                        //schf_mutex.lock()
                        val rd = vcall.DA

                        for (i in rd)
                        {
                            if (VET.seekf(i.key) != null)
                            {
                                continue
                            }
                            wintest()
                            if (i.value.first.PRI.toInt() == PI)
                            {
                                //println(i)
                                VET.push(Pair(i.key, i.value.first))
                                XLOG.DEBUG("PUST DEM_DATA(${i.key}, ${i.value.first.GNAME}) TO VET(Stack<Pair<String, IDemon>>)")
                            }
                        }
                    }
                }
            }
        }
    }


    open fun sch_acc()
    {
        //val lock:Lock = Lock()
        for (PI in conf.thr_PRI() downTo 0)
        {
            wintest()
            thread(name = "${vcall.dman_name}_SCH_TASK_$PI")
            {
                TRY(code = MK_ECODE(TOP_CODES.DSS_C, PI))
                {
                    synchronized(this)
                    {
                        XLOG.DEBUG("PI(${PI})")
                        //schf_mutex.lock()
                        val rd = vcall.DA

                        for (i in rd)
                        {
                            wintest()
                            if (i.value.first.PRI.toInt() == PI)
                            {
                                //println(i)
                                VET.push(Pair(i.key, i.value.first))
                                XLOG.DEBUG("PUST DEM_DATA(${i.key}, ${i.value.first.GNAME}) TO VET(Stack<Pair<String, IDemon>>)")
                            }
                        }
                    }
                }
            }
        }
    }
}
