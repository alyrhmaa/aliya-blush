package com.example.aliya_blush.pertemuan_6

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.aliya_blush.databinding.ActivityWebviewBinding

class WebView_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Web Desa"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("http://aliya-2sic.alwaysdata.net/")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}