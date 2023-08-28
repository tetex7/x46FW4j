@file:JvmName("JarFsUtils")

package com.trs.x46FW.utils
//import com.trs.x46FW.DSS.wintest
import com.trs.x46FW.internal.wintest
import java.io.InputStream


internal fun filegetr(p: String): String
{
    wintest()
    var fi: String
    if (p.startsWith("JAR:/"))
    {
        fi = p.replace("JAR:/", "")
    }
    else
    {
        if (p.startsWith("/"))
        {
            fi = p.replace("/", "")
        }
        else
        {
            fi = p
        }
    }
    return fi
}


fun getfile(path: String): InputStream
{
    wintest()
    val d: Any = Any()
    return d.getfile(path)!!
}

fun getfileA(path: String): List<String>?
{
    wintest()
    val d: Any = Object()
    return d.getfileA(path)
}

fun  <bi : Any> bi.getfile(path: String): InputStream?
{
    wintest()
    val o = TRY(NO_EX = false, code = MK_ECODE(TOP_CODES.JARFS_C, 20)) RET@{
        return@RET this.javaClass
                .classLoader
                .getResourceAsStream(filegetr(path)) ?: throw IllegalArgumentException("${path} is not found\n\tBREAK TO \"${filegetr(path)}\"")
    }
    return o.rd
}

fun  <bi : Any> bi.getfileA(path: String): List<String>?
{
    wintest()
    val d = this.javaClass
        .classLoader
        .getResourceAsStream(filegetr(path)) ?: return null
    return d.reader().readLines()
}

/*internal class JarFs
{
    fun  getfile(path: String): InputStream
    {
            return this.getfile(path)
    }
}*/
