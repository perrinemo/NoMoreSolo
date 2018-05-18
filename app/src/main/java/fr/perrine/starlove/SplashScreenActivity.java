package fr.perrine.starlove;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView ivLogo = findViewById(R.id.img_splash);
        String urlImg = "";
        Glide.with(this).load(R.drawable.startroopers).into(ivLogo);
        final int SPLASH_DISPLAY_LENGTH = 3000;

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashScreenActivity.this,InscriptionActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGTH);


    }
}
