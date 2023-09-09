package com.trs.x46FW.internal.nativeUtils

import java.lang.foreign.*

object PrintUils
{


    fun printf(str: String):Long
    {
        return Arena.openConfined().use RET@{ arena ->

            // 1. Allocate off-heap memory, and
            // 2. Dereference off-heap memory
            val nativeString = arena.allocateUtf8String(str)

            val linker = Linker.nativeLinker()

            val libc = linker.defaultLookup()
            val printf_addr: MemorySegment = libc.find("printf").get()


            val printf_sig = FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.ADDRESS
            )

            // 3d. Create a downcall handle for the C function
            val printf = linker.downcallHandle(printf_addr, printf_sig)

                // 3e. Call the C function directly from Java
            return@RET printf.invokeExact(nativeString) as Long
        }
    }

}