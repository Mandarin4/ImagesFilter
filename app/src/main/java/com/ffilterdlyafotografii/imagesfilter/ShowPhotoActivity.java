package com.ffilterdlyafotografii.imagesfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ShowPhotoActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn_Edit_Photo;
    String path = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        imageView = (ImageView) findViewById(R.id.imageView);
        btn_Edit_Photo = (Button) findViewById(R.id.btn_Edit_Photo);

        path = getIntent().getExtras().getString("path");
        File imgFile = new File(path);

        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(RotateBitmap(myBitmap,90));
        }

        btn_Edit_Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShowPhotoActivity.this, EditPhotoActivity.class);
                intent.putExtra("path", path);
                startActivity(intent);
            }
        });
    }

    public static Bitmap RotateBitmap(Bitmap sourse, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(sourse, 0,0,sourse.getWidth(),sourse.getHeight(),matrix, true);
    }
}