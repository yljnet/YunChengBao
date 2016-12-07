package com.netsun.yunchengbao.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.netsun.yunchengbao.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class FreightAdapter extends BaseAdapter {
    private Context mContext;
    private List<Freight> freights;
    private int selectedPosition = -1;

    public FreightAdapter(Context context, List<Freight> freightList) {
        mContext = context;
        freights = freightList;
    }
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
    @Override
    public int getCount() {
        return freights.size();
    }

    @Override
    public Object getItem(int position) {
        return freights.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.data_item, null);
            holder = new ViewHolder();
            holder.addrs = (TextView) convertView.findViewById(R.id.tv_addrs);
            holder.time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (selectedPosition == position){
            convertView.setBackgroundResource(R.color.item_selected);
        }else if ((position % 2 == 1) ) {
            convertView.setBackgroundResource(R.color.white);
        } else {
            convertView.setBackgroundResource(R.color.item_normal);
        }
        holder.addrs.setText(freights.get(position).getOrigin() + "-"
                + freights.get(position).getDestination());
        holder.time.setText(freights.get(position).getTime());
        return convertView;
    }

    class ViewHolder {
        TextView addrs;
        TextView time;
    }
}
