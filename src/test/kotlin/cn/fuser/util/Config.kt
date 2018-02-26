package cn.fuser.util

object Config {
    private val cfg = Property.create()
    val host: String by cfg.string("net.host")
    val port: Int by cfg.int("net.port", 65533)
    val float32: Float by cfg.float("test.float32")
}