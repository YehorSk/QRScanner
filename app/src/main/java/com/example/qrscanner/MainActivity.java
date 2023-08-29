package com.example.qrscanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan_btn = findViewById(R.id.scan_btn);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
    }

    private void scanCode() {
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setPrompt("Volume up to turn on flash");
        scanOptions.setBeepEnabled(false);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(scanOptions);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result -> {
        if(result.getContents() !=null){
            Intent intent = new Intent(MainActivity.this,ResultActivity.class);
            intent.putExtra("Link",result.getContents());
            startActivity(intent);
        }
    });
}