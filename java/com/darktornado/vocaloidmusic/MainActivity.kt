package com.darktornado.vocaloidmusic

import android.app.Activity
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.collections.ArrayList

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = 1
        val songs = getSongArray()!!
        val names = arrayOfNulls<String>(songs.size)
        for (n in songs.indices) {
            names[n] = songs[n]!!.title
        }

        val list = ListView(this)
        list.adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, names)
        list.isFastScrollEnabled = true
        list.onItemClickListener = OnItemClickListener { parent, view, pos, id ->
            Toast.makeText(this, songs[pos]!!.title, 1).show()
        }
        layout.addView(list)

        setContentView(layout)
    }

    private fun getSongArray(): ArrayList<SongInfo>? {
        try {
            val result = ArrayList<SongInfo>()
            val isr = InputStreamReader(assets.open("songs.csv"))
            val br = BufferedReader(isr)
            var line = br.readLine()
            while (line != null) {
                result.add(SongInfo(line))
                line = br.readLine()
            }
            isr.close()
            br.close()
            return result
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), 1).show()
        }
        return null
    }

}
