package com.example.bakingapp.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            mShort=getArguments().getString("shortDescription");
            mLong=getArguments().getString("longDescription");
            mVideo=getArguments().getString("videoDescription");


            Log.i("MasterDetailFragment",mShort+mLong+mVideo);
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
           // mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
         activity=this.getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.masterfragment_detail, container, false);
        playerView = rootView.findViewById(R.id.video_view);
        TextView shortView=rootView.findViewById(R.id.shortViewDetail);
        TextView longView=rootView.findViewById(R.id.longViewDetail);

        shortView.setText(mShort);
        longView.setText(mLong);
        initializePlayer();

        return rootView;
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
                new DefaultDataSourceFactory(activity, "exoplayer-codelab");
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
