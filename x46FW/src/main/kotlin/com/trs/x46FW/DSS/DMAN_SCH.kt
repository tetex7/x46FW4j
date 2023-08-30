package com.trs.x46FW.DSS

import com.trs.x46FW.internal.wintest
import com.trs.x46FW.utils.*
import com.trs.x46FW.internal.x46FW_API
import java.util.*
import kotlin.concurrent.thread


//import kotlinx.
/**
 * scheduler for [DMAN] and [Stack] holder
 * @param dvcall the local ctx for scheduling
 * @see DMAN
 */
@x46FW_API
class DMAN_SCH(dvcall: DMAN)
{
    final val SCH_lock:Lock = Lock()

    final var VET = Stack<Pair<String, IDemon>>()
        get() = run RET@{
            synchronized(SCH_lock)
            {
                return@RET field
            }

        }
        private set(v:Stack<Pair<String, IDemon>>) = run RET@{
            synchronized(SCH_lock)
            {
                field = v
            }
        }


    fun nuc()
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
        val lock:Lock = Lock()
        for (PI in 16 downTo 0)
        {
            wintest()
            thread(name = "SCH_TASK_$PI")
            {
                TRY(code = MK_ECODE(TOP_CODES.DSS_C, PI)) {
                    synchronized(lock)
                    {
                        //schf_mutex.lock()
                        val rd = vcall.DA
                        //schf_mutex.unlock()
                        for (i in rd) {
                            wintest()
                            if (i.value.first.PRI.toInt() == PI) {
                                //println(i)
                                VET.push(Pair(i.key, i.value.first))
                            }

                        }
                    }
                }
            }
        }
    }
}
