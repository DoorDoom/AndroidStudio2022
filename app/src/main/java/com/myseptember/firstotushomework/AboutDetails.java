package com.myseptember.firstotushomework;

import android.os.Parcel;
import android.os.Parcelable;

public class AboutDetails implements Parcelable {
    private String pathToPicture;
    private String pathToText;
    private String pathToTitle;

    public AboutDetails(String namePicture, String nameText, String nameTitle) {
        pathToPicture = namePicture;
        pathToText = nameText +"_text";
        pathToTitle = nameTitle +"_title";
    }

    public AboutDetails(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        pathToPicture = data[0];
        pathToText = data[1];
        pathToTitle = data[2];
    }

    public String getPathToText() {
        return pathToText;
    }

    public void setPathToText(String nameText) {
        pathToText = "@strings/"+nameText;
    }

    public String getPathToTitle() {
        return pathToTitle;
    }

    public void setPathToTitle(String nameTitle) {
        pathToTitle = "@strings/"+nameTitle;
    }

    public String getPathToPicture() {
        return pathToPicture;
    }

    public void setPathToPicture(String namePicture) {
        pathToPicture = "@drawable/"+namePicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { pathToPicture, pathToText, pathToTitle });
    }

    public static final Parcelable.Creator<AboutDetails> CREATOR = new Parcelable.Creator<AboutDetails>() {

        @Override
        public AboutDetails createFromParcel(Parcel source) {
            return new AboutDetails(source);
        }

        @Override
        public AboutDetails[] newArray(int size) {
            return new AboutDetails[size];
        }
    };

}
