package com.example.picture_frame_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.graphics.BitmapFactory
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1)Viewの取得
        val btnStart : Button= findViewById(R.id.btnStart)
        //(仮)
        val et:EditText =findViewById(R.id.et)
        val et2:EditText =findViewById(R.id.et2)//(仮２)

        //2)ボタンを押したら次の画面へ
        btnStart.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)

            //3)(仮)タイトルの値を渡す
            intent.putExtra("MY_TITLE",et.text.toString())

            intent.putExtra("MY_TEXT",et2.text.toString())//(仮２)

            startActivity(intent)
        }


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            selectPhoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    data?.data?.also { uri ->
                        val inputStream = contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        val imageView = findViewById<ImageView>(R.id.imageView)
                        imageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }
}
