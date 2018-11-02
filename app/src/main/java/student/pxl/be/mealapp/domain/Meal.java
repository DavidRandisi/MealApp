package student.pxl.be.mealapp.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meal implements Parcelable {
    @SerializedName("recipe_id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("href")
    @Expose
    public String href;
    @SerializedName("ingredients")
    @Expose
    public String ingredients;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("description")
    @Expose
    public String description;

    public Meal(){}

    public Meal(Parcel source) {
        id = source.readInt();
        title = source.readString();
        href = source.readString();
        ingredients = source.readString();
        thumbnail = source.readString();
    }

    public void setDescripion(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(href);
        dest.writeString(ingredients);
        dest.writeString(thumbnail);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Meal createFromParcel(Parcel source) {
            return new Meal(source);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return this.getTitle() + " - " + this.getHref();
    }
}
