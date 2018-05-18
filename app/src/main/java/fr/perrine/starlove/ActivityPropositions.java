package fr.perrine.starlove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityPropositions extends AppCompatActivity {

    ArrayList<ProfileModel> mImagesPeros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propositions);

        ArrayList<ProfileModel> candidate = getIntent().getExtras().getParcelableArrayList("clef");

        GridView propositionGrid = findViewById(R.id.grid_oposition);
        GridAdapter proposition = new GridAdapter(ActivityPropositions.this, candidate);
        propositionGrid.setAdapter(proposition);

        TextView title = findViewById(R.id.title_propositions);
        FontHelper.setFont(title, "Starjhol.ttf");
    }
}
