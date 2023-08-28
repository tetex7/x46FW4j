package com.trs.x46FW.ui
import com.trs.x46FW.internal.x46FW_API
import com.trs.x46FW.utils.getfile
//import com.trs.x46FW.internal.XDMAN
import com.trs.x46FW.internal.wintest
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import kotlin.system.exitProcess


@x46FW_API
class error_box(text:String, trem:Boolean = false, v:Int = 1, ti:String = "", thr:Thread? = null): JFrame()
{
    val excde:Int = v
    val thrd = thr
    internal companion object
    {
        init {
            wintest()
        }
    }
    init
    {

        if (trem)
        {
            this.defaultCloseOperation = EXIT_ON_CLOSE
        }
        else
        {
            this.defaultCloseOperation = DISPOSE_ON_CLOSE
        }
        layout = null

        // now frame will be visible, by default it is not visible

        // now frame will be visible, by default it is not visible
        isVisible = true
        if ((ti.isEmpty()) || (ti == "NO_MGS"))
        {
            title = "(!ERROR!)"
        }
        else
        {
            title = "(ERROR: \'${ti}\')"
        }

        setSize(538,220)
        if (trem)
        {
            val b = JButton("exit")
            this.add(b)
            b.setBounds(225, 100, 80, 30)
            b.addActionListener(KIB(this))
        }
        else
        {
            val b = JButton("ok")
            this.add(b)
            b.setBounds(225, 100, 80, 30)
            b.addActionListener(OKB(this))
        }

        val LAB: JLabel = JLabel("${text}", JLabel.CENTER)
        LAB.verticalTextPosition = JLabel.CENTER;
        LAB.setBounds(60, 40, 400, 50)
        //LAB.font
        //LAB.setHorizontalTextPosition(JLabel.CENTER);
        this.add(LAB)

        //LAB.isVisible = true



        //b.action.getValue("")
        // setting button position on screen

        // setting button position on screen
        this.iconImage = ImageIcon(getfile("JAR:/ERROR.png")?.readAllBytes()!!).image


        this.maximumSize = Dimension(538,220)
        this.minimumSize = Dimension(538,220)

        //isResizable = false


    }



    @x46FW_API
    private class KIB(caller: error_box): ActionListener
    {
        internal companion object
        {
            init {
                wintest()
            }
        }
        val vcaller = caller
        override fun actionPerformed(e: ActionEvent?)
        {
            wintest()
            this.vcaller
            //e.
            //vcaller.dispose()
            //println(vcaller.size)
            exitProcess(vcaller.excde)
        }

    }

    @x46FW_API
    private class OKB(caller: error_box): ActionListener
    {
        internal companion object
        {
            init {
                wintest()
            }
        }

        val vcaller = caller
        override fun actionPerformed(e: ActionEvent?)
        {
            wintest()
            //e.
            vcaller.dispose()
            if (vcaller.thrd != null)
            {
                vcaller.thrd?.interrupt()
            }
        }

    }

}

