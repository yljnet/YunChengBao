package com.netsun.yunchengbao.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.netsun.yunchengbao.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private ArrayList<android.app.Fragment> fragments;
    private int lastSelectedPosition = 0;
    private MissionFragment missionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.item_normal);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.mission, "派车单").setActiveColorResource(R.color.tab_focuse))
                .addItem(new BottomNavigationItem(R.drawable.return_, "返程").setActiveColorResource(R.color.tab_focuse))
                .addItem(new BottomNavigationItem(R.drawable.my_info, "我的").setActiveColorResource(R.color.tab_focuse))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        fragments = getFragments();
        setDefaultFragment();
    }

    private ArrayList<android.app.Fragment> getFragments() {
        ArrayList<android.app.Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(MissionFragment.newInstance("派车单"));
        return fragments;
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//开启fragment事物
        missionFragment = MissionFragment.newInstance("派车单");
        transaction.replace(R.id.layoutFrame, missionFragment);
        transaction.commit();//提交事物
    }

    @Override
    public void onTabSelected(int position) {
        Log.d("YunChengBao", "onTabSelected: call with: position=[" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事物
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (missionFragment == null) {
                    missionFragment = MissionFragment.newInstance("派车单");

                }
                transaction.replace(R.id.layoutFrame, missionFragment);

                break;
            default:
                break;
        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {
        Log.d("YunChengBao", "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }
}
