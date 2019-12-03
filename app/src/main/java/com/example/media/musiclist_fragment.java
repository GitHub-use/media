package com.example.media;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class musiclist_fragment extends Fragment {

    int listid;
    ArrayList<String> send_musicinfo = new ArrayList<String>();

    public musiclist_fragment(int id){
        this.listid=id;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.musiclist,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init(){
        DBHelp dbHelp = new DBHelp(getContext());
        SQLiteDatabase temp = dbHelp.getWritableDatabase();
        Cursor cursor = temp.query(DBHelp.CREATEDB_TWO,new String[]{"listid","listname"},"listid=?",new String[]{String.valueOf(listid)},null,null,null);
        List<musicinfo> list_date = new ArrayList<musicinfo>();
        while(cursor.moveToNext()){
            musicinfo musiclist = new musicinfo();
            musiclist.setListid(cursor.getInt(0));
            musiclist.setSongName(cursor.getString(1));
            list_date.add(musiclist);
            send_musicinfo.add(cursor.getString(1));
        }

        System.out.println(list_date.size());
        ListView listView = getActivity().findViewById(R.id.music_listname);
        musicInfo_Adapter musiclist_adapter = new musicInfo_Adapter(getContext(),R.layout.musicinfo_item,list_date);
        listView.setAdapter(musiclist_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),music_player.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("songlist",send_musicinfo);
                bundle.putInt("pos",i);
                intent.putExtras(bundle);
                startActivity(intent);
                }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                musicinfo musicinfo = (com.example.media.musicinfo) parent.getItemAtPosition(position);
                DBHelp db = new DBHelp(getContext());
                SQLiteDatabase temp = db.getWritableDatabase();
                temp.delete(DBHelp.CREATEDB_TWO,"listname=?",new String[]{musicinfo.getSongName()});
                init();
                return true;
            }
        });
    }
}
