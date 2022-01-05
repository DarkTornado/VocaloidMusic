package com.darktornado.vocaloidmusic

class SongInfo (input: String) {
    val title: String
    val ky: String
    val tj: String
    val vocal: String
    val prod: String

    init {
        val data = input.split("::")
        ky = data[0]
        tj = data[1]
        title = data[2]
        vocal = data[3]
        prod = data[4]
    }
}