package com.example.media;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class addlist_dialog extends Dialog {
    String songName = null;
    public addlist_dialog(Context context,String songName_temp){
        super(context);
        this.songName=songName_temp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmusic_dialog);
        DBHelp dbHelp = new DBHelp(getContext());
        SQLiteDatabase temp = dbHelp.getWritableDatabase();
        Cursor cursor = temp.query(DBHelp.CREATEDB_ONE,new String[]{"listid","listname"},null,null,null,null,null);
        List<musiclist> list_date = new ArrayList<musiclist>();
        while(cursor.moveToNext()){
            musiclist musiclist = new musiclist();
            musiclist.setId(cursor.getInt(0));
            musiclist.setMusiclistname(cursor.getString(1));
            list_date.add(musiclist);
        }
        musiclist_Adapter musiclist_adapter = new musiclist_Adapter(getContext(),R.layout.musiclist_item,list_date);
        ListView listView = findViewById(R.id.addlist_dialog_notnull);

//        listView.setAdapter(musiclist_adapter)

        listView.setAdapter(musiclist_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                musiclist musiclist =(musiclist) adapterView.getItemAtPosition(i);
                System.out.println(musiclist.getMusiclistname());
                System.out.println(songName);
                DBHelp dbHelp1 = new DBHelp(getContext());
                SQLiteDatabase temp1 = dbHelp1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("listid",musiclist.getId());
                values.put("listname",songName);
                temp1.insert(DBHelp.CREATEDB_TWO,null,values);
                dismiss();
            }
        });
    }
}
