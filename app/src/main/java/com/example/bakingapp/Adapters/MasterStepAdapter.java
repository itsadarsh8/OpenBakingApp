package com.example.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.MasterDetailActivity;
import com.example.bakingapp.MasterDetailFragment;
import com.example.bakingapp.MasterStepActivity;
import com.example.bakingapp.POJO.StepsPojo;
import com.example.bakingapp.R;

import java.util.ArrayList;

public class MasterStepAdapter extends RecyclerView.Adapter<MasterStepAdapter.ViewHolder> {

    private final MasterStepActivity mParentActivity;
    private final ArrayList<StepsPojo> stepList;
    private final boolean mTwoPane;

    public MasterStepAdapter(MasterStepActivity parent, ArrayList<StepsPojo> items, boolean twoPane) {
        stepList = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }


    @Override
    public MasterStepAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_fragment_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.shortView.setText(stepList.get(position).getShortDescription());
        holder.longView.setText(stepList.get(position).getLongDescription());


        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MasterStepAdapter",String.valueOf(stepList.size()));
                //    DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
//                if (mTwoPane) {
//
//                    Bundle arguments = new Bundle();
//                    arguments.putString("shortDescription", stepList.get(position).getShortDescription());
//                    arguments.putString("longDescription", stepList.get(position).getLongDescription());
//                    arguments.putString("videoDescription", stepList.get(position).getVideoDescription());
//                    MasterDetailFragment fragment = new MasterDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.masterfragment_detail_container, fragment)
//                            .commit();
//                } else {
//                    Context context = view.getContext();
//                    Intent intent = new Intent(context, MasterDetailActivity.class);
//                    intent.putExtra("shortDescription", stepList.get(position).getShortDescription());
//                    intent.putExtra("longDescription", stepList.get(position).getLongDescription());
//                    intent.putExtra("videoDescription", stepList.get(position).getVideoDescription());
//                    //  intent.putExtra(MasterDetailFragment.ARG_ITEM_ID, item.id);
//
//                    context.startActivity(intent);
//                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView shortView;
        final TextView longView;

        ViewHolder(View view) {
            super(view);
            shortView = (TextView) view.findViewById(R.id.stepSortView);
            longView = (TextView) view.findViewById(R.id.stepLongView);
        }
    }
}
