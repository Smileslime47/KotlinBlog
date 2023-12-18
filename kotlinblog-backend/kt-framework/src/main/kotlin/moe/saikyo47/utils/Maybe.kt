package moe.saikyo47.utils

class Maybe {
    companion object {
        fun <T> T?.default(default: T): T {
            return this ?: default
        }
    }
}