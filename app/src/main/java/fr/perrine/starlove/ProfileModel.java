package fr.perrine.starlove;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileModel implements Parcelable {


    public static final Creator<ProfileModel> CREATOR = new Creator<ProfileModel>() {
        @Override
        public ProfileModel createFromParcel(Parcel in) {
            return new ProfileModel(in);
        }

        @Override
        public ProfileModel[] newArray(int size) {
            return new ProfileModel[size];
        }
    };

    private String userName;
    private String genre;
    private String species;
    private int mass;
    private double height;
    private String avatar;

    public ProfileModel() {
    }

    public ProfileModel(String userName, String genre, String species, int mass, double height, String avatar) {
        this.userName = userName;
        this.genre = genre;
        this.species = species;
        this.mass = mass;
        this.height = height;
        this.avatar = avatar;
    }

    public ProfileModel(String userName, String genre, String species, int mass, double height) {
        this.userName = userName;
        this.genre = genre;
        this.species = species;
        this.mass = mass;
        this.height = height;
    }

    protected ProfileModel(Parcel in) {
        userName = in.readString();
        genre = in.readString();
        species = in.readString();
        mass = in.readInt();
        height = in.readDouble();
        avatar = in.readString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(genre);
        dest.writeString(species);
        dest.writeInt(mass);
        dest.writeDouble(height);
        dest.writeString(avatar);
    }
}