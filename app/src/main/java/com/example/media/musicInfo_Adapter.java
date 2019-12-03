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

class musicinfo{
    int listid;
    String songName;
    public void setListid(int listid1){
        this.listid=listid1;
    }
    public void setSongName(String songName1){
        this.songName=songName1;
    }
    public int getListid(){
        return this.listid;
    }
    public String getSongName(){
        return this.songName;
    }
}

public class musicInfo_Adapter extends ArrayAdapter<musicinfo> {
    int textResoureid;
    public musicInfo_Adapter(Context context, int textViewResoureId, List<musicinfo> object){
        super(context,textViewResoureId,object);
        textResoureid = textViewResoureId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        musicinfo musiclist = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(textResoureid,parent,false);
        TextView textView =view.findViewById(R.id.musicinfo_songname);

        textView.setText(musiclist.getSongName());
        return view;
    }
}
