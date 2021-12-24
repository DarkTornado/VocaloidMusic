package com.darktornado.vocaloidmusic

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.orientation = 1

        setContentView(layout)
    }
}