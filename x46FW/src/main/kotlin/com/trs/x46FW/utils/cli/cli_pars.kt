package com.trs.x46FW.utils.cli

import com.trs.x46FW.utils.*
import org.apache.commons.cli.*
import kotlin.system.exitProcess
import java.util.Vector

class cli_pars()
{
    private val re:FLAG = false

    private var options = Options()

    var args:Array<String> = arrayOf("NOARG")

    var footer_str = "x46FW cli\ngo to the github the docs <www.github.com/tetex7/x46FW4j>"

    val option_help = Option.builder("h")
        .required(false)
        .desc("well it's help LoL")
        .longOpt("help")
        .build()

    private fun help()
    {
        val o = TRY(exit = true, code = MK_ECODE(TOP_CODES.CATE_C, 50)) {
            for (cv in options.options)
            {
                println("${cv.longOpt!!} ${cv.argName!!} : ${cv.description!!}")
            }
            //println("--help -h : ${options.getOption("help").description}")


            println("")//"discord asm lang lib with a debug ui\ngo to the github the docs <www.github.com/tetex7>")
            exitProcess(0)
        }
    }
    private var commandLine: CommandLine? = null

    fun pars()
    {
        val parser: CommandLineParser = DefaultParser()
        TRY(false, true, exit = true, code = 450) {commandLine = parser.parse(options, args)}
        if (has_arg("help"))
        {
            help()
        }
    }

    fun push(op:Option)
    {
        options.addOption(op)
    }


    init {
        /*for (int i = 0; i < 50; i++)
        {
            System.out.println(i);
        }*/
        if (args.isEmpty())
        {
            TRY(err_box = true, code = 455, exit = true) { throw RuntimeException("YOU FUCKED UP LOL") }

        }
        options.addOption(option_help)
    }

    fun has_arg(arg_name: String): Boolean
    {
        var temp = ""
        temp = if (arg_name.startsWith("-"))
        {
            arg_name.replace("-", "")
        } else if (arg_name.startsWith("--"))
        {
            arg_name.replace("-", "")
        } else
        {
            arg_name
        }
        return commandLine!!.hasOption(temp)
    }

    fun get_arg_val(arg_name: String): String
    {
        var temp = ""
        if (arg_name.startsWith("-"))
        {
            temp = arg_name.replace("-", "")
        }
        else if (arg_name.startsWith("--"))
        {
            temp = arg_name.replace("-", "")
        }
        return commandLine!!.getOptionValue(temp)
    }
}