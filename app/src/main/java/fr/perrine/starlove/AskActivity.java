package fr.perrine.starlove;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AskActivity extends AppCompatActivity {

    public final static int GALLERY = 123;
    public final static int APP_PHOTO = 456;

    private String mGenre;
    private String mCurrentPhotoPath;
    private Uri mFileUri = null;
    private String mGetImageUrl = "";
    private ProgressBar mProgressBar;
    private String mUid;
    private DatabaseReference mDatabaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        final EditText editUser = findViewById(R.id.edit_user);
        final EditText editSpecies = findViewById(R.id.edit_species);
        final EditText editMass = findViewById(R.id.edit_mass);
        final EditText editHeight = findViewById(R.id.edit_height);
        Button btnValidate = findViewById(R.id.button_validate);
        RadioButton male = findViewById(R.id.radiobtn_male);
        RadioButton female = findViewById(R.id.radiobtn_female);
        final ImageView avatar = findViewById(R.id.img_profile);
        mProgressBar = findViewById(R.id.progress_bar);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(AskActivity.this, ConnexionActivity.class);
            startActivity(intent);
            finish();
        }

        final String username = editUser.getText().toString();
        final String species = editSpecies.getText().toString();
        final int mass = Integer.parseInt(editMass.getText().toString());
        final double height = Double.parseDouble(editHeight.getText().toString());

        if (male.isChecked()) {
            mGenre = "male";
        } else if (female.isChecked()) {
            mGenre = "female";
        }

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY);
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });

        mDatabaseUsers = firebaseDatabase.getReference("users").child(mUid);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileModel model = new ProfileModel(username, mGenre, species, mass, height);
                mDatabaseUsers.setValue(model);
                Intent intent = new Intent(AskActivity.this, AccueilActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GALLERY:
                try {
                    if (resultCode == RESULT_OK) {
                        mFileUri = data.getData();
                        mGetImageUrl = mFileUri.getPath();
                    }
                    saveCaptureImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void saveCaptureImage() {
        if (!mGetImageUrl.equals("") && mGetImageUrl != null) {
            StorageReference ref = FirebaseStorage.getInstance().getReference().child(mUid).child("avatar.jpg");
            ref.putFile(mFileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    FirebaseDatabase.getInstance().getReference("users")
                            .child(mUid).child("avatar").setValue(downloadUri.toString());
                }
            });
        }
    }
}
