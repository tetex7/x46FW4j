package com.trs.x46FW.DSS;

import kotlin.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

class DMANa {
    public static void msc(HashMap<String, Pair<IDemon, Thread>> ctx, DMAN_SCH sch)
    {
        synchronized (sch.getSCH_lock()) {
            Vector<Map.Entry<String, Pair<IDemon, Thread>>> Cx = new Vector<>(ctx.entrySet());

            for (Map.Entry<String, Pair<IDemon, Thread>> v : Cx) {
                for (Map.Entry<String, Pair<IDemon, Thread>> vv : Cx) {
                    if (v.getValue().getFirst().getUuid() == vv.getValue().getFirst().getUuid()) {
                        ctx.remove(vv.getKey());
                    }
                }
            }

            Vector<?> dd;
            sch.sch_acc();
        }
    }
}
