@file:Suppress("unused")

package com.trs.x46FW.internal.nativeUtils

import java.lang.foreign.*

/*internal object memTools
{
    fun xexit(stat:Long)
    {
        Arena.openConfined().use RET@{ arena ->


            // 3. Link and call C function

            // 3a. Obtain an instance of the native linker
            val linker = Linker.nativeLinker()

            // 3b. Locate the address of the C function
            val libc = SymbolLookup.libraryLookup("${System.getProperty("java.io.tmpdir")}/libx46FWn-1.0.so", SegmentScope.auto())
            val xexit_addr: MemorySegment = libc.find("xexit").get()

            // 3c. Create a description of the native function signature
            val xexit_sig = FunctionDescriptor.ofVoid(
                ValueLayout.JAVA_LONG
            )

            // 3d. Create a downcall handle for the C function
            val xexit = linker.downcallHandle(xexit_addr, xexit_sig)

            // 3e. Call the C function directly from Java

            xexit.invokeExact(stat)

            return@RET
        }
    }


    private fun __malloc(size:Long):Long
    {
        return Arena.openConfined().use RET@{ arena ->


            // 3. Link and call C function

            // 3a. Obtain an instance of the native linker
            val linker = Linker.nativeLinker()

            // 3b. Locate the address of the C function
            val libc = linker.defaultLookup()
            val malloc_addr: MemorySegment = libc.find("malloc").get()

            // 3c. Create a description of the native function signature
            val malloc_sig = FunctionDescriptor.of(
                ValueLayout.ADDRESS,
                ValueLayout.JAVA_LONG

            )

            // 3d. Create a downcall handle for the C function
            val malloc = linker.downcallHandle(malloc_addr, malloc_sig)

            // 3e. Call the C function directly from Java

            return@RET malloc.invokeExact(size) as Long
        }
        //return MemorySegment.ofAddress(d)!!
    }

    fun malloc(size:Long):MemorySegment = MemorySegment.ofAddress(__malloc(size))



    fun free(ass:Long)
    {
        Arena.openConfined().use RET@{ arena ->

            // 1. Allocate off-heap memory, and
            // 2. Dereference off-heap memory
            //val nativeString = arena.allocateUtf8String(str)

            // 3. Link and call C function

            // 3a. Obtain an instance of the native linker
            val linker = Linker.nativeLinker()

            // 3b. Locate the address of the C function
            val libc = linker.defaultLookup()
            val free_addr: MemorySegment = libc.find("free").get()

            // 3c. Create a description of the native function signature
            val free_sig = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS
            )

            // 3d. Create a downcall handle for the C function
            val free = linker.downcallHandle(free_addr, free_sig)

            // 3e. Call the C function directly from Java
            free.invokeExact(ass)
            return@RET
        }
    }


    fun free(mem:MemorySegment) = free(mem.address())
}*/