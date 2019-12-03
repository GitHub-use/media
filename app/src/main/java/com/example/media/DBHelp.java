package com.example.media;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelp extends SQLiteOpenHelper {

    final static public String CREATEDB_ONE="musiclist";
    final static public String CREATEDB_TWO="music";
    private Context mcontext;
    public DBHelp(Context context){
        super(context,"music",null,1);
        mcontext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+CREATEDB_ONE+"(listid integer primary key autoincrement,listname text)");
        sqLiteDatabase.execSQL("create table "+CREATEDB_TWO+"(musicid integer primary key autoincrement,listid integer not null,listname text)");
        Toast.makeText(mcontext,"创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
