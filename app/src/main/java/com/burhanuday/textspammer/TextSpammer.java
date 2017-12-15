package com.burhanuday.textspammer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class TextSpammer extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    private KeyboardView kv;
    private Keyboard spammer;
    Handler handler;
    Runnable runnable;

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
        setInputView(onCreateInputView());
    }

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard,null);
        spammer = new Keyboard(this, R.xml.spammer);
        kv.setKeyboard(spammer);
        kv.setOnKeyboardActionListener(this);
        kv.setPreviewEnabled(false);
        return kv;
    }

    @Override
    public void onPress(int primaryCode) {}

    @Override
    public void onRelease(int primaryCode) {}

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        switch (primaryCode){
            case -500:
                //Open App to change message
                Intent openset = new Intent(getApplicationContext(),MainActivity.class);
                openset.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openset);
                break;
            case -101:
                final InputConnection ic = getCurrentInputConnection();
                //get text to be spammed and how many times to send it
                SharedPreferences sharedPrefs = getSharedPreferences("com.burhanuday.textspammer", MODE_PRIVATE);
                final int number = sharedPrefs.getInt("times",30);
                final String message = sharedPrefs.getString("messagestring", "Press 'How To Use' to open the tutorial");
                final int[] tempnumber = {number};
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run(){
                        ic.commitText(message, 1);
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                        if(tempnumber[0] >1){
                            tempnumber[0]--;
                            handler.postDelayed(runnable, 1 * 100);
                        }
                    }};
                handler.postDelayed(runnable, 2 * 1000);

                break;
            case -102:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showInputMethodPicker();
                break;
        }
    }

    @Override
    public void onText(CharSequence text) {}

    @Override
    public void swipeLeft() {}

    @Override
    public void swipeRight() {}

    @Override
    public void swipeDown() {}

    @Override
    public void swipeUp() {}
}
