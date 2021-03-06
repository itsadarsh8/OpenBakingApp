package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Adapters.MasterStepAdapter;
import com.example.bakingapp.Fragment.MasterDetailFragment;
import com.example.bakingapp.POJO.IngredientsPOJO;
import com.example.bakingapp.POJO.RecipePOJO;
import com.example.bakingapp.POJO.StepsPojo;

import java.util.ArrayList;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MasterDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MasterStepActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public static ArrayList<StepsPojo> mStepsList;
    public static ArrayList<IngredientsPOJO> mIngredientsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_list);


        if (findViewById(R.id.masterfragment_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("RecipePojoObject");
        RecipePOJO recipePOJO = args.getParcelable("RecipePojoObject");
        mStepsList = recipePOJO.getSteps();
        mIngredientsList = recipePOJO.getIngredients();


        View recyclerView = findViewById(R.id.masterfragment_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        TextView ingredients = findViewById(R.id.ingredients);
        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MasterStepActivity.this, IngredientsActivity.class);
                Bundle args = new Bundle();
                args.putParcelable("RecipePojoObject", recipePOJO);
                intent.putExtra("RecipePojoObject", args);

                startActivity(intent);
            }
        });

       //Set the first Instruction step if Tablet View
        if (mTwoPane) {
            setFirstStep();

        }
    }

    private void setFirstStep() {
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList("stepList",mStepsList);
        arguments.putInt("position",0);
        MasterDetailFragment fragment = new MasterDetailFragment();
        fragment.setArguments(arguments);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.masterfragment_detail_container, fragment)
                .commit();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new MasterStepAdapter(this, mStepsList, mTwoPane));
    }


}
