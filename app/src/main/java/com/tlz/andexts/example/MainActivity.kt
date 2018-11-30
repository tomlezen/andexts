package com.tlz.andexts.example

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tlz.andexts.dp2px
import com.tlz.andexts.sp2px
import com.tlz.andexts.span.spannableStringBuilder
import com.tlz.andexts.windowStatusBarColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_text.text = spannableStringBuilder(this){
            append("第一行文字")
            foregroundColor(Color.RED)
            append("\n")
            appendImage(R.mipmap.ic_launcher_for_test)
            append("第二行文字")
            appendSpace(dp2px(10))
            append("第二行文字第二段")
            fontSize(sp2px(20))
        }.create()

        this.windowStatusBarColor = Color.BLUE
    }
}
