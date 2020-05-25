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

import com.example.bakingapp.MasterStepActivity;
import com.example.bakingapp.POJO.RecipePOJO;
import com.example.bakingapp.R;

import java.util.ArrayList;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.mainViewHolder> {
    ArrayList<RecipePOJO> mRecipePOJOArrayList;
    Context mContext;

    public MainViewAdapter(Context context,ArrayList<RecipePOJO> recipePOJOArrayList) {
        mRecipePOJOArrayList = recipePOJOArrayList;
        mContext=context;
    }

    @NonNull
    @Override
    public mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.main_item_layout, parent, false);
        return new mainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mainViewHolder holder, final int position) {
        String name=mRecipePOJOArrayList.get(position).getName();
        holder.item.setText(name);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MasterStepActivity.class);
                Bundle args = new Bundle();
                args.putParcelable("RecipePojoObject",mRecipePOJOArrayList.get(position));
                intent.putExtra("RecipePojoObject", args);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipePOJOArrayList.size();
    }

    public class mainViewHolder extends RecyclerView.ViewHolder{
        public TextView item;
        public mainViewHolder(@NonNull View itemView) {
            super(itemView);
            item=itemView.findViewById(R.id.recipeName);
        }
    }
}
