package com.zhimei.weixiaoyuan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class SecoMarketActivity extends Activity {
    private ImageView back;
    private ImageView backhome;
    private ImageView perSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seco_market);
        initview();
    }

    void initview(){
        back=(ImageView)findViewById(R.id.sec_back);
        backhome=(ImageView)findViewById(R.id.sec_index);
        perSettings=(ImageView)findViewById(R.id.per_icon);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(SecoMarketActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        perSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(SecoMarketActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
