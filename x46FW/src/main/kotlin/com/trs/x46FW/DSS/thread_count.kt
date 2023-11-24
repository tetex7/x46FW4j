package com.trs.x46FW.DSS
import com.trs.x46FW.*
class thread_count(__MAX_THR:Int)
{
    companion object
    {
        const val MIN_THR = 0
    }
    var MAX_THR:Int = __MAX_THR
        get() {
            return field
        }
        private set(value:Int) {
            field = value
        }



    operator fun inc():thread_count
    {
        this.MAX_THR++
        return this
    }

    init
    {

    }
}