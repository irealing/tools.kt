package cn.fuser.util

import java.util.*
import kotlin.reflect.KProperty

// 只读属性异常
class ReadOnlyPropException : RuntimeException()


/*
* 代理对象属性
* */
interface PropProxy<T> {
    //get
    operator fun getValue(ref: Any?, kp: KProperty<*>): T

    //set
    operator fun setValue(ref: Any, kp: KProperty<*>, value: T)
}

abstract class BasePropProxy<T>(protected val prop: Properties, protected val key: String, protected val default: T) : PropProxy<T> {
    //set
    override operator fun setValue(ref: Any, kp: KProperty<*>, value: T) = throw ReadOnlyPropException()
}

class StringPropProxy(prop: Properties, key: String, default: String = "") : BasePropProxy<String>(prop, key, default) {

    override operator fun getValue(ref: Any?, kp: KProperty<*>): String = prop.getProperty(key) ?: default
}

class IntPropProxy(prop: Properties, key: String, default: Int = -1) : BasePropProxy<Int>(prop, key, default) {
    override operator fun getValue(ref: Any?, kp: KProperty<*>): Int = prop.getProperty(key)?.toInt() ?: default
}

class FloatPropProxy(prop: Properties, key: String, default: Float = -1f) : BasePropProxy<Float>(prop, key, default) {
    override fun getValue(ref: Any?, kp: KProperty<*>): Float = prop.getProperty(key)?.toFloat() ?: default
}

// DoublePropProxy 代理Double类型
class DoublePropProxy(prop: Properties, key: String, default: Double = 1.0) : BasePropProxy<Double>(prop, key, default) {
    override fun getValue(ref: Any?, kp: KProperty<*>): Double = prop.getProperty(key)?.toDouble() ?: default
}

// LongPropProxy 代理Long类型
class LongPropProxy(prop: Properties, key: String, default: Long = 0) : BasePropProxy<Long>(prop, key, default) {
    override fun getValue(ref: Any?, kp: KProperty<*>): Long = prop.getProperty(key)?.toLong() ?: default
}

// BoolPropProxy 代理boolean类型
class BoolPropProxy(prop: Properties, key: String, default: Boolean = false) : BasePropProxy<Boolean>(prop, key, default) {
    override fun getValue(ref: Any?, kp: KProperty<*>): Boolean = prop.getProperty(key)?.toBoolean() ?: default
}