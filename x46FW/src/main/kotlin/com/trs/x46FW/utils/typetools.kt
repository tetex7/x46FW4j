package com.trs.x46FW.utils

//import com.sun.tools.javac.code.Attribute

inline fun <reified t1 : Class<*>, reified t2 : Class<*>> type_vale_comp(c1:t1, c2:t2):Boolean
{
    return c1 == c2
}

inline fun <reified t1 : Class<*>, reified t2 : Class<*>> type_comp():Boolean
{
    return t1::class == t2::class
}