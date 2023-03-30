package com.example.mediaappmusic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaappmusic.Activities.AlbumOrPlayListActivity;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.MediaPlayerService;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<AlbumDTO> items;

    FrameLayout frameLayoutLoading;
    public AlbumAdapter(Context context, ArrayList<AlbumDTO> items) {
        this.context = context;
        this.items = items;
    }
    public AlbumAdapter(Context context, ArrayList<AlbumDTO> items, FrameLayout loading) {
        this.context = context;
        this.items = items;
        this.frameLayoutLoading = loading;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumDTO item = items.get(position);
        holder.img.setImageDrawable(Utilities.LoadImageFromWebOperations(item.getThumbnail()));
        holder.descript.setText(item.getTitle());
        holder.linearLayoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AlbumOrPlayListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("IDAlbum", item.getEncodeId());
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView descript;
        LinearLayout linearLayoutParent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img                     = itemView.findViewById(R.id.itemAlbum_imageView_img);
            descript                = itemView.findViewById(R.id.itemAlbum_textView_descript);
            linearLayoutParent      = itemView.findViewById(R.id.itemAlbum_linearLayout_parent);
        }
    }
}
