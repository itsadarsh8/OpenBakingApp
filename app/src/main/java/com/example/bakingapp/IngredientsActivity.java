package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.Adapters.IngredientsAdapter;
import com.example.bakingapp.POJO.IngredientsPOJO;
import com.example.bakingapp.POJO.RecipePOJO;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("RecipePojoObject");
        RecipePOJO recipePOJO = args.getParcelable("RecipePojoObject");
        ArrayList<IngredientsPOJO> ingredientsPOJOArrayList=recipePOJO.getIngredients();
        RecyclerView recyclerView=findViewById(R.id.ingredients_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        IngredientsAdapter ingredientsAdapter=new IngredientsAdapter(ingredientsPOJOArrayList);
        recyclerView.setAdapter(ingredientsAdapter);

    }
}
