package com.netsun.yunchengbao.util;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.netsun.yunchengbao.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class FreightAdapter extends RecyclerView.Adapter<FreightAdapter.ViewHolder> {
    private List<Freight> freights;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View freightView;
        TextView origin, dest;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            freightView = itemView;
            origin = (TextView) itemView.findViewById(R.id.tv_origin);
            dest = (TextView) itemView.findViewById(R.id.tv_dest);
            time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    public FreightAdapter(List<Freight> freights) {
        this.freights = freights;
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.freightView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.color.item_selected);
                        break;
                    case MotionEvent.ACTION_UP:
                        int position = holder.getAdapterPosition();
                        if (position % 2 == 1) {
                            v.setBackgroundResource(R.color.white);
                        } else {
                            v.setBackgroundResource(R.color.item_normal);
                        }
                        break;
                    default:break;

                }
                return false;
            }
        });
        holder.freightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Freight freight = freights.get(position);
                Intent intent = new Intent("com.netsun.yunchengbao.ACTION_MISSION");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra(Freight.KEY_ORIGIN, freight.getOrigin());
                intent.putExtra(Freight.KEY_DEST, freight.getDestination());
                intent.putExtra(Freight.KEY_GOODS, freight.getGoods());
                intent.putExtra(Freight.KEY_WEIGHT, freight.getWeight());
                intent.putExtra(Freight.KEY_TIME, freight.getTime());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.freightView;
        if (position % 2 == 1) {
            view.setBackgroundResource(R.color.white);
        } else {
            view.setBackgroundResource(R.color.item_normal);
        }
        Freight freight = freights.get(position);
        holder.origin.setText(freight.getOrigin());
        holder.dest.setText(freight.getDestination());
        holder.time.setText(freight.getTime());
    }

    @Override
    public int getItemCount() {
        return freights.size();
    }


}
