package com.example.picture_frame_app

//class SecondActivity {
//
//
//
//}
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //（仮）Viewを取得
        val tvTitle : TextView = findViewById(R.id.tvTitle)

        val tvTEXT : TextView = findViewById(R.id.tvTEXT)//(仮２)
        //3)()渡された値を取り出す➩テキストに表示
        val Title = intent.getStringExtra("MY_TITLE")
        tvTitle.text =  Title

        val TEXT = intent.getStringExtra("MY_TEXT") //(仮２)
        tvTEXT.text = TEXT //(仮２)
        

    }





}