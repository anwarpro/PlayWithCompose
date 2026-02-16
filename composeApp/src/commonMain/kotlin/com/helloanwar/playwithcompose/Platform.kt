package com.helloanwar.playwithcompose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform