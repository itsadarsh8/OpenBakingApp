package com.example.bakingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.bakingapp.POJO.IngredientsPOJO;
import com.example.bakingapp.R;

import java.util.ArrayList;

public class IngredientsAdapter extends ArrayAdapter<IngredientsPOJO> {
        private ArrayList<IngredientsPOJO> mIngredientsPOJOArrayList;


    public IngredientsAdapter(Context context,ArrayList<IngredientsPOJO> ingredientsPOJOArrayList) {
        super(context,0,ingredientsPOJOArrayList);
        mIngredientsPOJOArrayList=ingredientsPOJOArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredients_view_holder, parent, false);
        }
        TextView ingredientName=convertView.findViewById(R.id.ingredient_name);
        TextView ingredientMeasure=convertView.findViewById(R.id.ingredients_measure);
        TextView ingredientQuantity=convertView.findViewById(R.id.ingredient_quantity);

        ingredientQuantity.setText(mIngredientsPOJOArrayList.get(position).getQuantity());
        ingredientName.setText(mIngredientsPOJOArrayList.get(position).getIngredient());
        ingredientMeasure.setText(mIngredientsPOJOArrayList.get(position).getMeasure());

        return convertView;

    }

}
