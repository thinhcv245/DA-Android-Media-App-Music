package com.example.mediaappmusic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.R;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<AlbumDTO> items;

    public AlbumAdapter(Context context, ArrayList<AlbumDTO> items) {
        this.context = context;
        this.items = items;
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
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView descript;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img       = itemView.findViewById(R.id.itemAlbum_imageView_img);
            descript   = itemView.findViewById(R.id.itemAlbum_textView_descript);
        }
    }
}
