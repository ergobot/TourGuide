package com.example.android.tourguide.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tourguide.R;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(Model item,int position);
    }

    private List<Model> modelList;
    private OnItemClickListener listener = null;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title;
        public TextView description;


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.placeimage);
            title = (TextView) view.findViewById(R.id.titletextview);
            description = (TextView) view.findViewById(R.id.description);
        }

        public void bind(final Model item, final OnItemClickListener listener, final int position) {

            imageView.setImageResource(item.getImageResourceId());
            title.setText(item.getName());
            description.setText(item.getDetails());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    listener.onItemClick(item,position);
                }
            });
        }
    }

    public ModelAdapter(List<Model> modelList, OnItemClickListener listener) {
        this.modelList = modelList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_model, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model model = modelList.get(position);
        holder.bind(model,listener,position);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}
