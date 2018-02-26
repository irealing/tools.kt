package cn.fuser.util

import junit.framework.TestCase

class Config {
    private val prop = Property.create()
    val host: String by prop.string("net.host", default = "192.168.99.123")
    val port: Int by prop.int("net.port", default = 2288)
    val ft: Float by prop.float("test.float32", 1f)
    val double: Double by prop.double("test.double", 2.5)
}

class PropertiesTest : TestCase() {
    private val cfg = Config()
    fun testInt() {
        assertEquals(cfg.port, 2048)
    }

    fun testFloat() {
        assertEquals(cfg.ft, 0.1f)
    }

    fun testString() {
        assertEquals(cfg.host, "127.0.0.1")
    }

    fun testDouble() {
        assertEquals(cfg.double, 18.0)
    }
}