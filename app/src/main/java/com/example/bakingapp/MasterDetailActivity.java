package com.example.bakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * An activity representing a single MasterFragment detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MasterStepActivity}.
 */
public class MasterDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);


        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString("shortDescription",
                    getIntent().getStringExtra("shortDescription"));
        arguments.putString("longDescription",
                getIntent().getStringExtra("longDescription"));
        arguments.putString("videoDescription",
                getIntent().getStringExtra("videoDescription"));
            MasterDetailFragment fragment = new MasterDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.masterfragment_detail_container, fragment)
                    .commit();
        }
    }

