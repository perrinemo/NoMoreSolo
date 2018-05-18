package fr.perrine.starlove;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ConnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        final EditText inputMail = findViewById(R.id.et_email);
        final EditText inputPass = findViewById(R.id.et_password);
        Button btnConnexion = findViewById(R.id.btn_signin);
        Button btnInscription = findViewById(R.id.btn_login);
        Button btnForgotPass = findViewById(R.id.btn_forgot_password);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        ImageView imgHeart = findViewById(R.id.img_heart);
        TextView appName = findViewById(R.id.tv_app_name);
        FontHelper.setFont(appName, "Starjhol.ttf");

        Glide.with(this).load(R.drawable.photofunky2).into(imgHeart);

        btnConnexion.setText(R.string.log_in);
        btnInscription.setText(R.string.register);
        btnForgotPass.setVisibility(View.VISIBLE);

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(ConnexionActivity.this, AccueilActivity.class);
            startActivity(intent);
            finish();
        }

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionActivity.this, ForgotPassActivity.class);
                startActivity(intent);
            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = inputMail.getText().toString();
                String pass = inputPass.getText().toString();

                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(ConnexionActivity.this, R.string.please_enter_fiels, Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(ConnexionActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(ConnexionActivity.this, R.string.bad_auth, Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(ConnexionActivity.this, AccueilActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
