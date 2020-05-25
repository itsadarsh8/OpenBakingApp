package com.example.bakingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment representing a single MasterFragment detail screen.
 * This fragment is either contained in a {@link MasterStepActivity}
 * in two-pane mode (on tablets) or a {@link MasterDetailActivity}
 * on handsets.
 */
public class MasterDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MasterDetailFragment() {
    }

    private String mLong;
    private String mShort;
    private String mVideo;

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

            //Activity activity = this.getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.masterfragment_detail, container, false);



        return rootView;
    }
}
