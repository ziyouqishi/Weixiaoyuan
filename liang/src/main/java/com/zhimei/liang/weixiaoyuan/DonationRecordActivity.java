package com.zhimei.liang.weixiaoyuan;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zhimei.liang.customview.RoundImageView;
import com.zhimei.liang.utitls.DonationRecord;
import com.zhimei.liang.utitls.TradeRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class DonationRecordActivity extends Activity {
    private ArrayList<DonationRecord> trList;
    private ArrayList<HashMap<String,Object>> mapArrayList;
    private TextView name,organization,time;
    private RoundImageView picture;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_record);
        initViews();
    }

    void initViews(){
        trList=new ArrayList<>();
        mapArrayList=new ArrayList<>();
        name=(TextView)findViewById(R.id.donation_good_name);
        organization=(TextView)findViewById(R.id.receive_organization);
        time=(TextView)findViewById(R.id.donation_good_time);
        picture=(RoundImageView)findViewById(R.id.donation_good_picture);
        lv=(ListView)findViewById(R.id.donation_record_listview);

        Drawable drawable=this.getResources().getDrawable(R.mipmap.disini);

        for(int i=0;i<12;i++){
           DonationRecord dr=new DonationRecord("迪斯尼公主精选集","支教团队","2015.5.10",drawable);
            trList.add(dr);
        }


        for(int j=0;j<trList.size();j++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("picture",trList.get(j).getPicture());
            map.put("name",trList.get(j).getName());
            map.put("organization",trList.get(j).getReceiveOrganization());
            map.put("time",trList.get(j).getTime());
            mapArrayList.add(map);
        }


        SimpleAdapter simpleAdapter=new SimpleAdapter(this,mapArrayList,R.layout.donation_record_item,
                new String[] { "picture", "name","organization","time" },
                new int[]{R.id.donation_good_picture,R.id.donation_good_name,R.id.receive_organization,R.id.donation_good_time
                       });


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
