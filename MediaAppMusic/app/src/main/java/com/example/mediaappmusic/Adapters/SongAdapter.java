package com.example.mediaappmusic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.img.setImageDrawable(Utilities.LoadImageFromWebOperations("https://photo-resize-zmp3.zmdcdn.me/w165_r1x1_jpeg/cover/0/9/5/4/09542fd83e019d4734587f836bc9bbc0.jpg"));
        holder.nameSong.setText(item.getTitle());
        holder.nameSing.setText(item.getArtistsNames());
        holder.encodeId = item.getEncodeId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private String encodeId;
        ImageView img;
        TextView nameSong, nameSing;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView img       = itemView.findViewById(R.id.itemSong_imageView_img);
            TextView nameSong   = itemView.findViewById(R.id.itemSong_textView_nameSong);
            TextView nameSing   = itemView.findViewById(R.id.itemSong_textView_nameSing);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
