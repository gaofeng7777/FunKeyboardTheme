package org.woaker.funkeyboardtheme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton step_a;
    private ImageButton step_b;
    private int tag = 0;
    boolean isShow = false;
    private TextView main_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initInputMothod();

    }

    private void initView() {
        step_a = findViewById(R.id.main_btn_a);
        step_b = findViewById(R.id.main_btn_b);
        main_tv = findViewById(R.id.main_text);
        step_a.setOnClickListener(this);
        step_b.setOnClickListener(this);
    }

    private void initInputMothod() {
        //获得已经启用的输入法列表
        getMothod();
        //获得默认的输入法
        getInputMothod();
    }


    private void getMothod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> methodList = imm.getEnabledInputMethodList();

        int tag = 0;
        for (InputMethodInfo mi : methodList) {

            if (mi.loadLabel(getPackageManager()).toString().equals("Fun Keyboard Theme")) {
                tag = 1;
                step_a.setBackgroundResource(R.drawable.icon_step1_finish);
                main_tv.setText("");
//                step_a.setText("第一步：输入法已启用");
                step_a.setEnabled(false);
                this.tag = 1;


            } else if (tag == 0) {

                step_a.setBackgroundResource(R.drawable.icon_step1);
//                step_a.setText("第一步：去启用输入法");
                step_b.setBackgroundResource(R.drawable.icon_step2);

                this.tag = 0;
            }
        }
    }

    private void getInputMothod() {
        String defaultIme = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        if (defaultIme.equals("org.woaker.funkeyboardtheme/.KeyboardService") && tag == 1) {
            step_b.setBackgroundResource(R.drawable.icon_step2_finish);
//            step_b.setText("第二步：设置成功");
            step_b.setEnabled(false);

            startActivity(new Intent(MainActivity.this, SettingActivity.class));

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_a:
                Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivityForResult(intent, 1);
                break;
            case R.id.main_btn_b:
                if (tag == 0) {
                    Toast.makeText(this, R.string.step_a_text, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.main_choose, Toast.LENGTH_SHORT).show();
                    ((InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).showInputMethodPicker();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            while (!isShow) {
                                String defaultIme = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
                                if (defaultIme.equals("org.woaker.funkeyboardtheme/.KeyboardService")) {
                                    isShow = true;
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (isShow) {
                                        step_b.setBackgroundResource(R.drawable.icon_step2_finish);
//                                        step_b.setText("第二步：设置成功");
                                        step_b.setEnabled(false);
                                        startActivity(new Intent(MainActivity.this, SettingActivity.class));

                                    }
                                }
                            });


                        }
                    }).start();


                }
                break;
            default:
                break;
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                initInputMothod();
                break;
            default:
                break;
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
