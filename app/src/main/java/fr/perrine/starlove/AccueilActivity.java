package fr.perrine.starlove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class AccueilActivity extends AppCompatActivity {

    ArrayList<ProfileModel> mImagesPeros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Random r = new Random();
        final int genreR = r.nextInt( 4-1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference candidatRef = database.getReference("aAimer");
        candidatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataForSnapshot: dataSnapshot.getChildren()) {

                    ProfileModel candidat = dataForSnapshot.getValue(ProfileModel.class);

                    String avatar = candidat.getAvatar();

                    String choice;

                    if (genreR >= 1 ) {
                        choice = "female";
                    } else {
                        choice = "male";
                    }

                    if (candidat.getGenre().equals(choice)) {
                        mImagesPeros.add(new ProfileModel(candidat.getUserName(), candidat.getGenre(),
                                candidat.getSpecies(), candidat.getMass(), candidat.getHeight(),
                                avatar));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /**
        // Crée une file d'attente pour les requêtes vers l'API
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://cdn.rawgit.com/akabab/starwars-api/0.2.1/api/all.json";

        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        final JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject characPage = response.getJSONObject(i);

                                String characUserName = characPage.getString("name");
                                String characGenre = characPage.getString("gender");
                                String characImg = characPage.getString("image");

                                String characSpecie;
                                if ((characPage.has("species"))){
                                    characSpecie = characPage.getString("species");
                                } else {
                                    characSpecie = "not published";
                                }


                                int characMass;
                                if ((characPage.has("mass"))) {
                                    characMass = characPage.getInt("mass");
                                } else {
                                    characMass = 0;
                                }

                                double characHeight;
                                if ((characPage.has("height"))) {
                                    characHeight = characPage.getDouble("height");
                                } else {
                                    characHeight = 0.0;
                                }

                                ProfileModel user = new ProfileModel(characUserName, characGenre, characSpecie,
                                        characMass,characHeight, characImg);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("aAimer");
                                myRef.push().setValue(user);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        // On ajoute la requête à la file d'attente
        requestQueue.add(JsonArrayRequest);
        */

        ImageView digitalPrint = findViewById(R.id.btn_aura);
        digitalPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfilView = new Intent(AccueilActivity.this, ActivityPropositions.class);
                goProfilView.putParcelableArrayListExtra("clef", mImagesPeros);
                startActivity(goProfilView);
            }
        });



}
}



