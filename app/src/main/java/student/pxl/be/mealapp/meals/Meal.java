package student.pxl.be.mealapp.meals;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Meal {
    @SerializedName("idMeal")
    private int id;
    @SerializedName("strMeal")
    private String name;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strArea")
    private String area;
    @SerializedName("strInstruction")
    private String description;
    @SerializedName("strMealThumb")
    private String pictureLink;
    @SerializedName("strTags")
    private String tags;
    @SerializedName("strYoutube")
    private String youtubeLink;
    @SerializedName("strSource")
    private String source;

    //Ingredients
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;

    //Measurements
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;

    //Key:Ingredient - Value:Measurement
    private Map<String,String> ingredientsWithMeasurements;

    public Map<String, String> getIngredientsWithMeasurements() {
        ingredientsWithMeasurements.put(strIngredient1, strMeasure1);
        ingredientsWithMeasurements.put(strIngredient2, strMeasure2);
        ingredientsWithMeasurements.put(strIngredient3, strMeasure3);
        ingredientsWithMeasurements.put(strIngredient4, strMeasure4);
        ingredientsWithMeasurements.put(strIngredient5, strMeasure5);
        ingredientsWithMeasurements.put(strIngredient6, strMeasure6);
        ingredientsWithMeasurements.put(strIngredient7, strMeasure7);
        ingredientsWithMeasurements.put(strIngredient8, strMeasure8);
        ingredientsWithMeasurements.put(strIngredient9, strMeasure9);
        ingredientsWithMeasurements.put(strIngredient10, strMeasure10);
        ingredientsWithMeasurements.put(strIngredient11, strMeasure11);
        ingredientsWithMeasurements.put(strIngredient12, strMeasure12);
        ingredientsWithMeasurements.put(strIngredient13, strMeasure13);
        ingredientsWithMeasurements.put(strIngredient14, strMeasure14);
        ingredientsWithMeasurements.put(strIngredient15, strMeasure15);
        ingredientsWithMeasurements.put(strIngredient16, strMeasure16);
        ingredientsWithMeasurements.put(strIngredient17, strMeasure17);
        ingredientsWithMeasurements.put(strIngredient18, strMeasure18);
        ingredientsWithMeasurements.put(strIngredient19, strMeasure19);
        ingredientsWithMeasurements.put(strIngredient20, strMeasure20);
        return ingredientsWithMeasurements;
    }
}
