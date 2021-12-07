package dev.dworks.apps.anexplorer;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Music_Activity extends Activity {

    private MediaPlayer mediaPlayer=new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        try {
            initMediaPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        Button btnPause = (Button) findViewById(R.id.btnPause);
        Button btnStop = (Button) findViewById(R.id.btnBack);

        btnPlay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Music_Activity.this.finish();
            }
        });
    }

    private void initMediaPlayer() throws IOException {
        AssetManager assetManager=getAssets();
        AssetFileDescriptor fd=assetManager.openFd("basicmusic.MP3");
        mediaPlayer.setDataSource(fd.getFileDescriptor(),fd.getStartOffset(),fd.getLength());
        mediaPlayer.prepare();
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}