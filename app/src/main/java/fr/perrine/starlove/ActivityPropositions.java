package fr.perrine.starlove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityPropositions extends AppCompatActivity {

    ArrayList<ProfileModel> mImagesPeros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propositions);

        GridView propositionGrid = findViewById(R.id.grid_oposition);
        final GridAdapter proposition = new GridAdapter(ActivityPropositions.this, mImagesPeros);
        propositionGrid.setAdapter(proposition);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference candidatRef = database.getReference("aAimer");
        candidatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mImagesPeros.clear();

                for (DataSnapshot dataForSnapshot: dataSnapshot.getChildren()){

                    ProfileModel candidat = dataForSnapshot.getValue(ProfileModel.class);

                    String avatar = candidat.getAvatar();

                    String choice = "male";

                    if (candidat.getGenre().equals(choice)) {
                        mImagesPeros.add(new ProfileModel(candidat.getUserName(), candidat.getGenre(),
                                candidat.getSpecies(), candidat.getMass(), candidat.getHeight(),
                                avatar));
                    }

                }
                proposition.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
