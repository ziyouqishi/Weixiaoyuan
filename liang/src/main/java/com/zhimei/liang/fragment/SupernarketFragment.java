package com.zhimei.liang.fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zhimei.liang.weixiaoyuan.R;

import java.util.ArrayList;
import java.util.List;


public class SupernarketFragment extends Fragment {
 private  View view;
    private ViewPager viewPager;// 声明一个viewpager对象
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView tabline;
    private FragmentManager myFragmentManager;

    private List<Fragment> list;// 声明一个list集合存放Fragment（数据源）

    private int tabLineLength;// 1/3屏幕宽
    private int currentPage = 0;// 初始化当前页为0（第一页）


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frament_super_market,container,false);
        initTabLine();
        initView();
        return view;
    }

    private void initView() {
        // 实例化对象
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        list = new ArrayList<Fragment>();

        // 设置数据源
        LearnToolsFragment fragment1 = new LearnToolsFragment();
        LiveToolsFragment fragment2 = new LiveToolsFragment();
        JunkDrinkFragment fragment3 = new JunkDrinkFragment();

        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        myFragmentManager = getFragmentManager();

        // 设置适配器
       FragmentPagerAdapter adapter = new FragmentPagerAdapter(myFragmentManager) {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return list.get(arg0);
            }
        };



        // 绑定适配器
        viewPager.setAdapter(adapter);

        // 设置滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 当页面被选择时，先讲3个textview的字体颜色初始化成黑
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.BLACK);

                // 再改变当前选择页（position）对应的textview颜色
                switch (position) {
                    case 0:
                        tv1.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 1:
                        tv2.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 2:
                        tv3.setTextColor(Color.rgb(51, 153, 0));
                        break;
                }

                currentPage = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.i("tuzi", arg0 + "," + arg1 + "," + arg2);

                // 取得该控件的实例
                LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) tabline
                        .getLayoutParams();

                if (currentPage == 0 && arg0 == 0) { // 0->1移动(第一页到第二页)
                    ll.leftMargin = (int) (currentPage * tabLineLength + arg1
                            * tabLineLength);
                } else if (currentPage == 1 && arg0 == 1) { // 1->2移动（第二页到第三页）
                    ll.leftMargin = (int) (currentPage * tabLineLength + arg1
                            * tabLineLength);
                } else if (currentPage == 1 && arg0 == 0) { // 1->0移动（第二页到第一页）
                    ll.leftMargin = (int) (currentPage * tabLineLength - ((1 - arg1) * tabLineLength));
                } else if (currentPage == 2 && arg0 == 1) { // 2->1移动（第三页到第二页）
                    ll.leftMargin = (int) (currentPage * tabLineLength - (1 - arg1)
                            * tabLineLength);
                }

                tabline.setLayoutParams(ll);

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });




    }

    private void initTabLine() {
        // 获取显示屏信息
        WindowManager manager = (WindowManager) view.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display displays = manager.getDefaultDisplay();

        // 得到显示屏宽度
        DisplayMetrics metrics = new DisplayMetrics();
        displays.getMetrics(metrics);
        // 1/3屏幕宽度
        tabLineLength = metrics.widthPixels / 3;
        // 获取控件实例
        tabline = (ImageView)view.findViewById(R.id.tabline);
        // 控件参数
        ViewGroup.LayoutParams lp = tabline.getLayoutParams();
        lp.width = tabLineLength;
        tabline.setLayoutParams(lp);
    }

}
