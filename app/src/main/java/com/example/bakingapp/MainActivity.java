package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.bakingapp.Adapters.MainViewAdapter;
import com.example.bakingapp.POJO_PlainOldJavaObjects.RecipePOJO;
import com.example.bakingapp.Utils.JsonUtil;
import com.example.bakingapp.Utils.NetworkUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_SUBJECT="MainActivity_Error";
    private static final String LOG_OUTPUT="MainActivity_Output";
    ArrayList<RecipePOJO> recipeArrayList;
    RecyclerView recyclerView;
    MainViewAdapter mainViewAdapter;
    IAmAsyncTask mainViewAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<RecipePOJO> recipeArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainViewAdapter=new MainViewAdapter(this,recipeArrayList);
        recyclerView.setAdapter(mainViewAdapter);

        mainViewAsyncTask=new IAmAsyncTask();
        mainViewAsyncTask.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

    }

    public class IAmAsyncTask extends AsyncTask<String,Void,String > {

        @Override
        protected String doInBackground(String... urls) {
            String json= NetworkUtil.fetchJsonString(urls[0]);
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            ArrayList<RecipePOJO> recipeArrayList= JsonUtil.fetchFeaturesFromJson(json);
            mainViewAdapter=new MainViewAdapter(MainActivity.this,recipeArrayList);
            recyclerView.setAdapter(mainViewAdapter);
            super.onPostExecute(json);
        }
    }


}
