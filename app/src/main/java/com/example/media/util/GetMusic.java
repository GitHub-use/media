package com.example.media.util;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;
public class GetMusic {
    public static String getMusicList(String songName){
        OkHttpClient client = new OkHttpClient();
        Response response = null;
        Request request = new Request.Builder().url("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query="+songName).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36").build();
        try{
        response = client.newCall(request).execute();
        return response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getMusicInfo(String songId){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid="+songId).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36").build();
        try{
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
