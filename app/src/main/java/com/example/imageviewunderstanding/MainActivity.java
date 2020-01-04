package com.example.imageviewunderstanding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageButton imgButton;
    ImageView imgView;
    Intent intent;
  //  Button b;
    final static int clickcode = 100;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgButton = findViewById(R.id.imageButton);
        imgView = findViewById(R.id.imageView);
     //   b = findViewById(R.id.wallpaper);
        InputStream inputStream=getResources().openRawResource(R.raw.jelly);
        bitmap= BitmapFactory.decodeStream(inputStream);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Camera Button Click", Toast.LENGTH_SHORT).show();
                intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivityForResult(intent, clickcode);

            }
        });

       /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getApplicationContext().setWallpaper(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");

            imgView.setImageBitmap(bitmap);
            try {
                getApplicationContext().setWallpaper(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

