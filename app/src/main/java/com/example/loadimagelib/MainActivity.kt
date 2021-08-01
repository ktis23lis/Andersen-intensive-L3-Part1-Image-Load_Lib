package com.example.loadimagelib

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import javax.security.auth.callback.Callback


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var pushUrlEditText: EditText
    private lateinit var button: Button
    private var url: String = ""

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        btnClick(button)
    }

    private fun initView() {
        imageView = findViewById(R.id.imageView)
        pushUrlEditText = findViewById(R.id.pushUrlEditText)
        button = findViewById(R.id.button)
    }

    private fun btnClick(btn: Button) {
        btn.setOnClickListener {
            url = pushUrlEditText.text.toString()
            loadPage(url, imageView)
        }
    }

    private fun loadPage(page: String, imageView: ImageView) {
        Picasso.get()
                .load(page)
                .into(imageView, object : Callback, com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        Log.d(TAG, resources.getString(R.string.success))
                    }
                    override fun onError(e: Exception?) {
                        Toast.makeText(this@MainActivity, R.string.enter_url, Toast.LENGTH_SHORT).show()
                    }
                })
    }
}