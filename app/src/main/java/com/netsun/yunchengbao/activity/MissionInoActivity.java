package com.netsun.yunchengbao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.netsun.yunchengbao.R;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MissionInoActivity extends AppCompatActivity {
    private Bundle bundle;
    private TextView tvOrigin;
    private TextView tvDest;
    private TextView tvGoods;
    private TextView tvWeight;
    private TextView tvTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mission_info);
        bundle = getIntent().getExtras();
        tvDest = (TextView) findViewById(R.id.tv_dest);
        tvOrigin = (TextView) findViewById(R.id.tv_origin);
        tvGoods = (TextView) findViewById(R.id.tv_goods);
        tvWeight = (TextView) findViewById(R.id.tv_weight);
        tvTime = (TextView) findViewById(R.id.tv_time);

    }
}
