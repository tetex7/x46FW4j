package com.trs.x46FW.utils.qlang

import com.trs.x46FW.utils.arsize
import com.trs.x46FW.utils.toSFstring
import java.time.LocalDate
import java.time.LocalTime
import java.util.Vector
import kotlin.random.Random
import com.trs.x46FW.utils.qlang.libstd.*


class Qlang
{
    private val pats:Vector<Pair<String, Qlang_inst>> = Vector()

    companion object
    {
        const val q_rand = "%rand%"
        const val q_ntap = "%ntap%"
        const val q_nntap = "%nntap%"
    }

    val tok = '$'

    init
    {

        this[q_rand] = { Random.nextInt(1221, 991199).toString() }
        this[q_ntap] = { "\t\n" }
        this[q_nntap] = { "\t\n\n" }
    }

    fun initLibSTD()
    {
        /*.replace(Logger.h_hour, LocalTime.now().hour.toSFstring())
        .replace(Logger.h_minute, LocalTime.now().minute.toSFstring())
        .replace(Logger.h_second, LocalTime.now().second.toSFstring())
        .replace(Logger.h_tname, Thread.currentThread().name)
        .replace(Logger.p_temp, System.getProperty("java.io.tmpdir"))
        .replace(Logger.q_rand, Random.nextInt(1221, 991199).toString())
        .replace(Logger.h_month, LocalDate.now().month.value.toSFstring())
        .replace(Logger.h_day, LocalDate.now().dayOfMonth.toSFstring())
        .replace(Logger.h_year, LocalDate.now().year.toSFstring())
        .replace(Logger.h_tname, Thread.currentThread().name)*/
        this[h_hour] = { LocalTime.now().hour.toSFstring() }
        this[h_minute] = { LocalTime.now().minute.toSFstring() }
        this[h_second] = { LocalTime.now().second.toSFstring() }
        this[h_tname] = { Thread.currentThread().name }
        this[p_temp] = { System.getProperty("java.io.tmpdir") }
        this[h_month] = { LocalDate.now().month.value.toSFstring() }
        this[h_day] = { LocalDate.now().dayOfMonth.toSFstring() }
        this[h_year] = { LocalDate.now().year.toSFstring() }

    }

    operator fun invoke(txt:String):String
    {
        return pars(txt)
    }

    fun pars(text:String):String
    {
        var rou = text
        //val ou =
        for (i in 0 .. pats.size.arsize)
        {
            rou = rou.replace(pats[i].first, pats[i].second.work(), false)
        }
        return rou
    }

    operator fun set(regex_p: String, v:Qlang_inst)
    {
        var pp_regex_p = regex_p
        /*if (regex_p.first() != tok)
        {
            pp_regex_p = "$tok$regex_p$tok"
        }
        else if (regex_p.last() != tok)
        {
            pp_regex_p = "$tok$regex_p%"
        }
        else
        {
            pp_regex_p = "$tok$regex_p%$tok"
        }*/

        pats.add(Pair(pp_regex_p, v))
    }

    operator fun set(regex_p: String, fv:()->String)
    {
        val pp_regex_p = regex_p
        /*if (regex_p.first() != tok)
        {
            pp_regex_p = "$tok$regex_p$tok"
        }
        else if (regex_p.last() != tok)
        {
            pp_regex_p = "$tok$regex_p%"
        }
        else
        {
            pp_regex_p = "$tok$regex_p%$tok"
        }*/

        pats.add(Pair(pp_regex_p, Qlang_inst(fv)))
    }
}