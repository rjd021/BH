package com.example.ryan.login;

        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.VideoView;

public class LoginActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button yourButton = (Button) findViewById(R.id.google_button);
        Button otherButton = (Button) findViewById(R.id.facebook);


        mVideoView = (VideoView) findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vid);

        mVideoView.setVideoURI(uri);
        mVideoView.start();

        yourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DrawerActivity.class));
            }
        });
        otherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View p) {
                    startActivity(new Intent(LoginActivity.this, VenueActivity.class));
            }
        });
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
    }
   // @Override
    protected void onResume(){
        super.onResume();
        mVideoView.start();
    }
}

