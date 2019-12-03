package com.example.media;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.media.model.SongList;
import com.example.media.util.GetMusic;
import com.example.media.util.GetMusicLink;
import com.example.media.util.Mediaplayer_servlet;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity {
    MediaPlayer player = null;
    boolean have_player= false;
    boolean play_now =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(have_player){

        }else {
        player = new MediaPlayer();
        have_player=true;
        }
        Log.d("mian", "onCreate: ");
        final ImageView button_listadd = findViewById(R.id.activity_main_musiclistadd);
        final EditText editText_findsong = findViewById(R.id.activity_findsong);
        button_listadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelp dbHelp = new DBHelp(MainActivity.this);
                final SQLiteDatabase temp = dbHelp.getWritableDatabase();
                final EditText editText = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this).setTitle("请输入歌单名").setView(editText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContentValues values = new ContentValues();
                        values.put("listname",editText.getText().toString());
                        temp.insert(DBHelp.CREATEDB_ONE,null,values);
                        initlistview();
                    }
                }).setNegativeButton("取消",null).show();
            }
        });
        initlistview();
        RelativeLayout layout = findViewById(R.id.activity_main_relate_botton);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,music_player.class);
                startActivity(intent);
            }
        });
        editText_findsong.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    if(event.getAction()==KeyEvent.ACTION_DOWN){
                        Intent intent = new Intent(MainActivity.this,music_player.class);
                        String name=GetMusic.getMusicList(editText_findsong.getText().toString());
                        intent.putExtra("songName",editText_findsong.getText().toString());
                        startActivity(intent);


                    }
                }
                return false;
            }
        });
    }

    public void initlistview(){
        DBHelp dbHelp = new DBHelp(MainActivity.this);
        SQLiteDatabase temp = dbHelp.getWritableDatabase();
        Cursor cursor = temp.query(DBHelp.CREATEDB_ONE,new String[]{"listid","listname"},null,null,null,null,null);
        List<musiclist> list_date = new ArrayList<musiclist>();
        while(cursor.moveToNext()){
            musiclist musiclist = new musiclist();
            musiclist.setId(cursor.getInt(0));
            musiclist.setMusiclistname(cursor.getString(1));
            list_date.add(musiclist);
        }
        ListView listView = findViewById(R.id.activity_main_listview);
        musiclist_Adapter musiclist_adapter = new musiclist_Adapter(MainActivity.this,R.layout.musiclist_item,list_date);
        listView.setAdapter(musiclist_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                musiclist musiclist =(musiclist) adapterView.getItemAtPosition(i);
                getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.activity_main_Franmelayout,new musiclist_fragment(musiclist.getId())).commit();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                musiclist musiclist = (com.example.media.musiclist) parent.getItemAtPosition(position);
                DBHelp db = new DBHelp(MainActivity.this);
                SQLiteDatabase temp = db.getWritableDatabase();
                temp.delete(DBHelp.CREATEDB_ONE,"listid=?",new String[]{String.valueOf(musiclist.getId())});
                temp.delete(DBHelp.CREATEDB_TWO,"listid=?",new String[]{String.valueOf(musiclist.getId())});
                initlistview();
                return true;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main", "onDestroy: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("main", "onStop: ");
    }
}
