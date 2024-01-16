package com.trs.x46FW.utils

data class vec2d<bi : Number>(var x:bi, var y:bi)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is vec2d<*>) return false

        return x == other.x && y == other.y
    }
}


data class SizeT<bi : Number>(var h:bi, var w:bi)


data class vec3d<bi : Number>(var x:bi, var y:bi, var z:bi)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is vec3d<*>) return false

        return x == other.x && y == other.y && z == other.z
    }
}
