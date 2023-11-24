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

/**
 * a dummy object for [synchronized]
 */
typealias Lock = Any

internal typealias DMAN_DAT_BUFF = HashMap<String, Pair<IDemon, Thread>>

typealias  ref<bi> = Reference<bi>


