package com.zhimei.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zhimei.utitls.ShopGoods;
import com.zhimei.weixiaoyuan.R;

import java.util.ArrayList;
import java.util.HashMap;


public class LiveToolsFragment extends Fragment {
    private ArrayList<ShopGoods> al_goods;
    private ArrayList<HashMap<String,Object>> al_map;
    private ListView lv;
    private  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view=inflater.inflate(R.layout.fragment_live_tools, container, false);
        initView();
        return view;
    }

    void initView(){
        al_goods=new ArrayList<>();
        al_map=new ArrayList<>();
        lv=(ListView)view.findViewById(R.id.lv_live);

        int test=R.drawable.goods;

        for(int i=0;i<15;i++){
            ShopGoods shopGoods=new ShopGoods(test,"营业中","已售34份","¥ 43.8","壁纸");
            al_goods.add(shopGoods);
        }

        for(int j=0;j<al_goods.size();j++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("picture",al_goods.get(j).getPicture());
            map.put("name",al_goods.get(j).getName());
            map.put("state",al_goods.get(j).getState());
            map.put("price",al_goods.get(j).getPrice());
            map.put("sell",al_goods.get(j).getSell());
            al_map.add(map);
        }


        SimpleAdapter goods_adapter = new SimpleAdapter(view.getContext(),
                al_map,// 数据源
                R.layout.shopitem,// 显示布局
                new String[] { "picture", "name","state","price","sell" },
                new int[] { R.id.shop_good_picture, R.id.shop_good_name,R.id.shop_good_state,
                        R.id.shop_good_price,R.id.shop_good_sellnum }
        );

        lv.setAdapter(goods_adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
