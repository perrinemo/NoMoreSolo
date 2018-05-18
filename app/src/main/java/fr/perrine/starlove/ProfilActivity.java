package fr.perrine.starlove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        TextView candidateName = findViewById(R.id.tv_username);
        TextView candidateGenre = findViewById(R.id.tv_genre);
        TextView candidateSpecies = findViewById(R.id.tv_species);
        TextView candidateMass = findViewById(R.id.tv_mass);
        TextView candidateHeight = findViewById(R.id.tv_height);
        ImageView candidateImg = findViewById(R.id.avatar);

        String clef = "CLEF";
        ProfileModel candidate = getIntent().getExtras().getParcelable(clef);

        candidateName.setText((candidate.getUserName()));
        candidateGenre.setText(candidate.getGenre());
        candidateSpecies.setText(candidate.getSpecies());

        Glide.with(ProfilActivity.this).load(candidate.getAvatar()).into(candidateImg);
        //candidateMass.setText(candidate.getHeight());
        //candidateHeight.setText(candidate.getHeight());
    }
}
