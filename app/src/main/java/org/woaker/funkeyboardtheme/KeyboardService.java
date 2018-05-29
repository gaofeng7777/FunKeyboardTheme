package org.woaker.funkeyboardtheme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class KeyboardService extends InputMethodService implements View.OnClickListener, KeyboardView.OnKeyboardActionListener, ViewPager.OnPageChangeListener {
    private RelativeLayout service_rl;
    private ViewPager service_vp;
    private KeyboardView keyboardView;
    private RadioButton vp_keyboardView;
    private RadioButton vp_btn_a;
    private RadioButton vp_btn_b;
    private RadioButton vp_btn_c;
    private RadioButton vp_btn_d;
    private RadioButton vp_btn_hide;
    private Keyboard keyboard_asdf;
    private Keyboard keyboard_number;
    private Keyboard keyboard_sign;
    public boolean isASDF = false;// qwer 是否大写

    @Override
    public View onCreateInputView() {

        return initView();
    }

    private View initView() {

        int themeType = Utils.getThemeType(this);

        switch (themeType) {
            case 0:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_1_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_1, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_1_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_1_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_1_sign);
                break;

            case 1:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_2_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_2, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_2_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_2_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_2_sign);
                break;

            case 2:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_3_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_3, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_3_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_3_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_3_sign);
                break;

            case 3:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_4_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_4, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_4_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_4_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_4_sign);
                break;

            case 4:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_1_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_1, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_1_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_1_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_1_sign);

                keyboardView.setBackground(getResources().getDrawable(R.drawable.pic_1));
                break;

            case 5:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_1_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_1, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_1_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_1_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_1_sign);

                keyboardView.setBackground(getResources().getDrawable(R.drawable.pic_2));
                break;

            case 6:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_1_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_1, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_1_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_1_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_1_sign);

                keyboardView.setBackground(getResources().getDrawable(R.drawable.pic_3));
                break;

            case 7:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_1_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_1, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_1_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_1_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_1_sign);

                keyboardView.setBackground(getResources().getDrawable(R.drawable.pic_4));
                break;

            case 100:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_1_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_1, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_1_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_1_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_1_sign);

                Uri imageUri;

                if (Build.VERSION.SDK_INT >= 24) {

                    File file = new File(Environment.getExternalStorageDirectory(), "/fun/" + "a.jpg");
                    imageUri = FileProvider.getUriForFile(this, "org.woaker.funkeyboardtheme.fileprovider", file);//通过FileProvider创建一个content类型的Uri

                } else {

                    imageUri = Uri.fromFile(new File(getExternalCacheDir(), "a.jpg"));
                }

                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                    keyboardView.setBackground(bitmapDrawable);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }

                break;

            default:
                // 装载keyboard.xml文件
                service_rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.keyboard_1_vp, null);
                keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_1, null);
                //初始化数字键盘和字母键盘
                keyboard_asdf = new Keyboard(getApplicationContext(), R.xml.theme_1_asdf);
                keyboard_number = new Keyboard(getApplicationContext(), R.xml.theme_1_number);
                keyboard_sign = new Keyboard(getApplicationContext(), R.xml.theme_1_sign);
                break;
        }


        service_vp = service_rl.findViewById(R.id.vp);
        vp_keyboardView = service_rl.findViewById(R.id.vp_keyboard);
        vp_btn_a = service_rl.findViewById(R.id.vp_btn_a);
        vp_btn_b = service_rl.findViewById(R.id.vp_btn_b);
        vp_btn_c = service_rl.findViewById(R.id.vp_btn_c);
        vp_btn_d = service_rl.findViewById(R.id.vp_btn_d);
        vp_btn_hide = service_rl.findViewById(R.id.vp_btn_hide);

        service_vp.addOnPageChangeListener(this);
        vp_keyboardView.setOnClickListener(this);
        vp_btn_a.setOnClickListener(this);
        vp_btn_b.setOnClickListener(this);
        vp_btn_c.setOnClickListener(this);
        vp_btn_d.setOnClickListener(this);
        vp_btn_hide.setOnClickListener(this);

        //获取4个view
        View view1 = getLayoutInflater().inflate(R.layout.facea, null);
        View view3 = getLayoutInflater().inflate(R.layout.facec, null);
        View view4 = getLayoutInflater().inflate(R.layout.faced, null);
        View view5 = getLayoutInflater().inflate(R.layout.facaee, null);

        //第1页表情
        Utils.getA(view1, this);
        //第2页表情
        Utils.getB(view3, this);
        //第3页表情
        Utils.getC(view4, this);
        //第4页表情
        Utils.getD(view5, this);

        List<View> viewList = new ArrayList<>();
        //将4个View加入到集合
        viewList.add(keyboardView);
        viewList.add(view3);
        viewList.add(view5);
        viewList.add(view1);
        viewList.add(view4);
        //实例化适配器
        MyAdapter adapter = new MyAdapter(viewList);

        //设置适配器
        service_vp.setAdapter(adapter);

        keyboardView.setKeyboard(keyboard_asdf);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setEnabled(true);
        keyboardView.setOnKeyboardActionListener(this);

        // 返回View对象
        return service_rl;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.vp_keyboard || v.getId() == R.id.vp_btn_a || v.getId() == R.id.vp_btn_b || v.getId() == R.id.vp_btn_c || v.getId() == R.id.vp_btn_d || v.getId() == R.id.vp_btn_hide) {

            switch (v.getId()) {
                case R.id.vp_keyboard:
                    service_vp.setCurrentItem(0, true);
                    break;
                case R.id.vp_btn_a:
                    service_vp.setCurrentItem(1, true);
                    break;
                case R.id.vp_btn_b:
                    service_vp.setCurrentItem(2, true);
                    break;
                case R.id.vp_btn_c:
                    service_vp.setCurrentItem(3, true);
                    break;
                case R.id.vp_btn_d:
                    service_vp.setCurrentItem(4, true);
                    break;
                case R.id.vp_btn_hide:
                    hideWindow();
                    break;
                default:
                    break;

            }

        } else {
            if (v.getId() == R.id.delete_a || v.getId() == R.id.delete_b || v.getId() == R.id.delete_c || v.getId() == R.id.delete_d) {

                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.deleteSurroundingText(2, 0);

            } else {
                Button button = (Button) v;
                InputConnection inputConnection = getCurrentInputConnection();
                inputConnection.commitText(button.getText(), 1);
            }
        }

    }


    @Override
    public void swipeUp() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        Keyboard keyboard = keyboard_asdf;

        InputConnection inputConnection = getCurrentInputConnection();

        if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成

            hideWindow();

        } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 删除

            inputConnection.deleteSurroundingText(1, 0);

        } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {// 大小写切换
            changeBigOrSmall(primaryCode);
            keyboardView.setKeyboard(keyboard);

        } else if (primaryCode == 1731) { // 打开符号界面

            keyboardView.setKeyboard(keyboard_sign);

        } else if (primaryCode == 1732) { // 打开数字界面

            keyboardView.setKeyboard(keyboard_number);

        } else if (primaryCode == 1733) { // 打开键盘

            keyboardView.setKeyboard(keyboard);

        } else if (primaryCode == 1734) { // 光标前移

            inputConnection.commitText("", -1);

        } else if (primaryCode == 1735) { // €符号

            inputConnection.commitText("€", 1);
        } else {
            inputConnection.commitText(Character.toString((char) primaryCode), 1);
        }
    }


    /**
     * 键盘大小写切换
     */
    private void changeBigOrSmall(int primaryCode) {
        if (primaryCode == -1) {
            List<Keyboard.Key> keylist = keyboard_asdf.getKeys();
            if (isASDF) {//大写切换小写
                isASDF = false;
                for (Keyboard.Key key : keylist) {
                    if (key.label != null && isqwer(key.label.toString())) {
                        key.label = key.label.toString().toLowerCase();
                        key.codes[0] = key.codes[0] + 32;
                    }
                }
            } else {//小写切换大写
                isASDF = true;
                for (Keyboard.Key key : keylist) {
                    if (key.label != null && isqwer(key.label.toString())) {
                        key.label = key.label.toString().toUpperCase();
                        key.codes[0] = key.codes[0] - 32;
                    }
                }
            }
        }
    }

    private boolean isqwer(String str) {
        String wordstr = "abcdefghijklmnopqrstuvwxyz";
        if (wordstr.indexOf(str.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
