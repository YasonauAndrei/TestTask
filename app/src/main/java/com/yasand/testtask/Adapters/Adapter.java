package com.yasand.testtask.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yasand.testtask.Models.Datum;
import com.yasand.testtask.R;

import java.util.List;

/**
 * Created by user on 04.10.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    List<Datum> datumList;
    Context context;

    public Adapter(List<Datum> datumList, Context context) {
        super();
        this.datumList=datumList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Datum datum=datumList.get(position);
        Picasso.with(context)
                .load(datum.getUrl())
                .into(holder.imageView);
        holder.tvTitle.setText(datum.getTitle());
        holder.tvNumber.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return datumList!=null?datumList.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvNumber;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image_icon);
            tvNumber=(TextView)itemView.findViewById(R.id.text_number);
            tvTitle=(TextView)itemView.findViewById(R.id.text_title);
        }
    }
}
