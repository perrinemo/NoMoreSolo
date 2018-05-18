package fr.perrine.starlove;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityNo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no);

        TextView msg = findViewById(R.id.no_choice_text);
        FontHelper.setFont(msg, "Starjhol.ttf");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ArrayList<ProfileModel> candidate = getIntent().getExtras().getParcelableArrayList("clef");
                Intent goProfilView = new Intent(ActivityNo.this, AuraActivity.class);
                goProfilView.putParcelableArrayListExtra("clef", candidate);
                startActivity(goProfilView);
            }
        }, 3500);
    }
}
