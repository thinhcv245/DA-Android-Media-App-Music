package com.example.mediaappmusic.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.MediaPlayerService;

import java.io.IOException;

public class PlaySongFragment extends Fragment {
    MediaPlayerService mediaPlayerService;
    ImageView imageViewRotate, imageViewPlayOrPause, imageViewIconShare, imageViewIconHeart,
            imageViewIconPrevious, imageViewIconNext, imageViewIconRandom, imageViewIconRepeat;
    TextView textViewStartTime, textViewEndTime, textViewTitleName, textViewArtist;
    SeekBar seekBarSong;
    Animation rotateAnimation;

    Context context;
    boolean heart = false;

    public PlaySongFragment(Context context) {
        this.context = context;
    }

    public static PlaySongFragment newInstance(Context context) {
        PlaySongFragment fragment = new PlaySongFragment(context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_play_song, container, false);

        rotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_circle);
        imageViewRotate         = layout.findViewById(R.id.playSongFragment_imageView_rotate);
        imageViewPlayOrPause    = layout.findViewById(R.id.playSongFragment_imageView_iconPlayOrPause);
        textViewStartTime       = layout.findViewById(R.id.playSongFragment_textView_timeStart);
        textViewEndTime         = layout.findViewById(R.id.playSongFragment_textView_timeEnd);
        seekBarSong             = layout.findViewById(R.id.playSongFragment_seekbar);
        textViewArtist          = layout.findViewById(R.id.playSongFragment_textView_artist);
        textViewTitleName       = layout.findViewById(R.id.playSongFragment_textView_titleMusic);
        imageViewIconShare      = layout.findViewById(R.id.playSongFragment_imageView_iconShare);
        imageViewIconHeart      = layout.findViewById(R.id.playSongFragment_imageView_iconHeart);
        imageViewIconPrevious   = layout.findViewById(R.id.playSongFragment_imageView_iconPrevious);
        imageViewIconNext       = layout.findViewById(R.id.playSongFragment_imageView_iconNext);
        imageViewIconRandom     = layout.findViewById(R.id.playSongFragment_imageView_iconRandom);
        imageViewIconRepeat     = layout.findViewById(R.id.playSongFragment_imageView_iconRepeat);

        if(MediaPlayerService.getInstance().getPlayStatus() == MediaPlayerService.PLAY_REPEAT) {
            imageViewIconRepeat.setColorFilter(Utilities.getCOLOR_ACTIVE(getContext()));
        }
        else {
            imageViewIconRepeat.setColorFilter(Utilities.getCOLOR_WHITE(getContext()));
        }
        if(MediaPlayerService.getInstance().getPlayStatus() == MediaPlayerService.PLAY_RANDOM) {
            imageViewIconRandom.setColorFilter(Utilities.getCOLOR_ACTIVE(getContext()));
        }
        else {
            imageViewIconRandom.setColorFilter(Utilities.getCOLOR_WHITE(getContext()));
        }

        SongDTO songDTO = MediaPlayerService.getSongs().get(MediaPlayerService.getPosition());
        imageViewRotate.setImageDrawable(Utilities.LoadImageFromWebOperations(songDTO.getThumbnailM()));
        textViewArtist.setText(songDTO.getArtistsNames());
        textViewTitleName.setText(songDTO.getTitle());

        mediaPlayerService = MediaPlayerService.getInstance();

        mediaPlayerService.createMediaPlayer(getContext(), imageViewPlayOrPause, imageViewRotate,
                rotateAnimation, seekBarSong, textViewStartTime, textViewEndTime, textViewTitleName, textViewArtist);

        if(MediaPlayerService.getMediaPlayer() != null) {

            MediaPlayerService.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayerService.createMediaPlayer(getContext(), imageViewPlayOrPause, imageViewRotate,
                            rotateAnimation, seekBarSong, textViewStartTime, textViewEndTime, textViewTitleName, textViewArtist);
                    seekBarSong.setProgress(0);
                }
            });


            seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    MediaPlayerService.getInstance().setTrackingTouch(true);
                    MediaPlayerService.getInstance().pauseSong(seekBarSong, imageViewPlayOrPause, imageViewRotate);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    MediaPlayerService.getMediaPlayer().seekTo(seekBar.getProgress());
                    MediaPlayerService.getInstance().setTrackingTouch(false);
                    MediaPlayerService.getInstance().setRotationWithTime(imageViewRotate, seekBar.getProgress());
                    MediaPlayerService.getInstance().playSong(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation, textViewTitleName, textViewArtist);
                }
            });


            imageViewPlayOrPause.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
               if(MediaPlayerService.getMediaPlayer().isPlaying()) {
                       mediaPlayerService.pauseSong(seekBarSong, imageViewPlayOrPause, imageViewRotate);
                   }
                   else {
                       mediaPlayerService.playSong(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation, textViewTitleName, textViewArtist);
                   }
               }
            });
            imageViewIconRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageViewIconRandom.setColorFilter(Utilities.getCOLOR_WHITE(getContext()));
                    int colorIconRepeat;
                    if(MediaPlayerService.getInstance().getPlayStatus() == MediaPlayerService.PLAY_REPEAT) {
                        MediaPlayerService.getInstance().setPlayDefault();
                        colorIconRepeat = Utilities.getCOLOR_WHITE(getContext());
                    }
                    else {
                        colorIconRepeat = Utilities.getCOLOR_ACTIVE(getContext());
                        MediaPlayerService.getInstance().setPlayRepeat();
                    }
                    imageViewIconRepeat.setColorFilter(colorIconRepeat);
                }
            });
            imageViewIconRandom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageViewIconRepeat.setColorFilter(Utilities.getCOLOR_WHITE(getContext()));
                    int colorIconRandom;
                    if(MediaPlayerService.getInstance().getPlayStatus() == MediaPlayerService.PLAY_RANDOM) {
                        MediaPlayerService.getInstance().setPlayDefault();
                        colorIconRandom = Utilities.getCOLOR_WHITE(getContext());
                    }
                    else {
                        colorIconRandom = Utilities.getCOLOR_ACTIVE(getContext());
                        MediaPlayerService.getInstance().setPlayRandom();
                    }
                    imageViewIconRandom.setColorFilter(colorIconRandom);
                }
            });
            imageViewIconNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextSong();
                }
            });
            imageViewIconPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    previousSong();
                }
            });
            imageViewIconHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(heart) {
                        imageViewIconHeart.setImageResource(R.drawable.icon_heart_empty);
                        heart = false;
                    }
                    else {
                        imageViewIconHeart.setImageResource(R.drawable.icon_heart);
                        heart = true;
                    }
                }
            });
        }
        return layout;
    }

    private void nextSong() {
        try {
            MediaPlayerService.getInstance().nextSong(imageViewRotate, textViewTitleName, textViewArtist);
        } catch (IOException e) {
            Toast.makeText(context, "Hệ thống phát nhạc lỗi", Toast.LENGTH_LONG).show();
        }
    }
    private void previousSong() {
        try {
            MediaPlayerService.getInstance().previousSong(imageViewRotate, textViewTitleName, textViewArtist);
        } catch (IOException e) {
            Toast.makeText(context, "Hệ thống phát nhạc lỗi", Toast.LENGTH_LONG).show();
        }
    }
}