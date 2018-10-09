package student.pxl.be.mealapp.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meal {
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
}
