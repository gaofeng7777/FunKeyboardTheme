package org.woaker.funkeyboardtheme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.jph.takephoto.app.TakePhotoFragmentActivity;
import com.jph.takephoto.model.CropOptions;

import java.io.File;

public class SettingActivity extends TakePhotoFragmentActivity {

    private ImageButton theme_btn;
    private ImageButton photo_btn;
    private RelativeLayout setting_rl;
    private View theme_rl;
    private View success_rl;
    private View success_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        setting_rl = findViewById(R.id.setting_rl);
        theme_rl = findViewById(R.id.setting_theme_rl);
        success_rl = findViewById(R.id.success_rl);
        success_btn = findViewById(R.id.successful_btn);
        theme_btn = findViewById(R.id.setting_theme_btn);
        photo_btn = findViewById(R.id.setting_photo_btn);


        theme_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(SettingActivity.this, ThemeActivity.class), 54);
            }
        });


        photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sdCardEnable = SDCardUtils.isSDCardEnableByEnvironment();
                if (sdCardEnable) {
                    Uri imageUri;

                    if (Build.VERSION.SDK_INT >= 24) {

                        File file = new File(Environment.getExternalStorageDirectory(), "/fun/" + "a.jpg");
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }

                        imageUri = FileProvider.getUriForFile(SettingActivity.this, "org.woaker.funkeyboardtheme.fileprovider", file);//通过FileProvider创建一个content类型的Uri

                    } else {
                        imageUri = Uri.fromFile(new File(getExternalCacheDir(), "a.jpg"));
                    }

                    CropOptions cropOptions = new CropOptions.Builder().setAspectY(800).setAspectY(450).setWithOwnCrop(true).create();
                    SettingActivity.this.getTakePhoto().onPickFromDocumentsWithCrop(imageUri, cropOptions);

                    Utils.setThemeType(SettingActivity.this, 100);
                }

            }
        });

        success_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
                mHomeIntent.addCategory(Intent.CATEGORY_HOME);
                mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivity(mHomeIntent);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 54 && data != null) {

            theme_rl.setVisibility(View.GONE);
            success_rl.setVisibility(View.VISIBLE);

        } else {

            if (data != null) {

                theme_rl.setVisibility(View.GONE);
                success_rl.setVisibility(View.VISIBLE);

            } else {
                Snackbar.make(setting_rl, "Setup failed", Snackbar.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onBackPressed() {
        boolean isOpen = Utils.getBoolean(this, "isOpen");
        if (isOpen) {
            Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
            mHomeIntent.addCategory(Intent.CATEGORY_HOME);
            mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(mHomeIntent);
        } else {
            super.onBackPressed();
        }
    }

}
