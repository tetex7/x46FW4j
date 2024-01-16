@file:JvmName("TypealiasUtils")
@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package com.trs.x46FW.utils


import com.trs.x46FW.DSS.IDemon
import java.lang.ref.Reference

/**
 * [FLAG] is just a [Boolean] flag
 */
typealias FLAG = Boolean

typealias JString = java.lang.String
typealias JInt = java.lang.Integer
typealias JObj = java.lang.Object

/**
 * a dummy object for [synchronized]
 */
typealias Lock = Any

typealias int = Int
typealias bool = Boolean
typealias byte = Byte
typealias long = Long
typealias short = Short

const val OFF = false
const val ON = true

const val YES = true
const val NO = false

const val F_SET = true
const val F_UNSET = false

internal typealias DMAN_DAT_BUFF = HashMap<String, Pair<IDemon, Thread>>

typealias hStrMap<bi> = HashMap<String, bi>

typealias  ref<bi> = Reference<bi>


