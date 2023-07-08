package com.teligent.mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION_CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE_CODE = 2;
    private static final int IMAGE_REQUEST = 1;

    private String _currentImagePath = null;

    private ImageView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        cameraView = findViewById(R.id.camera_view);
        cameraView = null;
    }

    public void old_captureImage(View view) {
        // Check for camera permission at runtime
        if (
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
        {
            // Request camera permission if not granted
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.CAMERA },
                    REQUEST_CAMERA_PERMISSION_CODE);

            return;
        }

        // Open the camera app
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE_CODE && resultCode == RESULT_OK)
        {
            // Get the captured image as bitmap
            Bundle extras = data.getExtras();
            Bitmap photoFromCamera = (Bitmap) extras.get("data");

            cameraView.setImageBitmap(photoFromCamera);

            saveImageToGallery(photoFromCamera);
        }
    }

    private void saveImageToGallery(Bitmap imageBitmap)
    {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(new Date());
        String fileName = "IMG_" + timeStamp + ".jpg";

        File imageFile = new File(storageDir, fileName);

        try
        {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            outputStream.flush();
            outputStream.close();

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(Uri.fromFile(imageFile));
            sendBroadcast(mediaScanIntent);

            Toast.makeText(this, getResources().getString(R.string.toast_image_saved), Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {

        }
    }

    public void captureImage_v2(View view)
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null)
        {
            File imageFile = null;

            try
            {
                imageFile = getImageFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (imageFile != null)
            {
                Uri imageUri = FileProvider.getUriForFile(
                        this,
                        "com.example.android.fileprovider",
                        imageFile
                );

                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                startActivityForResult(cameraIntent, IMAGE_REQUEST);
            }

        }

    }

    public void displayImage(View view)
    {
        Intent intent = new Intent(this, DisplayImage.class);
        intent.putExtra("image_path", _currentImagePath);
        startActivity(intent);
    }

    public void goToSecondPage(View view)
    {
        Intent intent = new Intent(this, SecondTestActivity.class);
        startActivity(intent);
    }

    private File getImageFile() throws IOException
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                .format(new Date());

        String fileName = "jpg_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(fileName,".jpg", storageDir);

        _currentImagePath = imageFile.getAbsolutePath();

        return imageFile;
    }

}