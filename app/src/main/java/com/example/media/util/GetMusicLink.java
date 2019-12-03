package com.example.media.util;

import com.example.media.model.SongInfo;
import com.example.media.model.SongList;
import com.google.gson.Gson;

public class GetMusicLink {
    public static String GetSongLink(String songName){
        System.out.println(songName);
        String json = GetMusic.getMusicList(songName);
        Gson gson = new Gson();
        System.out.println(json);
        SongList songList = gson.fromJson(json,SongList.class);
        String songinfo = GetMusic.getMusicInfo(songList.getSong().get(0).getSongid());
        SongInfo songInfo = gson.fromJson(songinfo,SongInfo.class);
        return songInfo.getBitrate().getFile_link();
    }
}
