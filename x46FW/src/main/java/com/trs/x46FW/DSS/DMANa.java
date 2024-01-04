package com.trs.x46FW.DSS;

import kotlin.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import com.trs.x46FW.internal.DefsKt;
import org.jetbrains.annotations.NotNull;


class DMANa
{
    public static void msc(@NotNull HashMap<String, Pair<IDemon, Thread>> ctx,@NotNull DMAN_SCH sch)
    {
        synchronized(sch)
        {
            @NotNull Vector<Map.Entry<String, Pair<IDemon, Thread>>> Cx = new Vector<>(ctx.entrySet());

            for (Map.Entry<String, Pair<IDemon, Thread>> v : Cx)
            {
                for (Map.Entry<String, Pair<IDemon, Thread>> vv : Cx)
                {
                    if (v.getValue().getFirst().getUuid().equals(vv.getValue().getFirst().getUuid()))
                    {
                        ctx.remove(vv.getKey());
                        DefsKt.getXLOG().DEBUG("REMOVEED " +
                                vv.getValue().getFirst().getGNAME() +
                                " FROM DMAN MAP"
                        );
                    }
                }

                if (v.getValue().getFirst().getSTAT() == TR_STAT.TR_ENDED)
                {
                    ctx.remove(v.getKey());
                }
            }
            sch.sch_acc();
        }
    }
}
