package com.example.bakingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.Adapters.MainViewAdapter;
import com.example.bakingapp.IdlingResource.MyIdlingResource;
import com.example.bakingapp.POJO.RecipePOJO;
import com.example.bakingapp.Utils.JsonUtil;
import com.example.bakingapp.Utils.NetworkUtil;

import java.util.ArrayList;

import id.ionbit.ionalert.IonAlert;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_SUBJECT = "MainActivity_Error";
    private static final String LOG_OUTPUT = "MainActivity_Output";
    ArrayList<RecipePOJO> recipeArrayList;
    RecyclerView recyclerView;
    MainViewAdapter mainViewAdapter;
    IAmAsyncTask mainViewAsyncTask;

    @Nullable
    private static MyIdlingResource mIdlingResource=new MyIdlingResource();

    @VisibleForTesting
    @NonNull
    public static IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new MyIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<RecipePOJO> recipeArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainViewAdapter = new MainViewAdapter(this, recipeArrayList);
        recyclerView.setAdapter(mainViewAdapter);

        mainViewAsyncTask = new IAmAsyncTask();

        String NETWORK_STATUS = getNetworkStatus();
        if (NETWORK_STATUS == "COONNECTED") {
            mainViewAsyncTask.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        }
        else{
            new IonAlert(this, IonAlert.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Check your network connection!")
                    .show();
        }

    }

    private String getNetworkStatus() {


        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NI = CM.getActiveNetworkInfo();
        if (NI != null && NI.isConnected()) {

            return "COONNECTED";

        } else {
            return "NOT_CONNECTED";
        }
    }

    public class IAmAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mIdlingResource.setIdleState(false);
        }

        @Override
        protected String doInBackground(String... urls) {
            String json = NetworkUtil.fetchJsonString(urls[0]);
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            ArrayList<RecipePOJO> recipeArrayList = JsonUtil.fetchFeaturesFromJson(json);
            for (int k = 0; k < recipeArrayList.size(); k++) {
                for (int m = 0; m < recipeArrayList.get(k).getIngredients().size(); m++) {

                }
            }
            mainViewAdapter = new MainViewAdapter(MainActivity.this, recipeArrayList);
            recyclerView.setAdapter(mainViewAdapter);

            super.onPostExecute(json);
            mIdlingResource.setIdleState(true);

        }
    }


}
