package com.trs.x46FW.internal.nativeUtils

import java.lang.foreign.*

object PrintUils
{

    /*init
    {
        val so_lib:String = "${System.getProperty("java.io.tmpdir")}/x46FWn-${Random.nextInt()}.so"
        val f: File = File(so_lib)
        f.writeBytes(getfile("JAR:/com/trs/x46FW/libx46FWn.so")?.readAllBytes()!!)
       // f.deleteOnExit()
        System.load(so_lib)
    }*/

    fun printf(str: String):Long
    {
        return Arena.openConfined().use RET@{ arena ->

            // 1. Allocate off-heap memory, and
            // 2. Dereference off-heap memory
            val nativeString = arena.allocateUtf8String(str)

            // 3. Link and call C function

            // 3a. Obtain an instance of the native linker
            val linker = Linker.nativeLinker()

            // 3b. Locate the address of the C function
            val libc = linker.defaultLookup()
            val strlen_addr: MemorySegment = libc.find("printf").get()

                // 3c. Create a description of the native function signature
            val strlen_sig = FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.ADDRESS
            )/*.ofVoid(
                    ValueLayowut.ADDRESS
                )*/

            // 3d. Create a downcall handle for the C function
            val strlen = linker.downcallHandle(strlen_addr, strlen_sig)

                // 3e. Call the C function directly from Java
            return@RET strlen.invokeExact(nativeString) as Long
        }
    }

}