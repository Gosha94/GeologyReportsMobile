package com.teligent.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayImage extends AppCompatActivity {

    private ImageView extraImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        extraImageView = findViewById(R.id.extra_image_view);

        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra("image_path"));
        extraImageView.setImageBitmap(bitmap);
    }
}