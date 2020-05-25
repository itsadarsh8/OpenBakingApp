package com.example.bakingapp.POJO_PlainOldJavaObjects;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientsPOJO  implements Parcelable {

    private String mQuantity;
    private String mMeasure;
    private String mIngredient;

    public IngredientsPOJO(String quantity, String measure, String ingredient) {
        mQuantity = quantity;
        mMeasure = measure;
        mIngredient = ingredient;
    }

    protected IngredientsPOJO(Parcel in) {
        mQuantity = in.readString();
        mMeasure = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<IngredientsPOJO> CREATOR = new Creator<IngredientsPOJO>() {
        @Override
        public IngredientsPOJO createFromParcel(Parcel in) {
            return new IngredientsPOJO(in);
        }

        @Override
        public IngredientsPOJO[] newArray(int size) {
            return new IngredientsPOJO[size];
        }
    };

    public String getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public String getIngredient() {
        return mIngredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredient);
    }
}
