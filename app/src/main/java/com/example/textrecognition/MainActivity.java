package com.example.textrecognition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {

    private Button CaptureImage, ShowText;
    private TextView ScannedText;
    private ImageView ScanImage;
    private Bitmap imageBitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CaptureImage = findViewById(R.id.captureImageBtn);
        ShowText = findViewById(R.id.showTextBtn);
        ScannedText = findViewById(R.id.scanText);
        ScanImage = findViewById(R.id.scanImage);

        CaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckPersmission()){
                    captureImage();

                }else{
                    requestPermission();
                }

            }
        });

        ShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetectText();
            }
        });

    }
    private boolean CheckPersmission(){
        int cameraPermisson = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return cameraPermisson == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(){
        int PERMISSION_CODE = 200;
        ActivityCompat.requestPermissions(this, new String[]{CAMERA},PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0){
            boolean cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if(cameraPermission){
                Toast.makeText(this, "Permission Granted...", Toast.LENGTH_SHORT).show();
                captureImage();
            }
            else{
                Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void captureImage() {
        Intent takePecture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePecture,REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && requestCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ScanImage.setImageBitmap(imageBitmap);

        }
    }

    private void DetectText() {
    }
}