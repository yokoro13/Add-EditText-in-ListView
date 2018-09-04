package com.i14yokoro.tecterminal;

import android.os.Parcel;
import android.os.Parcelable;

public class MyListItem{
    private int id;
    private String inputText;
    private boolean focus;
    
    public MyListItem(int id, String inputText, boolean focus){
        this.id = id;
        this.inputText = inputText;
        this.focus = focus;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getInputText() {
        return inputText;
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
