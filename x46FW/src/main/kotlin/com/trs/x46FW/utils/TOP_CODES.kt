package com.trs.x46FW.utils

enum class TOP_CODES(v: Int)
{
    DSS_C  (5000),
    EXP_C  (2000),
    CATE_C (1000),
    DMAN_C (6000),
    //MAT_C(6000),
    JARFS_C(9500);

    val `val` = v

    infix operator fun plus(v:TOP_CODES):Int
    {
        return this.`val` + v.`val`
    }

    infix operator fun plus(iv:Int):Int
    {
        return this.`val` + iv
    }

    infix operator fun minus(iv:TOP_CODES):Int
    {
        return this.`val` - iv.`val`
    }

    infix operator fun minus(iv:Int):Int
    {
        return this.`val` - iv
    }
    /*override fun toString(): String {
        return super.toString().replace("_C", "_ERR")
    }*/
}