package com.example.mediaappmusic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaappmusic.Activities.PlaySongActivity;
import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    Context context;
    ArrayList<SongDTO> items;

    public SongAdapter(Context context, ArrayList<SongDTO> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongDTO item = items.get(position);
        if(item != null) {
            holder.img.setImageDrawable(Utilities.LoadImageFromWebOperations(item.getThumbnailM()));
            holder.nameSong.setText(item.getTitle());
            holder.nameSing.setText(item.getArtistsNames());
            holder.encodeId = item.getEncodeId();
        }
        holder.linearLayoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PlaySongActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("IDSong", item.getEncodeId());
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
        private String encodeId;
        ImageView img;
        TextView nameSong, nameSing;
        LinearLayout linearLayoutParent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img                     = itemView.findViewById(R.id.itemSong_imageView_img);
            nameSong                = itemView.findViewById(R.id.itemSong_textView_nameSong);
            nameSing                = itemView.findViewById(R.id.itemSong_textView_nameSing);
            linearLayoutParent      = itemView.findViewById(R.id.itemSong_linearLayout_parent);
        }

    }
}
