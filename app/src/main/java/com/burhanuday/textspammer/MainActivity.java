package com.burhanuday.textspammer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button tut,more;
    ImageButton snap,insta;
    TextView title,subheading;
    EditText message,number;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefs = getSharedPreferences("com.burhanuday.textspammer", MODE_PRIVATE);
        tut = (Button)findViewById(R.id.buttontut);
        title = (TextView)findViewById(R.id.tvtitle);
        subheading = (TextView)findViewById(R.id.tvsubheading);
        message = (EditText)findViewById(R.id.input);
        number = (EditText)findViewById(R.id.number);

        Toast.makeText(MainActivity.this,"Press 'How To Use' to open the tutorial",Toast.LENGTH_LONG).show();
        message.setText(sharedPrefs.getString("messagestring","Check out the tutorial page before using Spam'em"));
        number.setText(Integer.toString(sharedPrefs.getInt("times",30)));

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                SharedPreferences sharedPrefs = getSharedPreferences("com.burhanuday.textspammer", MODE_PRIVATE);
                sharedPrefs.edit().putString("messagestring",message.getText().toString()).commit();
            }
        });

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(number.length() == 0){
                    sharedPrefs.edit().putInt("times",30).commit();
                }else{
                    SharedPreferences sharedPrefs = getSharedPreferences("com.burhanuday.textspammer", MODE_PRIVATE);
                    sharedPrefs.edit().putInt("times",Integer.parseInt(number.getText().toString())).commit();
                }
            }
        });

        Typeface latoreg= Typeface.createFromAsset(getAssets(),"fonts/Lato-Regular.ttf");
        Typeface nexabold= Typeface.createFromAsset(getAssets(),"fonts/Nexa Bold.otf");
        title.setTypeface(nexabold);
        subheading.setTypeface(latoreg);
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        subheading.setTypeface(subheading.getTypeface(), Typeface.ITALIC);

        tut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opentut = new Intent(getBaseContext(),Tutorial.class);
                startActivity(opentut);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPrefs.getBoolean("firstrun", true)) {
            Intent opentut = new Intent(getBaseContext(),Tutorial.class);
            startActivity(opentut);
            sharedPrefs.edit().putBoolean("firstrun", false).commit();
            finish();
        }
    }

}
