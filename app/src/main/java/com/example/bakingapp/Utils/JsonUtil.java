package com.example.bakingapp.Utils;

import android.util.Log;

import com.example.bakingapp.POJO.IngredientsPOJO;
import com.example.bakingapp.POJO.RecipePOJO;
import com.example.bakingapp.POJO.StepsPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtil {

    private JsonUtil(){}
    private static final String LOG_ERROR= "jsonUtilClass";

    public static ArrayList<RecipePOJO> fetchFeaturesFromJson(String json){

            ArrayList<RecipePOJO> recipePOJOArrayList=new ArrayList<>();
            if(json!=null) {
                try {
                    JSONArray baseArray = new JSONArray(json);
                    for (int i = 0; i < baseArray.length(); i++) {
                        JSONObject baseJsonObject = baseArray.getJSONObject(i);
                        JSONArray ingredientArray = baseJsonObject.getJSONArray("ingredients");
                        JSONArray stepsArray=baseJsonObject.getJSONArray("steps");

                        ArrayList<IngredientsPOJO> ingredients = fetchIngredientsFeatures(ingredientArray);
                        ArrayList<StepsPojo> steps = fetchStepsFeatures(stepsArray);
                        RecipePOJO recipe = fetchRecipeFeatures(baseJsonObject, ingredients, steps);

                        recipePOJOArrayList.add(recipe);
                   }
                } catch (JSONException e) {
                    Log.e(LOG_ERROR,"Error fetchFeaturesFromJson -"+e);
                }
            }
            else
            {
               Log.e(LOG_ERROR,"Json value passed is null");
            }

            return recipePOJOArrayList;
        }

    private static ArrayList<IngredientsPOJO> fetchIngredientsFeatures(JSONArray ingredientArray) {

            ArrayList<IngredientsPOJO> ingredientsArrayList=new ArrayList<>();
       try{

                for(int j=0;j<ingredientArray.length();j++){
                    JSONObject ingredientObject=ingredientArray.getJSONObject(j);
                    String quantity=ingredientObject.getString("quantity");
                    String measure=ingredientObject.getString("measure");
                    String ingredient=ingredientObject.getString("ingredient");

                    IngredientsPOJO ingredientsPOJO=new IngredientsPOJO(quantity,measure,ingredient);
                    ingredientsArrayList.add(ingredientsPOJO);

                }


        } catch (JSONException e) {
            Log.e(LOG_ERROR,"Error fetchIngredientsFeatures -"+e);
        }

        return ingredientsArrayList;
    }

    private static ArrayList<StepsPojo> fetchStepsFeatures(JSONArray stepsArray) {
        ArrayList<StepsPojo> stepsArrayList=new ArrayList<>();
        try{

            for(int j=0;j<stepsArray.length();j++){
                JSONObject stepsObject=stepsArray.getJSONObject(j);
                String id=stepsObject.getString("id");
                String shortDescription=stepsObject.getString("shortDescription");
                String description=stepsObject.getString("description");
                String videoURL=stepsObject.getString("videoURL");

                StepsPojo stepsPojo=new StepsPojo(id,shortDescription,description,videoURL);
                stepsArrayList.add(stepsPojo);

                Log.i("JsonUtil",shortDescription+description+videoURL);

            }

        } catch (JSONException e) {
            Log.e(LOG_ERROR,"Error fetchStepsFeatures -"+e);
        }
        return stepsArrayList;
    }

    private static RecipePOJO fetchRecipeFeatures(JSONObject jsonObject, ArrayList<IngredientsPOJO> ingredients, ArrayList<StepsPojo> steps) {

        try {
            String id=jsonObject.getString("id");
            String name=jsonObject.getString("name");
            String serving=jsonObject.getString("servings");

            RecipePOJO recipePOJO=new RecipePOJO(Integer.parseInt(id),name,serving,steps,ingredients);
            return recipePOJO;

        } catch (JSONException e) {
            Log.e(LOG_ERROR,"Error fetchRecipeFeatures -"+e);
        }

       return null;
    }


}
