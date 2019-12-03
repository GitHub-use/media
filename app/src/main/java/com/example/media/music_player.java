package com.example.media;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.media.util.GetMusicLink;
import com.example.media.util.Mediaplayer_servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class music_player extends AppCompatActivity {
    boolean find_song  = true;
    SeekBar bar ;
    final private int CHANGE = 1;
    boolean isstopthread = false;
    String songName=null;
    int BAR_CHANGE = 6;
    String songUrl = null;
    boolean player_now = false;
    Mediaplayer_servlet.Mediaplayer mediaplayer=null;
    boolean run_service = false;
    ArrayList<String> songlist = null;
    int pos = 0;
    boolean status = true;//true顺序，false随机
    Handler han = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == BAR_CHANGE){
                int long_time = 0;long_time= mediaplayer.getlong();
                int now_time = -1;now_time=mediaplayer.getnowtime();
                if(long_time!=0&&now_time!=-1){
                    bar.setMax(long_time);
                    bar.setProgress(now_time);
                }
            }
        }
    };
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("开始绑定");
            mediaplayer = (Mediaplayer_servlet.Mediaplayer) service;
            if(find_song){
            mediaplayer.setsong(songName);}else {
                mediaplayer.setsong(songlist.get(pos));
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("解除绑定");
            mediaplayer.rs();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.music_player);


        songName = getIntent().getStringExtra("songName");
//        songUrl = GetMusicLink.GetSongLink(songName);
        Intent intent = new Intent(music_player.this,Mediaplayer_servlet.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        if(getIntent().getStringExtra("songName")!=null){
            songName = getIntent().getStringExtra("songName");
            find_song=true;
            findViewById(R.id.music_nextmusic).setVisibility(Button.GONE);
            findViewById(R.id.music_lastmusic).setVisibility(Button.GONE);
            findViewById(R.id.music_status).setVisibility(Button.GONE);
        }else{
            find_song=false;
            findViewById(R.id.music_addmusiclist).setVisibility(Button.GONE);
            Bundle bundle = getIntent().getExtras();
            songlist= bundle.getStringArrayList("songlist");
            pos=bundle.getInt("pos");
            System.out.println(pos);
        }
        findViewById(R.id.music_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.play();
                boolean temp = true;
                while (temp){
                    if(!mediaplayer.isplaying()){
                        mediaplayer.play();
                    }else{
                        temp =false;
                    }
                }
            }
        });
        findViewById(R.id.music_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.pause();
            }
        });
        new Thread(){
            @Override
            public void run() {
                while (true&&!isstopthread){
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.what = BAR_CHANGE;
                    han.sendMessage(msg);}

            }
        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//
//                while (true){
//                    try{
//                        Thread.sleep(1000);
//                        System.out.println(mediaplayer.getnowtime());
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
        findViewById(R.id.music_status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status){
                    status=false;
                }else{
                    status=true;
                }
            }
        });

        findViewById(R.id.music_lastmusic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status){
                    if(pos==0){
                        Toast.makeText(music_player.this,"前面没有了",Toast.LENGTH_SHORT).show();
                        return ;
                    }else{
                        pos=pos-1;
                        mediaplayer.rs();
                        mediaplayer.setsong(songlist.get(pos));
                        boolean temp = true;
                        while (temp){
                            if(!mediaplayer.isplaying()){
                                mediaplayer.play();
                            }else{
                                temp=false;
                            }
                        }
                    }
                }
            }
        });

        findViewById(R.id.music_addmusiclist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new addlist_dialog(music_player.this,songName).show();
            }
        });
        bar=findViewById(R.id.player_seerbar);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaplayer.setnowtime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private Thread newmythread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                try{
                    if(isstopthread)
                        break;
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what=BAR_CHANGE;
                    Bundle bundle = new Bundle();
//                    bundle.putString("long",String.valueOf(mediaplayer.getlong()));
//                    bundle.putString("now",String.valueOf(mediaplayer.getnowtime()));
                    msg.setData(bundle);
                    han.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    });
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event){
//        if(event.getAction()==KeyEvent.ACTION_UP){
//            if(keyCode==KeyEvent.KEYCODE_BACK){
////                moveTaskToBack(true);
//                return true;
//            }
//        }
//        return super.onKeyUp(keyCode,event);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ddd:", "onPause: pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ddddd", "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("stop", "onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destroy", "onDestroy: ");
//        newmythread.interrupt();
        mediaplayer.rs();
        unbindService(connection);
        isstopthread=true;
    }
}
