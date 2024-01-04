package com.trs.x46FW.DSS

import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.*
import java.util.Vector

@x46FW_API
class Dman_Iterator(data:DMAN_DAT_BUFF) : Iterator<IDemon>
{
    var IIT = 0
    var i_arr:Vector<IDemon> = Vector()

    init
    {
        for(vi in data)
        {
            i_arr.add(vi.value.first)
        }
        IIT = i_arr.size
    }

    override fun hasNext(): Boolean
    {
        return IIT < i_arr.size
    }

    override fun next(): IDemon
    {
        var i_i = i_arr[IIT]
        IIT++
        return i_i
    }

}