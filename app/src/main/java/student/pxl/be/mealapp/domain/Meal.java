package student.pxl.be.mealapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meal implements Parcelable {
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
        dest.writeString(title);
        dest.writeString(href);
        dest.writeString(ingredients);
        dest.writeString(thumbnail);
    }
}
