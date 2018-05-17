package fr.perrine.starlove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<ProfileModel> {

    public GridAdapter(Context context, ArrayList<ProfileModel> profile) {
        super(context, 0,profile );
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ProfileModel profileModel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, parent, false);

        }

        ImageView heroImage = convertView.findViewById(R.id.img_item);
        Glide.with(parent).load(profileModel.getImage()).into(heroImage);

        return convertView;

    }
}
