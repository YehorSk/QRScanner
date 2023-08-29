package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    TextView date;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Scan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        date = (TextView) findViewById(R.id.textDate);
        link = (TextView) findViewById(R.id.textLink);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        date.setText(sdf.format(new Date()));
        Intent intent = getIntent();
        link.setText(intent.getStringExtra("Link"));

    }

    public void copyText(View view){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("link",link.getText().toString()));
        Toast.makeText(this,"Text Copied",Toast.LENGTH_LONG).show();
    }
    public void openText(View view){
        String search = "https://www.google.com/search?q="+link.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(search));
        startActivity(intent);
    }
}