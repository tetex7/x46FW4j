package com.trs.x46FW.DSS

import com.trs.x46FW.utils.FLAG
import com.trs.x46FW.utils.toStrY
import java.util.*

data class IDemon_DAT_BLOCK(
        val Name:String,
        val R: FLAG,
        val E: FLAG,
        val D: FLAG,
        val STAT:TR_STAT,
        val DID:Short,
        val PRI:Short,
        val uuid: UUID,
        val GNAME:String
)
{

    fun RAW_STR(): String {
        val str = "$GNAME: {%NTAB%NAME: $Name%NTAB%R: ${R.toStrY()}%NTAB%E: ${E.toStrY()}%NTAB%D: ${D.toStrY()}%NTAB%STAT: ${STAT}%NTAB%DID: $DID%NTAB%PRI: $PRI%NTAB%UUID: $uuid%NTAB%GNAME: $GNAME%N%}"

        return str
    }

    override fun toString(): String
    {
        return RAW_STR().replace("%NTAB%", "\n\t").replace("%N%", "\n")
    }
}
