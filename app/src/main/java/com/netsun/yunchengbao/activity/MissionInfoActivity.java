package com.netsun.yunchengbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.netsun.yunchengbao.R;

public class MissionInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private Bundle bundle;
    private TextView tvDest,tvOrigin,tvGoods,tvWeight,tvTime;
    private ImageView ivBack;
    private Button buttonTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_info);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        tvDest = (TextView) findViewById(R.id.tv_dest);
        tvOrigin = (TextView) findViewById(R.id.tv_origin);
        tvGoods = (TextView) findViewById(R.id.tv_goods);
        tvWeight = (TextView) findViewById(R.id.tv_weight);
        tvTime = (TextView) findViewById(R.id.tv_time);
        ivBack = (ImageView)findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        buttonTodo = (Button)findViewById(R.id.button_todo);
        buttonTodo.setOnClickListener(this);
        bundle = getIntent().getExtras();
        tvDest.setText(bundle.getString("dest"));
        tvGoods.setText(bundle.getString("goods"));
        tvOrigin.setText(bundle.getString("origin"));
        tvTime.setText(bundle.getString("time"));
        tvWeight.setText(String.valueOf(bundle.getFloat("weight")));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Instrumentation不能在主线程使用，故开一个线程
//                        Instrumentation instrumentation = new Instrumentation();
//                        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
//                    }
//                }).start();
            break;
            case R.id.button_todo:
                Intent intent = new Intent();
                intent.setClass(MissionInfoActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }
}
