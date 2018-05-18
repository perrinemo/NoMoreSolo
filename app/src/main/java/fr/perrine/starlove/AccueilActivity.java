package fr.perrine.starlove;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class AccueilActivity extends AppCompatActivity {

    ArrayList<ProfileModel> mImagesPeros = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        TextView hello = findViewById(R.id.tv_hello);
        final TextView username = findViewById(R.id.tv_username);
        ;

        final ImageView avatar = findViewById(R.id.img_avatar);

        FontHelper.setFont(hello, "Starjhol.ttf");
        FontHelper.setFont(username, "Starjhol.ttf");

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference ref = database2.getReference("users").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProfileModel model = dataSnapshot.getValue(ProfileModel.class);
                username.setText(model.getUserName());
                String url = model.getAvatar();
                Glide.with(getApplicationContext()).load(url).into(avatar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Random r = new Random();
        final int genreR = r.nextInt(4 - 1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference candidatRef = database.getReference("aAimer");
        candidatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataForSnapshot : dataSnapshot.getChildren()) {

                    ProfileModel candidat = dataForSnapshot.getValue(ProfileModel.class);

                    String avatar = candidat.getAvatar();

                    String choice;

                    if (genreR >= 1) {
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

                while (mImagesPeros.size() > 9) {
                    Random d = new Random();
                    int delI = d.nextInt(9 - 2);
                    mImagesPeros.remove(delI);
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

        @Override public void onResponse(JSONArray response) {

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

        @Override public void onErrorResponse(VolleyError error) {
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

                final ProgressBar progressBar = findViewById(R.id.progress_bar);
                ValueAnimator animator = ValueAnimator.ofInt(0, progressBar.getMax());
                animator.setDuration(8500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        progressBar.setProgress((Integer) animation.getAnimatedValue());
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        // start your activity here
                    }
                });
                animator.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView load1 = findViewById(R.id.tv_load_1);
                        load1.setText("Loading the blasters");
                    }
                }, 1500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView load2 = findViewById(R.id.tv_load_2);
                        load2.setText("Reading deep mind of the user");
                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView load3 = findViewById(R.id.tv_load_3);
                        load3.setText("Preparing the perfect match");
                    }
                }, 3500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView load4 = findViewById(R.id.tv_load_4);
                        load4.setText("Putting some love in the air");
                    }
                }, 4500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView load5 = findViewById(R.id.tv_load_5);
                        load5.setText("Buying flower for the perfect mate");
                    }
                }, 6000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView load6 = findViewById(R.id.tv_load_6);
                        load6.setText("Calculating distance");
                    }
                }, 6700);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView load7 = findViewById(R.id.tv_load_7);
                        load7.setText("Sortin the best result");
                    }
                }, 7500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent goProfilView = new Intent(AccueilActivity.this, AuraActivity.class);
                        goProfilView.putParcelableArrayListExtra("clef", mImagesPeros);
                        startActivity(goProfilView);
                        finish();
                    }
                }, 8000);


            }
        });

    }
}



