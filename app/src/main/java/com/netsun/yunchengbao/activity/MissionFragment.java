package com.netsun.yunchengbao.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.netsun.yunchengbao.R;
import com.netsun.yunchengbao.util.Freight;
import com.netsun.yunchengbao.util.FreightAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MissionFragment extends Fragment implements AdapterView.OnItemClickListener {
    private RecyclerView lvMission;

    private ArrayList<Freight> freights;
    private FreightAdapter adapter;
    private View view;

    public static MissionFragment newInstance(String paraml) {

        Bundle args = new Bundle();
        args.putString("args1", paraml);
        MissionFragment fragment = new MissionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void initData() {
        Freight freightFirst = new Freight();
        freightFirst.setId(1);
        freightFirst.setOrigin("上海");
        freightFirst.setDestination("杭州");
        freightFirst.setGoods("甲苯");
        freightFirst.setTime("2010-10-10");
        freightFirst.setWeight(1.5f);
        freights.add(freightFirst);

        Freight freightSec = new Freight();
        freightSec.setId(2);
        freightSec.setOrigin("武汉");
        freightSec.setDestination("广州");
        freightSec.setGoods("甲苯");
        freightSec.setTime("2010-10-10");
        freightSec.setWeight(1.5f);
        freights.add(freightSec);

        Freight freightThird = new Freight();
        freightThird.setId(1);
        freightThird.setOrigin("南昌");
        freightThird.setDestination("大连");
        freightThird.setGoods("甲苯");
        freightThird.setTime("2010-10-10");
        freightThird.setWeight(1.5f);
        freights.add(freightThird);

        Freight freightForth = new Freight();
        freightForth.setId(1);
        freightForth.setOrigin("长沙");
        freightForth.setDestination("北京");
        freightForth.setGoods("甲苯");
        freightForth.setTime("2010-10-10");
        freightForth.setWeight(1.5f);
        freights.add(freightForth);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvMission = (RecyclerView)getActivity().findViewById(R.id.freight_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lvMission.setLayoutManager(layoutManager);
        freights = new ArrayList<Freight>();
        initData();
        adapter = new FreightAdapter(freights);
        lvMission.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mission_layout, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
