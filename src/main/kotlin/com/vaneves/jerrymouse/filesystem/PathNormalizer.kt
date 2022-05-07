package com.vaneves.jerrymouse.filesystem

class PathNormalizer(private val root: String) {

    fun getFullPath(path: String): String {
        var root = this.root
        if (! root.endsWith("/") && ! path.startsWith("/")) {
            root += "/"
        }
        return root + path
    }
}