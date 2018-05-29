package org.woaker.funkeyboardtheme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

public class ThemeActivity extends AppCompatActivity {

    private RecyclerView theme_rl;
    private RelativeLayout setting_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        initView();

        initData();
    }

    private void initData() {
        ThemeAdapter themeAdapter = new ThemeAdapter(this);
        theme_rl.setAdapter(themeAdapter);

        themeAdapter.setThemeItemOnClickListener(new ThemeItemOnClickListener() {
            @Override
            public void itemOnClick(int position) {
                Utils.setThemeType(ThemeActivity.this, position);

                boolean isOpen = Utils.getBoolean(ThemeActivity.this, "isOpen");
                if (isOpen) {
                    startActivity(new Intent(ThemeActivity.this, SettingActivity.class));
                } else {
                    Intent intent = new Intent().putExtra("themeas", "aa");
                    setResult(54, intent);
                    finish();
                }
            }
        });

        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isOpen = Utils.getBoolean(ThemeActivity.this, "isOpen");
                if (isOpen) {
                    startActivity(new Intent(ThemeActivity.this, SettingActivity.class));
                } else {
                    finish();
                }
            }
        });
    }

    private void initView() {
        setting_back = findViewById(R.id.setting_back);
        theme_rl = findViewById(R.id.theme_rl);
        theme_rl.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    public void onBackPressed() {
        boolean isOpen = Utils.getBoolean(this, "isOpen");
        if (isOpen) {
            startActivity(new Intent(ThemeActivity.this, SettingActivity.class));
        } else {
            super.onBackPressed();
        }
    }
}
