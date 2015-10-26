package com.zhimei.liang.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zhimei.liang.weixiaoyuan.R;

import com.zhimei.liang.weixiaoyuan.LoginActivity;

public class IndividulFragment extends Fragment {
    private  View view;
    private Button login;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_person_center,container,false);
        initview();
        return view;
    }
    void initview(){
        login=(Button)view.findViewById(R.id.per_bt_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}