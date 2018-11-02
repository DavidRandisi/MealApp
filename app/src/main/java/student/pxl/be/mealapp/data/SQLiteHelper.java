package student.pxl.be.mealapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import student.pxl.be.mealapp.domain.Meal;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MealsDB";
    private static final String TABLE_FAVORITES = "favorites";

    // Favorite Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_HREF = "href";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_THUMBNAIL = "thumbnail";
    private static final String[] COLUMNS = {KEY_ID, KEY_TITLE, KEY_HREF, KEY_INGREDIENTS, KEY_THUMBNAIL};

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create favorite table
        String CREATE_FAVORITES_TABLE = "CREATE TABLE favorites ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "href TEXT, " +
                "ingredients TEXT, " +
                "thumbnail TEXT )";

        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older favorites table if existed
        db.execSQL("DROP TABLE IF EXISTS favorites");

        // create fresh favorites table
        this.onCreate(db);
    }

    public void addFavoriteMeal(Meal meal) {
        Log.d("Adding favorite meal: ", meal.toString());

        //Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, meal.getTitle());
        values.put(KEY_HREF, meal.getHref());
        values.put(KEY_INGREDIENTS, meal.ingredients);
        values.put(KEY_THUMBNAIL, meal.getThumbnail());

        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public List<Meal> getAllFavoriteMeals() {
        List<Meal> meals = new ArrayList<Meal>();
        String query = "SELECT * FROM " + TABLE_FAVORITES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Meal meal = null;
        if (cursor.moveToFirst()) {
            do {
                meal = new Meal();
                meal.setId(Integer.parseInt(cursor.getString(0)));
                meal.setTitle(cursor.getString(1));
                meal.setHref(cursor.getString(2));
                meal.setIngredients(cursor.getString(3));
                meal.setThumbnail(cursor.getString(4));

                meals.add(meal);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFavoriteMeals()", meals.toString());
        return meals;
    }

    public void deleteFavoriteMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_FAVORITES, KEY_ID + " = ?",
                new String[]{
                        String.valueOf(meal.getId())
                }
        );
        db.close();
        Log.d("deleteFavoriteMeal", meal.toString());
    }
}
