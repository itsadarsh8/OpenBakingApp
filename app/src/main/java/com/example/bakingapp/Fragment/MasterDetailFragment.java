package com.example.bakingapp.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bakingapp.POJO.StepsPojo;
import com.example.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;


public class MasterDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MasterDetailFragment() {
    }

    private String mLong;
    private String mShort;
    private String mVideo;
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    Activity activity;
    private ArrayList<StepsPojo> mStepList;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mStepList = getArguments().getParcelableArrayList("stepList");
        position = getArguments().getInt("position");

        mShort =mStepList.get(position).getShortDescription();
        mLong =mStepList.get(position).getLongDescription();
        mVideo =mStepList.get(position).getVideoDescription();


        activity = this.getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.masterfragment_detail, container, false);
        playerView = rootView.findViewById(R.id.video_view);
        TextView shortView = rootView.findViewById(R.id.shortViewDetail);
        TextView longView = rootView.findViewById(R.id.longViewDetail);
        Button nextStep=rootView.findViewById(R.id.nextStepButton);
         startFragment(shortView, longView);

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;

                if(position==mStepList.size()) {
                    position=0;
                }
                    mShort = mStepList.get(position).getShortDescription();
                    mLong = mStepList.get(position).getLongDescription();
                    mVideo = mStepList.get(position).getVideoDescription();

                    startFragment(shortView, longView);
                }


        });

        return rootView;
    }

    private void startFragment(TextView shortView, TextView longView) {
        shortView.setText(mShort);
        longView.setText(mLong);
        initializePlayer();
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(activity);
        playerView.setPlayer(player);

        Uri uri = Uri.parse(mVideo);
        MediaSource mediaSource = buildMediaSource(uri);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, true, true);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(activity, "exoplayer");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
}
