package cn.fuser.util

import java.util.*

class Property private constructor(fileName: String) {
    private val prop = Properties()

    init {
        val input = this::class.java.classLoader.getResourceAsStream(fileName)
        if (input != null) {
            prop.load(input)
            input.close()
        }
    }

    companion object {
        fun create(fileName: String = "config.properties"): Property {
            return Property(fileName)
        }
    }

    fun string(key: String, default: String = ""): PropProxy<String> = StringPropProxy(prop, key, default)
    fun int(key: String, default: Int = -1): PropProxy<Int> = IntPropProxy(prop, key, default)
    fun float(key: String, default: Float = -1f): PropProxy<Float> = FloatPropProxy(prop, key, default)
}