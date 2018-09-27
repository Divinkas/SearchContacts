package com.example.divinkas.searchcontacts.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.divinkas.searchcontacts.R;

public class OpenPdfActivity extends AppCompatActivity {
    private Context context;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pdf);

        String link = getIntent().getStringExtra("link");

        if (link == null){
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            context = this;
            webView = findViewById(R.id.pdfOpenView);
            String url = "http://drive.google.com/viewerng/viewer?embedded=true&url=" + link;
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }
    }

}
