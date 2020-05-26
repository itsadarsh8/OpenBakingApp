package com.example.bakingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.POJO.IngredientsPOJO;
import com.example.bakingapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
        private ArrayList<IngredientsPOJO> mIngredientsPOJOArrayList;

    public IngredientsAdapter(ArrayList<IngredientsPOJO> ingredientsPOJOArrayList) {
        mIngredientsPOJOArrayList = ingredientsPOJOArrayList;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.ingredients_view_holder, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.ingredientQuantity.setText(mIngredientsPOJOArrayList.get(position).getQuantity());
        holder.ingredientName.setText(mIngredientsPOJOArrayList.get(position).getIngredient());
        holder.ingredientMeasure.setText(mIngredientsPOJOArrayList.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return mIngredientsPOJOArrayList.size();
    }

    public class IngredientsViewHolder extends  RecyclerView.ViewHolder{
            TextView ingredientName;
            TextView ingredientMeasure;
            TextView ingredientQuantity;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientMeasure=itemView.findViewById(R.id.ingredients_measure);
            ingredientName=itemView.findViewById(R.id.ingredient_name);
            ingredientQuantity=itemView.findViewById(R.id.ingredient_quantity);
        }
    }
}
