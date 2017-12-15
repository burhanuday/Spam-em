package com.burhanuday.textspammer;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tutorial extends AppCompatActivity {
    Button enable,conti,opentut;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        enable = (Button)findViewById(R.id.enable);
        title = (TextView)findViewById(R.id.tvtitle2);
        opentut = (Button)findViewById(R.id.but_opentutpage);

        opentut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("https://github.com/burhanuday/Spam-em#how-to-use"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Typeface nexabold= Typeface.createFromAsset(getAssets(),"fonts/Nexa Bold.otf");

        title.setTypeface(nexabold);
        title.setTypeface(title.getTypeface(), Typeface.BOLD);

        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                Toast.makeText(Tutorial.this, "Enable Spam 'em and go back", Toast.LENGTH_SHORT).show();
            }
        });

        conti = (Button)findViewById(R.id.bconti);
        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent op = new Intent(getBaseContext(),MainActivity.class);
                startActivity(op);
                finish();
            }
        });
    }
}
