package com.netsun.yunchengbao.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.netsun.yunchengbao.R;

public class MissionInfoActivity extends Activity implements View.OnClickListener{
    private Bundle bundle;
    private TextView tvDest,tvOrigin,tvGoods,tvWeight,tvTime;
    private ImageView ivBack;
    private Button buttonTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mission_info);
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Instrumentation不能在主线程使用，故开一个线程
                        Instrumentation instrumentation = new Instrumentation();
                        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                    }
                }).start();
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
