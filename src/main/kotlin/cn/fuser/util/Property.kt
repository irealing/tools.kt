package cn.fuser.util

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.file.Paths
import java.util.*

class Property private constructor(fileName: String) {
    private val prop = Properties()

    init {
        val cwd = System.getProperty("user.dir") ?: ""
        val cfgFile = Paths.get(cwd, fileName).toFile()
        val input = when {
            cfgFile.exists() -> FileInputStream(cfgFile)
            else -> this::class.java.classLoader.getResourceAsStream(fileName)
        } ?: throw FileNotFoundException(fileName)
        prop.load(input)
        input.close()
    }

    companion object {
        fun create(fileName: String = "config.properties"): Property {
            return Property(fileName)
        }
    }

    fun string(key: String, default: String = ""): PropProxy<String> = StringPropProxy(prop, key, default)
    fun int(key: String, default: Int = -1): PropProxy<Int> = IntPropProxy(prop, key, default)
    fun float(key: String, default: Float = -1f): PropProxy<Float> = FloatPropProxy(prop, key, default)
    fun double(key: String, default: Double = 1.0): PropProxy<Double> = DoublePropProxy(prop, key, default)
}