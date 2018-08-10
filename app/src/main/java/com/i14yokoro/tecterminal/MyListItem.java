package com.i14yokoro.tecterminal;

import android.os.Parcel;
import android.os.Parcelable;

public class MyListItem{
    private int id;
    private String upText;
    private boolean focus;

    public MyListItem(){
    }

    public MyListItem(int id, String upText, boolean focus){
        this.id = id;
        this.upText = upText;
        this.focus = focus;
    }

    public void setUpText(String upText) {
        this.upText = upText;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUpText() {
        return upText;
    }

    public long getId(){
        return id;
    }

    public boolean isFocus(){
        return focus;
    }

    public void setFocus(boolean focus){
        this.focus = focus;
    }
}
