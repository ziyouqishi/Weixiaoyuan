package com.zhimei.weixiaoyuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.zhimei.utitls.SecondHandGoods;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity  {
    private GridView gridview;
    private ImageView perSetting;
    private ImageView secondMarket;
    private ImageView donate;
    private ImageView shopping;
    private ArrayList<SecondHandGoods> goods_list;
    private Spinner mySpinner1;
    private Spinner mySpinner2;
    private ArrayAdapter spi_adapter_1;
    private ArrayAdapter spi_adapter_2;
    private ArrayAdapter<String> adapter;
    private  Animation animation;
    private  SwipeRefreshLayout swipeLayout;
    ArrayList<HashMap<String, Object>> item_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initreso();
    }
    void initview(){
        perSetting=(ImageView)findViewById(R.id.per_icon);
        secondMarket=(ImageView)findViewById(R.id.sec_icon);
        donate=(ImageView)findViewById(R.id.don_icon);
        shopping=(ImageView)findViewById(R.id.sch_icon);
        gridview=(GridView)findViewById(R.id.gridview);
        mySpinner1=(Spinner)findViewById(R.id.spinner1);
        mySpinner2=(Spinner)findViewById(R.id.spinner2);
        spi_adapter_1 = ArrayAdapter.createFromResource(this, R.array.spingarr, android.R.layout.simple_spinner_item);
        spi_adapter_2 = ArrayAdapter.createFromResource(this, R.array.spingarr2, android.R.layout.simple_spinner_item);

        spi_adapter_1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spi_adapter_2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        mySpinner1.setAdapter(spi_adapter_1);
        mySpinner2.setAdapter(spi_adapter_2);

        perSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonCenterActivity.class);
                startActivity(intent);
            }
        });

        secondMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecoMarketActivity.class);
                startActivity(intent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DonateActivity.class);
                startActivity(intent);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SuperMarketActivity.class);
                startActivity(intent);
            }
        });



        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        swipeLayout.setRefreshing(false);
                        //进行数据更新
                        Toast.makeText(MainActivity.this,"正在进行数据更新",Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });


    }

    /**
     *资源初始化
     */
    void initreso(){
        goods_list=new ArrayList<SecondHandGoods>();
        item_list = new ArrayList<HashMap<String, Object>>();
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.myanim);
        /**
         * 得到商品的图片
         */
       // Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.goods);
        //模拟了六个商品
        int test=R.drawable.goods;
        SecondHandGoods good1=new SecondHandGoods(test,"22.2","墙纸","2天前","23人围观");
        SecondHandGoods good2=new SecondHandGoods(test,"312.2","墙纸2","5天前","23人围观");
        SecondHandGoods good3=new SecondHandGoods(test,"34.2","墙纸3","6天前","13人围观");
        SecondHandGoods good4=new SecondHandGoods(test,"52.2","墙纸4","1天前","0人围观");
        SecondHandGoods good5=new SecondHandGoods(test,"62.2","墙纸5","3天前","13人围观");
        SecondHandGoods good6=new SecondHandGoods(test,"72.2","墙纸6","4天前","73人围观");
        SecondHandGoods good7=new SecondHandGoods(test,"32.2","墙纸7","2天前","3人围观");

        goods_list.add(good1);
        goods_list.add(good2);
        goods_list.add(good3);
        goods_list.add(good4);
        goods_list.add(good5);
        goods_list.add(good6);
        goods_list.add(good7);

        for(int i=0;i<goods_list.size();i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("picture",goods_list.get(i).getPicture());
            map.put("price",goods_list.get(i).getPrice());
            map.put("time",goods_list.get(i).getVar_data());
            map.put("name",goods_list.get(i).getName());
            map.put("attention",goods_list.get(i).getAttention());
            item_list.add(map);
        }

        SimpleAdapter goods_adapter = new SimpleAdapter(this,
                item_list,// 数据源
                R.layout.item,// 显示布局
                new String[] { "picture", "price","time","name","attention" },
                new int[] { R.id.goods_image, R.id.goods_price,R.id.goods_time,R.id.goods_name,R.id.goods_attention });

        gridview.setAdapter(goods_adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(MainActivity.this,"item单击事件",Toast.LENGTH_SHORT).show();
                animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.myanim);
                ImageView iv = (ImageView) view.findViewById(R.id.goods_image);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(MainActivity.this, "item单击事件", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                iv.startAnimation(animation);


            }
        });

    }

}
