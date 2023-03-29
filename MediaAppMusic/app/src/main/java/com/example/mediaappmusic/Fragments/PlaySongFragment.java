package com.example.mediaappmusic.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.MediaPlayerService;

public class PlaySongFragment extends Fragment {

    private static final String ARG_PARAMSONG = "paramSong";
    private SongDTO song;
    MediaPlayerService mediaPlayerService;

    ImageView imageViewRotate, imageViewPlayOrPause;
    TextView textViewStartTime, textViewEndTime, textViewTitleName, textViewArtist;
    SeekBar seekBarSong;
    Animation rotateAnimation;

    public PlaySongFragment() {
        // Required empty public constructor
    }

    public static PlaySongFragment newInstance(SongDTO song) {
        PlaySongFragment fragment = new PlaySongFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAMSONG, song);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            song = (SongDTO) getArguments().get(ARG_PARAMSONG);
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

        mediaPlayerService = MediaPlayerService.getInstance();

        mediaPlayerService.createMediaPlayer(getContext(), imageViewPlayOrPause, imageViewRotate,
                rotateAnimation, seekBarSong, textViewStartTime, textViewEndTime);

        if(MediaPlayerService.getMediaPlayer() != null) {

            MediaPlayerService.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayerService.createMediaPlayer(getContext(), imageViewPlayOrPause, imageViewRotate,
                            rotateAnimation, seekBarSong, textViewStartTime, textViewEndTime);
                    seekBarSong.setProgress(0);
                }
            });

            seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(seekBar.getProgress() == seekBar.getMax()) {
                       mediaPlayerService.pauseSong(imageViewPlayOrPause, imageViewRotate);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    MediaPlayerService.getMediaPlayer().seekTo(seekBar.getProgress());
                }
            });


            imageViewPlayOrPause.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
               if(MediaPlayerService.getMediaPlayer().isPlaying()) {
                       mediaPlayerService.pauseSong(imageViewPlayOrPause, imageViewRotate);
                   }
                   else {
                       mediaPlayerService.playSong(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation);
                   }
               }
            });
        }
        return layout;
    }
}