package com.zhimei.utitls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhimei.weixiaoyuan.R;

import java.util.ArrayList;

/**
 * Created by 张佳亮 on 2015/10/13.
 */
public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private View view;
    private Button buy;
    private ArrayList<ShopGoods> al_goods;
    private int num;

    public MyAdapter(Context context,ArrayList al) {
        super();
        al_goods=al;
        this.mInflater = LayoutInflater.from(context);
       view=LayoutInflater.from(context).inflate(R.layout.fragment_live_tools, null);
        buy=(Button)view.findViewById(R.id.already_choosed);
    }

    @Override
    public int getCount() {
        return al_goods.size();
    }

    @Override
    public Object getItem(int position) {
        return al_goods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.shopitem,null);
            holder = new ViewHolder();
            /**得到各个控件的对象*/
            holder.name = (TextView) convertView.findViewById(R.id.shop_good_name);
            holder.price = (TextView) convertView.findViewById(R.id.shop_good_price);
            holder.state = (TextView) convertView.findViewById(R.id.shop_good_state);
            holder.sell = (TextView) convertView.findViewById(R.id.shop_good_sellnum);
            holder.picture=(ImageView) convertView.findViewById(R.id.shop_good_picture);
            holder.add=(ImageView) convertView.findViewById(R.id.shop_num_jia);
            holder.decrease=(ImageView) convertView.findViewById(R.id.shop_num_jian);
            holder.num=(TextView) convertView.findViewById(R.id.shop_num);
            convertView.setTag(holder);//绑定ViewHolder对象
        }
        else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        holder.name.setText(al_goods.get(position).getName());
        holder.price.setText(al_goods.get(position).getPrice());
        holder.sell.setText(al_goods.get(position).getSell());
        holder.state.setText(al_goods.get(position).getState());
        holder.picture.setImageResource(al_goods.get(position).getPicture());
        holder.num.setText(al_goods.get(position).getBuynum());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy.setVisibility(View.VISIBLE);
               ++ num;
             //  holder.add
                Toast.makeText(MyApplication.getContext(),"规划加把劲",Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    class ViewHolder{
        public TextView name;
        public TextView price;
        public TextView state;
        public TextView sell;
        public ImageView picture;
        public ImageView add;
        public ImageView decrease;
        public TextView num;

    }

}
