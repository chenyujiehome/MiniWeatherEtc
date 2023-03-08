package com.example.miniweather

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title_update_btn=findViewById<ImageView>(R.id.title_update_btn)
        // 使用Thread类创建新线程
        val thread = Thread {
            val client = OkHttpClient()
            val url = "https://devapi.qweather.com/v7/weather/now?location=101010100&key=9099fc0d476c46aba08dcb91b74191fd"
            val request = Request.Builder().url(url).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // 处理请求失败的情况
                    Log.e("abc", "请求失败：${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    // 处理请求成功的情况
                    val jsonString = response.body?.string()
                    Log.i("abc", "请求成功：$jsonString")

                }
            })
        }
        thread.start()
//        title_update_btn.setOnClickListener{
//            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show()

//    }
        title_update_btn.setOnClickListener{
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }
}