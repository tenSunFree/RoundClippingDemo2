package com.home.roundclippingdemo2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class CropPictureResultActivity extends BaseActivity {

    public static void startWithUri(@NonNull Context context, @NonNull Uri uri) {
        Intent intent = new Intent(context, CropPictureResultActivity.class);
        intent.setData(uri);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_picture_result);
        Uri uri = getIntent().getData();
        if (uri != null) {
            try {
                // 顯示裁剪後的圖片, 以圓形顯示
                ImageView imageView = findViewById(R.id.imageView);
                Glide.with(this)
                        .load(uri)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(imageView);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SelectPictureActivity.class);
        startActivity(intent);
        finish();
    }
}
