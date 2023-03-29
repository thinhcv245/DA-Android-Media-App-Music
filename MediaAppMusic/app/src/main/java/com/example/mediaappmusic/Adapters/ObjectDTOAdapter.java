package com.example.mediaappmusic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaappmusic.Activities.OnePlayListActivity;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.example.mediaappmusic.R;

import java.util.ArrayList;

public class ObjectDTOAdapter extends RecyclerView.Adapter<ObjectDTOAdapter.ViewHolder> {

    Context context;
    ArrayList<ObjectDTO> objectDTOS;

    FrameLayout frameLayoutLoading;
    public ObjectDTOAdapter(Context context, ArrayList<ObjectDTO> objectDTOS) {
        this.context = context;
        this.objectDTOS = objectDTOS;
    }
    public ObjectDTOAdapter(Context context, ArrayList<ObjectDTO> objectDTOS, FrameLayout loading) {
        this.context = context;
        this.objectDTOS = objectDTOS;
        this.frameLayoutLoading = loading;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_top100, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ObjectDTO objectDTO = objectDTOS.get(position);
        AlbumAdapter albumAdapter = new AlbumAdapter(context, objectDTO.getItems());
        RecyclerView recyclerView = holder.recyclerView;
        if(recyclerView != null) {
            recyclerView.setAdapter(albumAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
        holder.textViewTitle.setText(objectDTO.getTitle());
        holder.linearLayoutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), OnePlayListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objectDTO", objectDTO);
                i.putExtras(bundle);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayoutTitle;
        RecyclerView recyclerView;
        TextView textViewTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutTitle       = itemView.findViewById(R.id.itemTop100_linearLayout_title);
            recyclerView            = itemView.findViewById(R.id.itemTop100_recyclerView_recycle);
            textViewTitle           = itemView.findViewById(R.id.itemTop100_textView_title);
        }
    }
}
