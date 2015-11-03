package com.zhimei.liang.weixiaoyuan;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zhimei.liang.customview.RoundImageView;
import com.zhimei.liang.utitls.TradeRecord;
import com.zhimei.liang.weixiaoyuan.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeRecordActivity extends Activity {
    private ArrayList<TradeRecord> trList;
    private ArrayList<HashMap<String,Object>> mapArrayList;
    private TextView name,price,time;
    private RoundImageView picture;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_record);
        initViews();
    }
    void initViews(){
        trList=new ArrayList<TradeRecord>();
        mapArrayList=new ArrayList<>();
        name=(TextView)findViewById(R.id.trade_good_name);
        price=(TextView)findViewById(R.id.trade_good_price);
        time=(TextView)findViewById(R.id.trade_good_time);
        picture=(RoundImageView)findViewById(R.id.trade_good_picture);
        lv=(ListView)findViewById(R.id.record_listview);


        Drawable drawable=this.getResources().getDrawable(R.mipmap.test1);

        for(int i=0;i<12;i++){
            TradeRecord tr=new TradeRecord("海贼王 航海王","2015.2.15",drawable,"¥ 45.3","买进");
            trList.add(tr);
        }

        for(int j=0;j<trList.size();j++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("picture",trList.get(j).getPicture());
            map.put("name",trList.get(j).getGood_name());
            map.put("price",trList.get(j).getPrice());
            map.put("time",trList.get(j).getTime());
            map.put("ways",trList.get(j).getWays());
           mapArrayList.add(map);
        }

        SimpleAdapter simpleAdapter=new SimpleAdapter(this,mapArrayList,R.layout.trade_record_item,
                new String[] { "picture", "name","price","time","ways" },
                new int[]{R.id.trade_good_picture,R.id.trade_good_name,R.id.trade_good_price,R.id.trade_good_time,
                R.id.trade_good_way});

        lv.setAdapter(simpleAdapter);

        /**
         * 将Drawable转化为ImageView在SimpleAdapter中进行显示，否则，无法进行正常显示。
         */
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {

                if(view instanceof RoundImageView && data instanceof Drawable){
                    RoundImageView iv=(RoundImageView)view;
                   // iv.setImageBitmap((Bitmap) attentionList);
                    iv.setImageDrawable((Drawable)data);
                    return true;
                }else{
                    return false;
                }
            }
        });



    }

}
