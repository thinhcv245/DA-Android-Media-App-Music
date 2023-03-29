package com.example.mediaappmusic.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaappmusic.Activities.PlaySongActivity;
import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.Fragments.PlaySongFragment;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.MediaPlayerService;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    Context context;
    ArrayList<SongDTO> items;
    int idColor = R.color.black;
    public SongAdapter(Context context, ArrayList<SongDTO> items) {
        this.context = context;
        this.items = items;
    }
    public SongAdapter(Context context, ArrayList<SongDTO> items, @NonNull int idColor) {
        this.context = context;
        this.items = items;
        this.idColor = idColor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = position;
        SongDTO item = items.get(position);
        if(item != null) {
            holder.img.setImageDrawable(Utilities.LoadImageFromWebOperations(item.getThumbnailM()));
            holder.nameSong.setText(item.getTitle());
            holder.nameSing.setText(item.getArtistsNames());
            holder.encodeId = item.getEncodeId();
            if(pos == MediaPlayerService.getPosition()) {
                holder.gifImageViewIconPlaySong.setVisibility(View.VISIBLE);
            }
            else {
                holder.gifImageViewIconPlaySong.setVisibility(View.GONE);
            }
        }
        holder.linearLayoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayerService.setPosition(pos);
                Intent i = new Intent(context, PlaySongActivity.class);
                context.startActivity(i);
                ((AppCompatActivity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private String encodeId;
        ImageView img, threePointVerticle;
        TextView nameSong, nameSing;
        LinearLayout linearLayoutParent;
        CardView gifImageViewIconPlaySong;
        @SuppressLint("ResourceAsColor")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img                         = itemView.findViewById(R.id.itemSong_imageView_img);
            threePointVerticle          = itemView.findViewById(R.id.itemSong_imageView_iconThreePointVertical);
            nameSong                    = itemView.findViewById(R.id.itemSong_textView_nameSong);
            nameSing                    = itemView.findViewById(R.id.itemSong_textView_nameSing);
            linearLayoutParent          = itemView.findViewById(R.id.itemSong_linearLayout_parent);
            gifImageViewIconPlaySong    = itemView.findViewById(R.id.itemSong_cardView_iconGifPlaySong);

            nameSong.setTextColor(context.getColor(idColor));
            threePointVerticle.setColorFilter(context.getColor(idColor));
            gifImageViewIconPlaySong.setVisibility(View.GONE);
        }

    }
}
