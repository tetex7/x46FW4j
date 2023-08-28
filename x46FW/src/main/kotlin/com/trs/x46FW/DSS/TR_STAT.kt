package com.trs.x46FW.DSS

import com.trs.x46FW.utils.MK_ECODE
import com.trs.x46FW.utils.TOP_CODES

enum class TR_STAT(v:Int)
{
    TR_NA(0),
    //TR_KILL(666),
    TR_STOPED(MK_ECODE(TOP_CODES.DSS_C, 115)),
    TR_ERR(5),
    TR_OK(3);

    override fun toString(): String {
        return super.toString().replace("TR_", "")
    }
    val VAL = v
}

/*private fun sd()
{
    for (i in Int.MIN_VALUE .. Int.MIN_VALUE)
    {
        thread(block = { sd() })
    }
}*/

