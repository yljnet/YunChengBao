package com.netsun.yunchengbao.model;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/7.
 */

public class BaseListFragment extends ListFragment {
    public Context context;
    public Activity activity;
    public HashMap<Integer, View> map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        context = getActivity();
        activity = getActivity();
    }
}
