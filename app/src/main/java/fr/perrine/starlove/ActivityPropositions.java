package fr.perrine.starlove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityPropositions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propositions);

        final FirebaseAuth auth = FirebaseAuth.getInstance();

        ArrayList<ProfileModel> candidate = getIntent().getExtras().getParcelableArrayList("clef");

        GridView propositionGrid = findViewById(R.id.grid_oposition);
        GridAdapter proposition = new GridAdapter(ActivityPropositions.this, candidate);
        propositionGrid.setAdapter(proposition);

        TextView title = findViewById(R.id.title_propositions);
        FontHelper.setFont(title, "Starjhol.ttf");

        ImageView logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPropositions.this, ConnexionActivity.class);
                startActivity(intent);
                auth.signOut();
            }
        });
    }
}
