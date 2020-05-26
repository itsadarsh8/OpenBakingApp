package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bakingapp.Adapters.IngredientsAdapter;
import com.example.bakingapp.POJO.IngredientsPOJO;
import com.example.bakingapp.POJO.RecipePOJO;
import com.example.bakingapp.Widget.IngredientWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private AppWidgetProvider appWidgetProvider;
   public static String widgetText;

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

        FloatingActionButton setWidget=findViewById(R.id.addWidget);
        setWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widgetText="";
                for(int i=0;i<ingredientsPOJOArrayList.size();i++){
                    widgetText+=(i+1)+(".")+ingredientsPOJOArrayList.get(i).getIngredient()+" -"+ingredientsPOJOArrayList.get(i).getQuantity()+ingredientsPOJOArrayList.get(i).getMeasure();
                    widgetText+="\n ";
                }

                Toast.makeText(IngredientsActivity.this,"Added Ingredients to Widget", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IngredientsActivity.this, IngredientWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(),IngredientWidget.class));
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                sendBroadcast(intent);



            }
        });


    }

}
