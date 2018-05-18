package fr.perrine.starlove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

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

        digitalPrint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                startActivity(new Intent(AccueilActivity.this, AuraActivity.class));

                return true;
            }
        });
    }
}

