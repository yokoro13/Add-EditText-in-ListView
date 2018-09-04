package com.i14yokoro.tecterminal;

import android.os.Parcel;
import android.os.Parcelable;

public class MyListItem{
    private int id;
    private String outputText;
    
    public MyListItem(int id, String outputText){
        this.id = id;
        this.outputText = outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getOutputText() {
        return outputText;
    }

    public long getId(){
        return id;
    }

}
