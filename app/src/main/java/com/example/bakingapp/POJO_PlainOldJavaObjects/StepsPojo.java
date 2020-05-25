package com.example.bakingapp.POJO_PlainOldJavaObjects;

import android.os.Parcel;
import android.os.Parcelable;

public class StepsPojo implements Parcelable
{

    private String mid;
    private String mShortDescription;
    private String mLongDescription;
    private String mVideoDescription;

    public StepsPojo(String id, String shortDescription, String longDescription, String videoDescription) {
        mid = id;
        mShortDescription = shortDescription;
        mLongDescription = longDescription;
        mVideoDescription = videoDescription;
    }

    protected StepsPojo(Parcel in) {
        mid = in.readString();
        mShortDescription = in.readString();
        mLongDescription = in.readString();
        mVideoDescription = in.readString();
    }

    public static final Creator<StepsPojo> CREATOR = new Creator<StepsPojo>() {
        @Override
        public StepsPojo createFromParcel(Parcel in) {
            return new StepsPojo(in);
        }

        @Override
        public StepsPojo[] newArray(int size) {
            return new StepsPojo[size];
        }
    };

    public String getMid() {
        return mid;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public String getVideoDescription() {
        return mVideoDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mid);
        dest.writeString(mShortDescription);
        dest.writeString(mLongDescription);
        dest.writeString(mVideoDescription);
    }
}
