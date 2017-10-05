
package com.yasand.testtask.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable{

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    Datum(Parcel parcel)
    {
        setUrl(parcel.readString());
        setTitle(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getUrl());
        parcel.writeString(getTitle());
    }

    public static final Parcelable.Creator<Datum> CREATOR = new Parcelable.Creator<Datum>()
    {

        @Override
        public Datum createFromParcel(Parcel parcel) {
            return new Datum(parcel);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };
}
