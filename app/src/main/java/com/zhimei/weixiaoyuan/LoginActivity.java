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
import android.widget.Toast;


public class LoginActivity extends Activity {
    private ImageView back;
    private ImageView backhome;
    private TextView register;
    private Button login;
    private ImageView perSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
    }

    void initview(){
        back=(ImageView)findViewById(R.id.log_back);
        backhome=(ImageView)findViewById(R.id.log_index);
        register=(TextView)findViewById(R.id.log_tv_rigister);
        login=(Button)findViewById(R.id.log_ensure_bu);
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
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(LoginActivity.this,"登录验证",Toast.LENGTH_SHORT).show();
            }
        });

        perSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(LoginActivity.this,PersonCenterActivity.class);
                startActivity(intent);
            }
        });
    }

}
