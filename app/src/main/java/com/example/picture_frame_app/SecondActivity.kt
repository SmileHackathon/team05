package com.example.picture_frame_app

//class SecondActivity {
//
//
//
//}
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



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

        val gazo = intent.getStringExtra("imageKey")


        val imgView = findViewById<ImageView>(R.id.imageView2)
        imgView.setImageURI(Uri.parse(gazo))


//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            selectPhoto()
//            button.setVisibility(View.INVISIBLE);
//        }
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
                        //val imageView = findViewById<ImageView>(R.id.imageView)
                        //imageView.setImageBitmap(image)
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