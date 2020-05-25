package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.bakingapp.POJO_PlainOldJavaObjects.IngredientsPOJO;
import com.example.bakingapp.POJO_PlainOldJavaObjects.RecipePOJO;
import com.example.bakingapp.POJO_PlainOldJavaObjects.StepsPojo;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    private static final String LOG_ERROR="RecipeActivity ";
    private static final String LOG_OUTPUT="RecipeActivity_Output";
    private String name;
    private ArrayList<StepsPojo> stepsList;
    private ArrayList<IngredientsPOJO> ingredientsList;
    private String serving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("RecipePojoObject");
        RecipePOJO recipePOJO = args.getParcelable("RecipePojoObject");

        if (recipePOJO != null) {
            serving=recipePOJO.getServing();
            name=recipePOJO.getName();
            stepsList=recipePOJO.getSteps();
            ingredientsList=recipePOJO.getIngredients();
        }

       else{
           Log.e(LOG_ERROR,"recipePOJO from getParcelable is null");
        }

    }
}
