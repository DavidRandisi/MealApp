package student.pxl.be.mealapp.meals;

import java.util.List;

public class Meal {
    //extends data.ObjectWithId implements Parcelable
    public int id;
    public int name;
    public String categroy;
    public String instructions;
    public String image;
    public String tags;
    public String youtube;
    public List<String> Ingredients;
    public List<String> measures;
    public boolean favorite;

    public Meal(int id, int name, String categroy, String instructions, String image, String tags, String youtube, List<String> ingredients, List<String> measures, boolean favorite) {
        this.id = id;
        this.name = name;
        this.categroy = categroy;
        this.instructions = instructions;
        this.image = image;
        this.tags = tags;
        this.youtube = youtube;
        Ingredients = ingredients;
        this.measures = measures;
        this.favorite = favorite;
    }

    public Meal() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public List<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        Ingredients = ingredients;
    }

    public List<String> getMeasures() {
        return measures;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
