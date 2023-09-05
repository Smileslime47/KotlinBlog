package moe.saikyo47.utils

import java.io.File
import java.io.InputStream

class FileHandler {
    companion object{
        fun getTextFile(path:String):String = File(path).readText()
    }
}