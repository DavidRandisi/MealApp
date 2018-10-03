package student.pxl.be.mealapp.meals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResult {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("version")
    @Expose
    public Double version;
    @SerializedName("href")
    @Expose
    public String href;
    @SerializedName("results")
    @Expose
    public List<Meal> meals = null;
}
