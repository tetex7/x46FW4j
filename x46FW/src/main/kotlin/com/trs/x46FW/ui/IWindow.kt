package com.trs.x46FW.ui.console

import java.util.Stack

import com.trs.x46FW.utils.*
import javax.swing.JFrame
private typealias WFUN = () -> Unit
abstract class IWindow : JFrame()
{
    //val cords:vec
    val cord:vec2d<Int> = vec2d(0,0)
    val size:SizeT<Int> = SizeT(15, 15)
    val GSTAK:Stack<Any?> = Stack()
    val run:WFUN? = null
}