package com.sara.project.ExpandRecycleClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sara on 1/30/2018.
 */

public class LanguageChild implements Parcelable {

    public static final Creator<LanguageChild> CREATOR = new Creator<LanguageChild>() {
        @Override
        public LanguageChild createFromParcel(Parcel source) {
            return new LanguageChild(source);
        }

        @Override
        public LanguageChild[] newArray(int size) {
            return new LanguageChild[size];
        }
    };
    private String lang;

    public LanguageChild(Parcel in) {
        lang = in.readString();
    }

    public LanguageChild(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lang);
    }

}
