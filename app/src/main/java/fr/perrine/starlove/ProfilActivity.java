package fr.perrine.starlove;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
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

        FontHelper.setFont(candidateName, "Starjhol.ttf");

        ProfileModel candidate = getIntent().getExtras().getParcelable("clef");

        String name = candidate.getUserName();
        candidateName.setText(name);
        candidateGenre.setText(candidate.getGenre());
        candidateSpecies.setText(candidate.getSpecies());
        if ((String.valueOf(candidate.getMass())).equals("0")) {
            candidateMass.setText("NC");
        } else {
            candidateMass.setText(String.valueOf(candidate.getMass()));
        }

        if ((Double.toString(candidate.getHeight())).equals("0")) {

            candidateHeight.setText("NC");
        } else {
            candidateHeight.setText(Double.toString(candidate.getHeight()));
        }


        Glide.with(ProfilActivity.this).load(candidate.getAvatar()).into(candidateImg);

        FloatingActionButton sendMsg = findViewById(R.id.btn_msg);

        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
    }

    private void openDialog() {
        LayoutInflater inflater = LayoutInflater.from(ProfilActivity.this);
        View subView = inflater.inflate(R.layout.dialog_msg, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.setTitle(R.string.send_msg)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProfilActivity.this, R.string.mss_has_been_sent, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
