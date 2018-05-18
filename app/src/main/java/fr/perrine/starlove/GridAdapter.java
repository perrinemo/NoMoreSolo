package fr.perrine.starlove;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<ProfileModel> {

    public GridAdapter(Context context, ArrayList<ProfileModel> profile) {
        super(context, 0,profile );
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ProfileModel profileModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, parent, false);
        }

        ImageView heroImage = convertView.findViewById(R.id.img_item);
        Glide.with(getContext()).load(profileModel.getAvatar()).into(heroImage);

        heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileModel goProfil = new ProfileModel(profileModel.getUserName(),profileModel.getGenre(),
                        profileModel.getSpecies(),profileModel.getMass(),profileModel.getHeight(),
                        profileModel.getAvatar());

                Toast.makeText(getContext(), profileModel.getAvatar(), Toast.LENGTH_SHORT).show();

            Intent goProfilView = new Intent(getContext(),ProfilActivity.class);
            goProfilView.putExtra("clef", goProfil);
            getContext().startActivity(goProfilView);

            }
        });

        return convertView;

    }
}
