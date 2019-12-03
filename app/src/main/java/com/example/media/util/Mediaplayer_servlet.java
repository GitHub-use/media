package com.example.media.util;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileDescriptor;

public class Mediaplayer_servlet extends Service {
    final MediaPlayer mediaPlayer = new MediaPlayer();
    public class Mediaplayer extends Binder{
        MediaPlayer player = mediaPlayer;
        public void rs(){
            System.out.println("准备释放资源");
            player.pause();
            player.reset();
            player.release();
        }
        public int getlong(){
            int i =0;
            try {
                i=player.getDuration();
            }catch (Exception e){
                return 0;
            }
            return i;
        }
        public int getnowtime(){
            int i=-1;
            try{
            i=player.getCurrentPosition();}catch(Exception e){
                return -1;
            }
            return i;
        }
        public boolean isplaying(){
            try {
                player.isPlaying();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
        public void setnowtime(int time){
            player.seekTo(time);
        }
        public void setsong(final String songName){
            System.out.println(songName);
            new Thread(){
                @Override
                public void run() {
                    try{
                        String url=GetMusicLink.GetSongLink(songName);
                        if(player.isPlaying()){
                            rs();
                        }else{
                            System.out.println("无音乐播放");
                        }
                        player.setDataSource(url);
                        player.prepare();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        public void play(){
            player.start();

        }

        public void pause(){
            player.pause();
        }
    }
    Mediaplayer player = new Mediaplayer();
    static boolean hava_service = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return player;
    }
}
