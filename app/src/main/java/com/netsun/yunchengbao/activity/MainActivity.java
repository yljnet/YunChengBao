package com.netsun.yunchengbao.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.netsun.yunchengbao.R;
import com.netsun.yunchengbao.service.LocationService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    public static final String TAG = "MainActivity";
    private BottomNavigationBar bottomNavigationBar;
    private ArrayList<android.app.Fragment> fragments;
    private int lastSelectedPosition = 0;
    private MissionFragment missionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(MainActivity.this, LocationService.class);
                startService(intent);
                break;
            case R.id.remove_item:
                break;
            case R.id.exit:
                finish();//销毁活动
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    //保存数据，待活动重新生成时取回
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
