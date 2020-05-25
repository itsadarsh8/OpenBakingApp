package com.example.bakingapp.POJO_PlainOldJavaObjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RecipePOJO  implements Parcelable {

    private int mid;
    private String mName;
    private ArrayList<StepsPojo> mSteps;
    private ArrayList<IngredientsPOJO> mIngredients;
    private String mServing;

    public RecipePOJO(int id, String name, String serving,ArrayList<StepsPojo> steps, ArrayList<IngredientsPOJO> ingredients) {
        mid = id;
        mName = name;
        mSteps = steps;
        mIngredients = ingredients;
        mServing=serving;
    }

    protected RecipePOJO(Parcel in) {
        mid = in.readInt();
        mName = in.readString();
        mSteps = in.createTypedArrayList(StepsPojo.CREATOR);
        mIngredients = in.createTypedArrayList(IngredientsPOJO.CREATOR);
        mServing = in.readString();
    }

    public static final Creator<RecipePOJO> CREATOR = new Creator<RecipePOJO>() {
        @Override
        public RecipePOJO createFromParcel(Parcel in) {
            return new RecipePOJO(in);
        }

        @Override
        public RecipePOJO[] newArray(int size) {
            return new RecipePOJO[size];
        }
    };

    public String getServing() {
        return mServing;
    }

    public int getMid() {
        return mid;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<StepsPojo> getSteps() {
        return mSteps;
    }

    public ArrayList<IngredientsPOJO> getIngredients() {
        return mIngredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mid);
        dest.writeString(mName);
        dest.writeTypedList(mSteps);
        dest.writeTypedList(mIngredients);
        dest.writeString(mServing);
    }
}
