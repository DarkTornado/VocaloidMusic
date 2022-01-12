package com.darktornado.vocaloidmusic

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import java.io.BufferedReader
import java.io.InputStreamReader

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

        val txt = EditText(this)
        txt.hint = "검색어 입력..."
        layout.addView(txt)

        val list = ListView(this)
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, names)
        list.adapter = adapter
        list.isFastScrollEnabled = true
        list.isTextFilterEnabled = true
        list.onItemClickListener = OnItemClickListener { parent, view, pos, id ->
            var index = 0
            val text = (view as TextView).text.toString()
            for (n in names.indices) {
                if (text == names.get(n)) {
                    index = n
                    break
                }
            }
            showDialog(songs[index])
        }
        layout.addView(list)

        txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.getFilter().filter(s.toString())
            }

            override fun afterTextChanged(s: Editable) {
                if (txt.text.toString().length == 0) adapter.getFilter().filter(null)
            }
        })

        val pad = dip2px(16)
        layout.setPadding(pad, pad, pad, pad);

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

    private fun showDialog(info: SongInfo) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(info.title)
        dialog.setMessage("금영 : ${info.ky}\n태진 : ${info.tj}")
        dialog.setNegativeButton("닫기", null)
        dialog.show()
    }

    fun dip2px(dips: Int) = Math.ceil((dips * this.resources.displayMetrics.density).toDouble()).toInt()

}
