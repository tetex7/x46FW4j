package com.trs.x46FW.DSS

import com.trs.x46FW.internal.wintest
import com.trs.x46FW.utils.*
import com.trs.x46FW.internal.x46FW_API
import java.util.*
import kotlin.concurrent.thread
import com.trs.x46FW.internal.*


//import kotlinx.
/**
 * scheduler for [DMAN] and [Stack] holder
 * @param dvcall the local ctx for scheduling
 * @see DMAN
 */
@x46FW_API
class DMAN_SCH(dvcall: DMAN) : JObj()
{
    //final val SCH_lock:Lock = Lock()

    val FAS:FLAG = true


    final var VET = Stack<Pair<String, IDemon>>()
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

    fun sch_acc()
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
