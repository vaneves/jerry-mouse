package com.vaneves.jerrymouse

import java.io.FileInputStream
import java.util.Properties;

class Config(path: String) {

    private val properties: Properties = Properties()

    init {
        properties.load(FileInputStream(path))
    }

    fun get(property: String): String = properties.getProperty(property)
}