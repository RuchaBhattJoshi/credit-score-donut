package com.ruchabhattjoshi.cstask

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform