package com.trs.x46FW.DSS;

import kotlin.Pair;
import com.trs.x46FW.DSS.IDemon;
import java.util.Stack;

public class DMAN_SCH
{

    private Stack<Pair<String, IDemon>> VET = new Stack<>();
    private DMAN vcall;

    Stack<Pair<String, IDemon>> getVET()
    {
        return VET;
    }

    void setVET(Stack<Pair<String, IDemon>> v)
    {
        VET = v;
    }

    void nuc()
    {
        VET = new Stack<>();
        sch_acc();
    }

    void sch_acc()
    {
        //System.out.println("FUCK");
        for(int PI=16; PI > -15; PI--)
        {

            //System.out.println("FUCK " + PI);
            for (int i = 0; i < vcall.getDA_A().size(); i++)
            {
                //println("tu: ${i}")
                //System.out.println(vcall.getDA_A().get(i).getFirst());
                if (vcall.getDA_A().get(i).getSecond().getPRI() == PI)
                {
                    //println(i)
                    VET.push(new Pair<>(vcall.getDA_A().get(i).getFirst(), vcall.getDA_A().get(i).getSecond()));
                }
            }
        }
    }

    DMAN_SCH(DMAN dd)
    {
        vcall = dd;
    }
}
