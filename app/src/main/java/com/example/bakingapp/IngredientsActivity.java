package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.appwidget.AppWidgetProvider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.bakingapp.Adapters.IngredientsAdapter;
import com.example.bakingapp.POJO.IngredientsPOJO;
import com.example.bakingapp.POJO.RecipePOJO;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private AppWidgetProvider appWidgetProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("RecipePojoObject");
        RecipePOJO recipePOJO = args.getParcelable("RecipePojoObject");
        ArrayList<IngredientsPOJO> ingredientsPOJOArrayList=recipePOJO.getIngredients();
        ListView listView=findViewById(R.id.ingredients_recyclerView);
        IngredientsAdapter ingredientsAdapter=new IngredientsAdapter(this,ingredientsPOJOArrayList);
        listView.setAdapter(ingredientsAdapter);


    }

}
