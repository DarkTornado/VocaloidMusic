package com.darktornado.vocaloidmusic

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = 1

        setContentView(layout)
    }

    private fun getSongArray(): Array<SongInfo?>? {
        try {
            val isr = InputStreamReader(assets.open("songs.csv"))
            val br = BufferedReader(isr)
            var str = br.readLine()
            var line = ""
            while (br.readLine().also { line = it } != null) {
                str += "\n$line"
            }
            isr.close()
            br.close()
            val cache = str.split("\n").toTypedArray()
            val result = arrayOfNulls<SongInfo>(cache.size)
            for (n in cache.indices) {
                result[n] = SongInfo(cache[n])
            }
            Arrays.sort(result)
            return result
        } catch (e: Exception) {
        }
        return null
    }

}
