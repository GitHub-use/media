package com.example.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


class musiclist{
    private int id;
    private String musiclistname;
    public int getId(){
        return this.id;
    }
    public String getMusiclistname(){
        return this.musiclistname;
    }
    public void setId(int temp){
        id = temp;
    }
    public void setMusiclistname(String temp){
        musiclistname = temp;
    }
}

public class musiclist_Adapter extends ArrayAdapter<musiclist>{
    int textResoureid;
    public musiclist_Adapter(Context context,int textViewResoureId,List<musiclist> object){
        super(context,textViewResoureId,object);
        textResoureid = textViewResoureId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        musiclist musiclist = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(textResoureid,parent,false);
        TextView textView =view.findViewById(R.id.musiclist_listname);
        textView.setText(musiclist.getMusiclistname());
        return view;
    }
}