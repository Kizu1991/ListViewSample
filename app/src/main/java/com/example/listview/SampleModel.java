package com.example.listview;

/**
 * リストの表示に利用するデータを格納するClass
 * Created by pdc-k-kamiya on 2017/12/26.
 */
public class SampleModel{
    private String mImageUrl;
    private String mName;
    private String mDescription;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
