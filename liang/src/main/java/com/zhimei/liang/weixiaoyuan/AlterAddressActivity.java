package com.zhimei.liang.weixiaoyuan;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhimei.liang.weixiaoyuan.R;

public class AlterAddressActivity extends Activity {
    private ImageButton ensure;
    private RelativeLayout layout;
    private ImageButton monify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_address);
        initViews();
    }

    void initViews(){
        ensure=(ImageButton)findViewById(R.id.xiugaidizhi);
        layout=(RelativeLayout)findViewById(R.id.alter_three);
        monify=(ImageButton)findViewById(R.id.monifyaddress_ensure);

        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.VISIBLE);
            }
        });

        monify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AlterAddressActivity.this,"上传修改后的地址",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

